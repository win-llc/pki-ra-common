package com.winllc.acme.common;

import org.springframework.data.annotation.Id;

import java.util.Set;

public abstract class SettingsDocument implements Settings {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
