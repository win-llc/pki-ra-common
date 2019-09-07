package com.winllc.acme.common;

public class CertificateAuthoritySettings {

    private String name;
    private String externalValidationRulesUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalValidationRulesUrl() {
        return externalValidationRulesUrl;
    }

    public void setExternalValidationRulesUrl(String externalValidationRulesUrl) {
        this.externalValidationRulesUrl = externalValidationRulesUrl;
    }
}
