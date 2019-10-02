package com.winllc.acme.common;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class DirectoryDataSettings extends SettingsDocument {
    private String name;

    //Directory fields
    private boolean allowPreAuthorization;
    private String mapsToCertificateAuthorityName;
    private String externalAccountProviderName;
    private Date termsOfServiceLastUpdatedOn;
    private List<String> termsOfServiceHistory;

    //META
    private String metaTermsOfService;
    private String metaWebsite;
    private String[] metaCaaIdentities;
    private boolean metaExternalAccountRequired;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowPreAuthorization() {
        return allowPreAuthorization;
    }

    public void setAllowPreAuthorization(boolean allowPreAuthorization) {
        this.allowPreAuthorization = allowPreAuthorization;
    }

    public String getMapsToCertificateAuthorityName() {
        return mapsToCertificateAuthorityName;
    }

    public void setMapsToCertificateAuthorityName(String mapsToCertificateAuthorityName) {
        this.mapsToCertificateAuthorityName = mapsToCertificateAuthorityName;
    }

    public String getExternalAccountProviderName() {
        return externalAccountProviderName;
    }

    public void setExternalAccountProviderName(String externalAccountProviderName) {
        this.externalAccountProviderName = externalAccountProviderName;
    }

    public Date getTermsOfServiceLastUpdatedOn() {
        return termsOfServiceLastUpdatedOn;
    }

    public void setTermsOfServiceLastUpdatedOn(Date termsOfServiceLastUpdatedOn) {
        this.termsOfServiceLastUpdatedOn = termsOfServiceLastUpdatedOn;
    }

    public String getMetaTermsOfService() {
        return metaTermsOfService;
    }

    public void setMetaTermsOfService(String metaTermsOfService) {
        this.metaTermsOfService = metaTermsOfService;
    }

    public String getMetaWebsite() {
        return metaWebsite;
    }

    public void setMetaWebsite(String metaWebsite) {
        this.metaWebsite = metaWebsite;
    }

    public String[] getMetaCaaIdentities() {
        return metaCaaIdentities;
    }

    public void setMetaCaaIdentities(String[] metaCaaIdentities) {
        this.metaCaaIdentities = metaCaaIdentities;
    }

    public boolean isMetaExternalAccountRequired() {
        return metaExternalAccountRequired;
    }

    public void setMetaExternalAccountRequired(boolean metaExternalAccountRequired) {
        this.metaExternalAccountRequired = metaExternalAccountRequired;
    }

    public List<String> getTermsOfServiceHistory() {
        if(termsOfServiceHistory == null) termsOfServiceHistory = new ArrayList<>();
        return termsOfServiceHistory;
    }

    public void setTermsOfServiceHistory(List<String> termsOfServiceHistory) {
        this.termsOfServiceHistory = termsOfServiceHistory;
    }

    public void updateTermsOfService(String termsOfServiceUrl){
        //Save the current terms of service url to the history
        if(getMetaTermsOfService() != null) getTermsOfServiceHistory().add(getMetaTermsOfService());

        setTermsOfServiceLastUpdatedOn(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        setMetaTermsOfService(termsOfServiceUrl);
    }
}
