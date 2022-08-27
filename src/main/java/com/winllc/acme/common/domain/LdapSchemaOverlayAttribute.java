package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ldapschemaoverlayattribute")
public class LdapSchemaOverlayAttribute extends BaseEntity {

    private String name;
    private Boolean enabled;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="ldapSchemaOverlay_fk")
    private LdapSchemaOverlay ldapSchemaOverlay;

    @PreRemove
    public void preRemove(){
        if(ldapSchemaOverlay != null){
            ldapSchemaOverlay.getAttributeMap().remove(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LdapSchemaOverlay getLdapSchemaOverlay() {
        return ldapSchemaOverlay;
    }

    public void setLdapSchemaOverlay(LdapSchemaOverlay ldapSchemaOverlay) {
        this.ldapSchemaOverlay = ldapSchemaOverlay;
    }


}
