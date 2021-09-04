package com.winllc.acme.common.domain;

import org.springframework.ldap.odm.annotations.Transient;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UniqueEntity extends BaseEntity {

    @Transient
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
