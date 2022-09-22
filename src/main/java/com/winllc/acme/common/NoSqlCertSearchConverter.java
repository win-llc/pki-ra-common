package com.winllc.acme.common;

import com.winllc.ra.integration.ca.CertSearchConverter;
import com.winllc.ra.integration.ca.CertSearchParam;
import com.winllc.ra.integration.ca.CertSearchParams;

import java.util.HashMap;
import java.util.Map;

public class NoSqlCertSearchConverter implements CertSearchConverter {

    private Map<CertSearchParams.CertSearchParamRelation, String> relationMap;
    private Map<CertSearchParams.CertField, String> certFieldMap;

    private NoSqlCertSearchConverter(){
        relationMap = new HashMap<>();
        relationMap.put(CertSearchParams.CertSearchParamRelation.CONTAINS, "like");
        relationMap.put(CertSearchParams.CertSearchParamRelation.EQUALS, "=");
        relationMap.put(CertSearchParams.CertSearchParamRelation.DOES_NOT_EQUAL, "!=");

        certFieldMap = new HashMap<>();
        certFieldMap.put(CertSearchParams.CertField.ISSUER, "");
    }

    public static NoSqlCertSearchConverter build(){
        return new NoSqlCertSearchConverter();
    }


    @Override
    public String convert(CertSearchParam param) {
        return null;
    }

    @Override
    public String convertAnd(CertSearchParam param) {
        return null;
    }

    @Override
    public String convertOr(CertSearchParam param) {
        return null;
    }
}
