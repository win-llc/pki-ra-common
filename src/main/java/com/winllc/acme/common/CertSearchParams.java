package com.winllc.acme.common;

public class CertSearchParams {

    public enum CertField {
        SUBJECT,
        ISSUER,
        STATUS,
        VALID_ON,
        EXPIRES_ON,
        REVOKED_ON,
        SERIAL
    }

    public enum CertSearchParamRelation {
        EQUALS,
        DOES_NOT_EQUAL,
        CONTAINS,
        GREATER_THAN,
        LESS_THAN,
        AND,
        OR
    }

}
