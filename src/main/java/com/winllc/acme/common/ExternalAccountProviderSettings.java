package com.winllc.acme.common;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
public class ExternalAccountProviderSettings extends SettingsDocument {

    private String name;
    private String accountVerificationUrl;
    private Map<String, String> additionalSettings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountVerificationUrl() {
        return accountVerificationUrl;
    }

    public void setAccountVerificationUrl(String accountVerificationUrl) {
        this.accountVerificationUrl = accountVerificationUrl;
    }

    public Map<String, String> getAdditionalSettings() {
        return additionalSettings;
    }

    public void setAdditionalSettings(Map<String, String> additionalSettings) {
        this.additionalSettings = additionalSettings;
    }
}
