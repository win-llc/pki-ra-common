package com.winllc.acme.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CertSearchParams {

    private CertSearchParam firstSearchParams;

    private CertSearchParams(CertSearchParam param){
        this.firstSearchParams = param;
    }

    public static CertSearchParams build(CertSearchParam param){
        return new CertSearchParams(param);
    }

    public CertSearchParams and(CertSearchParam certSearchParam){
        CertSearchParam param = getLowest();
        param.connectedParam = certSearchParam;
        param.connector = CertSearchParamConnector.AND;

        return this;
    }

    public CertSearchParams or(CertSearchParam certSearchParam){
        CertSearchParam param = getLowest();
        param.connectedParam = certSearchParam;
        param.connector = CertSearchParamConnector.OR;

        return this;
    }

    public String buildQuery(){
        //todo
        StringBuilder builder = new StringBuilder();
        CertSearchParam param = this.firstSearchParams;

        builder.append(param.field.toString() + param.relation + param.value);

        while(param.connectedParam != null){
            param = param.connectedParam;
            builder.append(param.connector);
            builder.append(param.field.toString() + param.relation + param.value);
        }

        return builder.toString();
    }

    public String buildQuery(DbConverter converter){
        //todo
        return null;
    }

    public enum CertField {
        SUBJECT,
        ISSUER,
        STATUS,
        VALID_ON,
        EXPIRES_ON,
        SERIAL
    }

    public enum CertSearchParamRelation {
        EQUALS,
        DOES_NOT_EQUAL,
        CONTAINS
    }

    public enum CertSearchParamConnector {
        AND,
        OR
    }

    private CertSearchParam getLowest(){
        if(this.firstSearchParams.connectedParam == null){
            return this.firstSearchParams;
        }else{
            CertSearchParam param = this.firstSearchParams;
            while(param.connectedParam != null){
                param = param.connectedParam;
            }
            return param;
        }
    }

    public static class CertSearchParam {
        private CertField field;
        private String value;
        private CertSearchParamRelation relation;

        private CertSearchParam connectedParam;
        private CertSearchParamConnector connector;

        public CertSearchParam(CertField field, String value, CertSearchParamRelation relation) {
            this.field = field;
            this.value = value;
            this.relation = relation;
        }

        public CertField getField() {
            return field;
        }

        public void setField(CertField field) {
            this.field = field;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public CertSearchParamRelation getRelation() {
            return relation;
        }

        public void setRelation(CertSearchParamRelation relation) {
            this.relation = relation;
        }

    }

    public static class DbConverter {

        private Map<CertSearchParamRelation, String> relationMap;

        private DbConverter(){
            relationMap = new HashMap<>();
            relationMap.put(CertSearchParamRelation.CONTAINS, "like");
            relationMap.put(CertSearchParamRelation.EQUALS, "=");
            relationMap.put(CertSearchParamRelation.DOES_NOT_EQUAL, "!=");
        }

        public static DbConverter build(){
            return new DbConverter();
        }

        public String convert(CertSearchParams params){
            CertSearchParam param = params.firstSearchParams;

            StringBuilder builder = new StringBuilder();
            builder.append(param.field.toString() + relationMap.get(param.relation) + param.value);

            while(param.connectedParam != null){
                param = param.connectedParam;
                builder.append(param.connector);
                builder.append(param.field.toString() + relationMap.get(param.relation) + param.value);
            }
            return builder.toString();
        }
    }
}
