package com.winllc.acme.common.contants;

public enum IdentifierType {
    DNS("dns");

    private String value;

    IdentifierType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
