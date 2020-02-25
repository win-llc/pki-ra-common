package com.winllc.acme.common;

public interface CertSearchConverter<T> {

    T convert(CertSearchParam param);
    T convertAnd(CertSearchParam param);
    T convertOr(CertSearchParam param);
}
