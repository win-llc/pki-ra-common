package com.winllc.acme.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "estserverproperties")
public class EstServerProperties extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    private String caConnectionName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaConnectionName() {
        return caConnectionName;
    }

    public void setCaConnectionName(String caConnectionName) {
        this.caConnectionName = caConnectionName;
    }
}
