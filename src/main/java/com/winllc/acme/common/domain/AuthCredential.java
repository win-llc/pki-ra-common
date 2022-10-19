package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nimbusds.jose.util.Base64;
import com.winllc.acme.common.util.AppUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

@Entity
@Table(name = "authcredential")
@Getter
@Setter
public class AuthCredential extends BaseServerEntryEntity implements Comparable<AuthCredential> {

    @Column(unique = true)
    private String keyIdentifier;
    private String macKey;
    private Boolean valid;
    private String pocAssignedTo;
    @Column(nullable = false)
    private ZonedDateTime createdOn;
    private ZonedDateTime expiresOn;

    public static AuthCredential buildNew(AuthCredentialHolderInteface credentialHolder){
        AuthCredential authCredential = new AuthCredential();

        String macKey = AppUtil.generate256BitString();
        String keyIdentifier = AppUtil.generate20BitString();

        authCredential.setMacKey(macKey);
        authCredential.setKeyIdentifier(keyIdentifier);
        authCredential.setValid(true);
        if(credentialHolder instanceof ServerEntry){
            authCredential.setServerEntry((ServerEntry) credentialHolder);
        }else if(credentialHolder instanceof Account){
            authCredential.setAccount((Account) credentialHolder);
        }
        authCredential.setCreatedOn(ZonedDateTime.now());

        return authCredential;
    }

    @PreRemove
    private void preRemove(){
        try {
            Optional<AuthCredentialHolderInteface> optionalHolder = getParentEntity();
            if (optionalHolder.isPresent()) {
                optionalHolder.get().getAuthCredentials().remove(this);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @JsonIgnore
    public Optional<AuthCredentialHolderInteface> getParentEntity(){
        if(getServerEntry() != null){
            return Optional.of(getServerEntry());
        }else if(getAccount() != null){
            return Optional.of(getAccount());
        }else{
            return Optional.empty();
        }
    }

    public String getMacKeyBase64(){
        return Base64.encode(this.macKey).toString();
    }


    @Override
    public int compareTo(AuthCredential o) {
        return this.createdOn.compareTo(o.createdOn);
    }
}
