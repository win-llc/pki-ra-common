package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Optional;
import java.util.Set;

public interface AuthCredentialHolderInteface {
    @JsonIgnore
    Set<AuthCredential> getAuthCredentials();

    @JsonIgnore
    Optional<AuthCredential> getLatestAuthCredential();
}
