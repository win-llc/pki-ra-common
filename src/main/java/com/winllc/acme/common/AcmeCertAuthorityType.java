package com.winllc.acme.common;

public enum AcmeCertAuthorityType {
    INTERNAL("internal"),
    WINLLC_RA("winllc");

    private String value;

    AcmeCertAuthorityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AcmeCertAuthorityType valueToType(String value) throws Exception {
        for(AcmeCertAuthorityType type : AcmeCertAuthorityType.values()){
            if(type.getValue().equalsIgnoreCase(value)) return type;
        }
        throw new Exception("No matching AcmeCertAuthorityType: "+value);
    }
}
