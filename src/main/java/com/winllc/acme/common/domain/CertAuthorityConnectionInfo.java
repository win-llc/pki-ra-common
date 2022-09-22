package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winllc.ra.integration.ca.CertAuthorityConnectionInfoInterface;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "certauthorityconnectioninfo")
public class CertAuthorityConnectionInfo extends BaseEntity implements CertAuthorityConnectionInfoInterface {

    @Column(unique = true)
    private String name;
    private String certAuthorityClassName;
    private String baseUrl;
    private String issuePath;
    private String revokePath;
    private String searchPath;
    @JsonIgnore
    @OneToMany(mappedBy = "certAuthorityConnectionInfo", fetch = FetchType.EAGER)
    private Set<CertAuthorityConnectionProperty> properties;
    //todo require this
    @Column(length = 3000)
    private String trustChainBase64;
    //use to pull auth cert from application keystore for mutual client auth
    @Column(name = "auth_key_alias")
    private String authKeyAlias;

    //todo include global validation contraints

    @PreRemove
    private void preRemove(){
        Set<CertAuthorityConnectionProperty> properties = getProperties();
        if(!CollectionUtils.isEmpty(properties)){
            for(CertAuthorityConnectionProperty property : properties){
                property.setCertAuthorityConnectionInfo(null);
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCertAuthorityClassName() {
        return certAuthorityClassName;
    }

    public void setCertAuthorityClassName(String certAuthorityClassName) {
        this.certAuthorityClassName = certAuthorityClassName;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String getIssuePath() {
        return issuePath;
    }

    public void setIssuePath(String issuePath) {
        this.issuePath = issuePath;
    }

    @Override
    public String getRevokePath() {
        return revokePath;
    }

    public void setRevokePath(String revokePath) {
        this.revokePath = revokePath;
    }

    @Override
    public String getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

    @Override
    public Set<CertAuthorityConnectionProperty> getProperties() {
        if(properties == null) properties = new HashSet<>();
        return properties;
    }

    public void setProperties(Set<CertAuthorityConnectionProperty> properties) {
        this.properties = properties;
    }

    @Override
    public String getTrustChainBase64() {
        return trustChainBase64;
    }

    public void setTrustChainBase64(String trustChainBase64) {
        this.trustChainBase64 = trustChainBase64;
    }

    @Override
    public String getAuthKeyAlias() {
        return authKeyAlias;
    }

    public void setAuthKeyAlias(String authKeyAlias) {
        this.authKeyAlias = authKeyAlias;
    }

    @Override
    public Optional<CertAuthorityConnectionProperty> getPropertyByName(String name){
        if(getProperties() != null){
            return getProperties().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
        }
        return Optional.empty();
    }
}
