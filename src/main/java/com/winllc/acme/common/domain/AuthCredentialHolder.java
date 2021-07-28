package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Optional;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AuthCredentialHolder extends UniqueEntity {

    @JsonIgnore
    public abstract Set<AuthCredential> getAuthCredentials();
    @JsonIgnore
    public Optional<AuthCredential> getLatestAuthCredential(){
        return getAuthCredentials().stream()
                .sorted()
                .findFirst();
    }
}
