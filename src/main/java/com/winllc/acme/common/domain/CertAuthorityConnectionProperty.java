package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winllc.ra.integration.ca.CertAuthorityConnectionPropertyInterface;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "certauthorityconnectionproperty")
public class CertAuthorityConnectionProperty extends AbstractPersistable<Long> implements CertAuthorityConnectionPropertyInterface {

    private String name;
    private String value;
    private Boolean password;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="certAuthorityConnectionInfo_fk")
    private CertAuthorityConnectionInfo certAuthorityConnectionInfo;

    @PreRemove
    private void preRemove(){
        if(this.certAuthorityConnectionInfo != null){
            this.certAuthorityConnectionInfo.getProperties().remove(this);
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
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Boolean getPassword() {
        return password;
    }

    public void setPassword(Boolean password) {
        this.password = password;
    }

    @Override
    public CertAuthorityConnectionInfo getCertAuthorityConnectionInfo() {
        return certAuthorityConnectionInfo;
    }

    public void setCertAuthorityConnectionInfo(CertAuthorityConnectionInfo certAuthorityConnectionInfo) {
        this.certAuthorityConnectionInfo = certAuthorityConnectionInfo;
    }
}
