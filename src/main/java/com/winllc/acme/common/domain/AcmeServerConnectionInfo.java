package com.winllc.acme.common.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "acmeserverconnectioninfo")
public class AcmeServerConnectionInfo extends BaseEntity {

    private String name;
    private String url;

    public AcmeServerConnectionInfo(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public AcmeServerConnectionInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
