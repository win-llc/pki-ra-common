package com.winllc.acme.common;

import java.util.ArrayList;
import java.util.List;

public class CertSearchParam {
    private CertSearchParams.CertField field;
    private CertSearchParams.CertSearchParamRelation relation;
    private String value;

    private CertSearchParam parent;
    private List<CertSearchParam> params;

    private CertSearchParam(){}

    public CertSearchParam(CertSearchParams.CertSearchParamRelation relation) {
        this.relation = relation;
        this.params = new ArrayList<>();
    }

    public CertSearchParam(CertSearchParams.CertField field, String value, CertSearchParams.CertSearchParamRelation relation) {
        this.field = field;
        this.value = value;
        this.relation = relation;
    }

    public void addSearchParam(CertSearchParam param){
        if(isRelational()){
            param.parent = this;
            params.add(param);
        }
    }

    public boolean isRelational(){
        return relation == CertSearchParams.CertSearchParamRelation.OR || relation == CertSearchParams.CertSearchParamRelation.AND;
    }

    public String buildQuery(CertSearchConverter converter){

        StringBuilder query = new StringBuilder();
        if(!isRelational()){
            query.append(converter.convert(this));
        }else{
            if(relation == CertSearchParams.CertSearchParamRelation.AND){
                query.append(converter.convertAnd(this));
            }else{
                query.append(converter.convertOr(this));
            }
        }

        return query.toString();
    }


    public CertSearchParam getParent() {
        return parent;
    }

    public void setParent(CertSearchParam parent) {
        this.parent = parent;
    }

    public List<CertSearchParam> getParams() {
        return params;
    }

    public void setParams(List<CertSearchParam> params) {
        this.params = params;
    }

    public CertSearchParams.CertField getField() {
        return field;
    }

    public void setField(CertSearchParams.CertField field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CertSearchParams.CertSearchParamRelation getRelation() {
        return relation;
    }

    public void setRelation(CertSearchParams.CertSearchParamRelation relation) {
        this.relation = relation;
    }

}