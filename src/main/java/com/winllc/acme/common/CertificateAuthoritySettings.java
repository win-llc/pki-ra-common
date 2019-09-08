package com.winllc.acme.common;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
public class CertificateAuthoritySettings extends SettingsDocument {

    private String type;
    private String name;
    private String externalValidationRulesUrl;
    private Map<String, String> additionalSettings;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Map<String, String> getAdditionalSettings() {
        return additionalSettings;
    }

    public void setAdditionalSettings(Map<String, String> additionalSettings) {
        this.additionalSettings = additionalSettings;
    }
}
