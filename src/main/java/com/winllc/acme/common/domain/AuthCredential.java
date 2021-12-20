package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nimbusds.jose.util.Base64;
import com.winllc.acme.common.util.AppUtil;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

@Entity
@Table(name = "auth_credential")
public class AuthCredential extends BaseEntity implements Comparable<AuthCredential> {

    @Column(unique = true)
    private String keyIdentifier;
    private String macKey;
    private Boolean valid;
    private String pocAssignedTo;
    @Column(nullable = false)
    private ZonedDateTime createdOn;
    private ZonedDateTime expiresOn;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parentEntity_fk")
    @NotFound(action = NotFoundAction.IGNORE)
    private AuthCredentialHolder parentEntity;

    public static AuthCredential buildNew(AuthCredentialHolder credentialHolder){
        AuthCredential authCredential = new AuthCredential();

        String macKey = AppUtil.generate256BitString();
        String keyIdentifier = AppUtil.generate20BitString();

        authCredential.setMacKey(macKey);
        authCredential.setKeyIdentifier(keyIdentifier);
        authCredential.setValid(true);
        authCredential.setParentEntity(credentialHolder);
        authCredential.setCreatedOn(ZonedDateTime.now());

        return authCredential;
    }

    @PreRemove
    private void preRemove(){
        try {
            if (getParentEntity() != null) {
                getParentEntity().getAuthCredentials().remove(this);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @JsonIgnore
    public <T extends AuthCredentialHolder> Optional<T> getParentEntityByType(Class<T> clazz){
        if(getParentEntity().getClass().isAssignableFrom(clazz)){
            return Optional.of((T) getParentEntity());
        }else{
            return Optional.empty();
        }
    }

    public String getMacKeyBase64(){
        return Base64.encode(this.macKey).toString();
    }

    public String getKeyIdentifier() {
        return keyIdentifier;
    }

    public void setKeyIdentifier(String keyIdentifier) {
        this.keyIdentifier = keyIdentifier;
    }

    public String getMacKey() {
        return macKey;
    }

    public void setMacKey(String macKey) {
        this.macKey = macKey;
    }

    public String getPocAssignedTo() {
        return pocAssignedTo;
    }

    public void setPocAssignedTo(String pocAssignedTo) {
        this.pocAssignedTo = pocAssignedTo;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public ZonedDateTime getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(ZonedDateTime expiresOn) {
        this.expiresOn = expiresOn;
    }

    public AuthCredentialHolder getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(AuthCredentialHolder parentEntity) {
        this.parentEntity = parentEntity;
    }

    @Override
    public int compareTo(AuthCredential o) {
        return this.createdOn.compareTo(o.createdOn);
    }
}
