package com.winllc.acme.common;

public class SubjectAltName {
    private String value;
    private String type;

    public SubjectAltName(){}

    public SubjectAltName(String value, String type){
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
