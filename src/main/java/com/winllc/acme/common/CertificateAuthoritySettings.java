package com.winllc.acme.common;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class CertificateAuthoritySettings extends SettingsDocument {

    private String type;
    private String name;
    private String issuerDn;
    private String mapsToExternalAccountProviderName;
    private String mapsToCaConnectionName;
    private String baseUrl;
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

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdditionalSetting> getAdditionalSettings() {
        return additionalSettings;
    }

    public void setAdditionalSettings(List<AdditionalSetting> additionalSettings) {
        this.additionalSettings = additionalSettings;
    }

    public String getMapsToExternalAccountProviderName() {
        return mapsToExternalAccountProviderName;
    }

    public void setMapsToExternalAccountProviderName(String mapsToExternalAccountProviderName) {
        this.mapsToExternalAccountProviderName = mapsToExternalAccountProviderName;
    }

    public String getIssuerDn() {
        return issuerDn;
    }

    public void setIssuerDn(String issuerDn) {
        this.issuerDn = issuerDn;
    }

    public String getMapsToCaConnectionName() {
        return mapsToCaConnectionName;
    }

    public void setMapsToCaConnectionName(String mapsToCaConnectionName) {
        this.mapsToCaConnectionName = mapsToCaConnectionName;
    }
}
