package com.winllc.acme.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

public interface ProtectedEntity {
    @JsonIgnore
    String getProtectedEntityName();
}
