package com.winllc.acme.common;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class CertificateAuthoritySettings extends SettingsDocument {

    private String type;
    private String name;
    private String externalValidationRulesUrl;
    private List<AdditionalSetting> additionalSettings = new ArrayList<>();

    public Optional<AdditionalSetting> getAdditionalSettingByKey(String key){
        for(AdditionalSetting additionalSetting : additionalSettings){
            if(additionalSetting.getName().contentEquals(key)) return Optional.of(additionalSetting);
        }
        return Optional.empty();
    }

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

    public List<AdditionalSetting> getAdditionalSettings() {
        return additionalSettings;
    }

    public void setAdditionalSettings(List<AdditionalSetting> additionalSettings) {
        this.additionalSettings = additionalSettings;
    }
}
