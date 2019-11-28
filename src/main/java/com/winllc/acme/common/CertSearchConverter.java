package com.winllc.acme.common;

public interface CertSearchConverter {

    String convert(CertSearchParam param);
    String convertAnd(CertSearchParam param);
    String convertOr(CertSearchParam param);
}
