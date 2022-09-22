package com.winllc.acme.common;

import com.winllc.ra.integration.ca.CertSearchConverter;
import com.winllc.ra.integration.ca.CertSearchParam;
import com.winllc.ra.integration.ca.CertSearchParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlCertSearchConverter implements CertSearchConverter<String> {

    private Map<CertSearchParams.CertSearchParamRelation, String> relationMap;
    private Map<CertSearchParams.CertField, String> certFieldMap;

    private SqlCertSearchConverter(){
        relationMap = new HashMap<>();
        relationMap.put(CertSearchParams.CertSearchParamRelation.CONTAINS, "like");
        relationMap.put(CertSearchParams.CertSearchParamRelation.EQUALS, "=");
        relationMap.put(CertSearchParams.CertSearchParamRelation.DOES_NOT_EQUAL, "!=");

        certFieldMap = new HashMap<>();
        certFieldMap.put(CertSearchParams.CertField.SUBJECT, "subject_dn");
        certFieldMap.put(CertSearchParams.CertField.STATUS, "status");
        certFieldMap.put(CertSearchParams.CertField.REVOKED_ON, "revoked_on");
        certFieldMap.put(CertSearchParams.CertField.ISSUER, "issuer_dn");
        certFieldMap.put(CertSearchParams.CertField.VALID_ON, "issued_on");
        certFieldMap.put(CertSearchParams.CertField.EXPIRES_ON, "expires_on");
        certFieldMap.put(CertSearchParams.CertField.SERIAL, "serial");
    }

    public static SqlCertSearchConverter build(){
        return new SqlCertSearchConverter();
    }

    public String convert(CertSearchParam param){

        return paramToSqlClause(param);
    }

    @Override
    public String convertAnd(CertSearchParam param) {
        return convertJoining(param, "and");
    }

    @Override
    public String convertOr(CertSearchParam param) {
        return convertJoining(param, "or");
    }

    private String convertJoining(CertSearchParam param, String joiner){
        StringBuilder builder = new StringBuilder();

        builder.append(" (");

        List<String> childQueries = new ArrayList<>();
        for(CertSearchParam child : param.getParams()){
            childQueries.add(child.buildQuery(this));
        }

        String childQuery = childQueries.stream()
                .collect(Collectors.joining(" "+joiner+" "));

        builder.append(childQuery);
        builder.append(") ");
        return builder.toString();
    }

    private String paramToSqlClause(CertSearchParam param){
        if(param.getRelation() == CertSearchParams.CertSearchParamRelation.EQUALS) {
            return certFieldMap.get(param.getField()) + " " + relationMap.get(param.getRelation()) + " '" + param.getValue() + "'";
        }else{
            return certFieldMap.get(param.getField()) + " " + relationMap.get(param.getRelation()) + " '%" + param.getValue() + "%'";
        }
    }

}
