package com.winllc.acme.common;

import com.winllc.acme.common.constants.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CertSearchParam {
    private CertSearchParams.CertField field;
    private CertSearchParams.CertSearchParamRelation relation;
    private Object value;
    private boolean valueIsDateTime = false;
    private LocalDateTime betweenFrom;
    private LocalDateTime betweenTo;

    private CertSearchParam parent;
    private List<CertSearchParam> params;

    private int page = -1;
    private int pageSize = -1;

    private CertSearchParam(){}

    public CertSearchParam(CertSearchParams.CertSearchParamRelation relation) {
        this.relation = relation;
        this.params = new ArrayList<>();
    }

    public CertSearchParam(CertSearchParams.CertField field, Object value, CertSearchParams.CertSearchParamRelation relation) {
        this.field = field;
        this.relation = relation;
        setValue(value);
    }

    public CertSearchParam(CertSearchParams.CertField field, LocalDateTime from, LocalDateTime to){
        this.field = field;
        this.relation = CertSearchParams.CertSearchParamRelation.BETWEEN;
        this.betweenFrom = from;
        this.betweenTo = to;
    }

    public static CertSearchParam createNew(){
        return new CertSearchParam();
    }

    public static CertSearchParam createNew(CertSearchParams.CertSearchParamRelation relation){
        CertSearchParam param = new CertSearchParam();
        param.setRelation(relation);
        return param;
    }

    public CertSearchParam field(CertSearchParams.CertField field){
        this.field = field;
        return this;
    }

    public CertSearchParam value(Object value){
        setValue(value);
        return this;
    }

    public CertSearchParam relation(CertSearchParams.CertSearchParamRelation relation){
        this.relation = relation;
        if(isRelational()){
            this.params = new ArrayList<>();
        }
        return this;
    }

    public CertSearchParam paginated(int page, int pageSize){
        this.page = page;
        this.pageSize = pageSize;
        return this;
    }

    public CertSearchParam nextPage(){
        this.page++;
        return this;
    }

    public CertSearchParam addSearchParam(CertSearchParam param){
        if(isRelational()){
            param.parent = this;
            if(params == null) params = new ArrayList<>();
            params.add(param);
        }
        return this;
    }

    public int searchParamCount(){
        if(params != null){
            return params.size();
        }else{
            return 0;
        }
    }



    public boolean isRelational(){
        return relation == CertSearchParams.CertSearchParamRelation.OR || relation == CertSearchParams.CertSearchParamRelation.AND;
    }

    public boolean isPaginated(){
        return page >= 0 && pageSize > 0;
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

    public Object getValue() {
        return value;
    }

    public String getValueAsString(){
        if(this.valueIsDateTime){
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(getValueAsLocalDateTime());
        }else{
            return value.toString();
        }
    }

    public ZonedDateTime getValueAsLocalDateTime(){
        //if(this.valueIsDateTime){
            if(value instanceof LocalDateTime){
                return ((LocalDateTime) value).atZone(ZoneId.systemDefault());
            }else{
                ZonedDateTime zdt = ZonedDateTime.parse(this.value.toString(), DateTimeUtil.DATE_TIME_FORMATTER);
                return zdt;
            }
        //}else{
        //    return null;
        //}
    }

    public void setValue(Object value) {
        this.value = value;
        if(value instanceof LocalDateTime){
            this.valueIsDateTime = true;
        }
    }

    public CertSearchParams.CertSearchParamRelation getRelation() {
        return relation;
    }

    public void setRelation(CertSearchParams.CertSearchParamRelation relation) {
        this.relation = relation;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isValueIsDateTime() {
        return valueIsDateTime;
    }

    public LocalDateTime getBetweenFrom() {
        return betweenFrom;
    }

    public void setBetweenFrom(LocalDateTime betweenFrom) {
        this.betweenFrom = betweenFrom;
    }

    public LocalDateTime getBetweenTo() {
        return betweenTo;
    }

    public void setBetweenTo(LocalDateTime betweenTo) {
        this.betweenTo = betweenTo;
    }


}