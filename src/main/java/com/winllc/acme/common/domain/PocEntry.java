package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "pocentry")
@Getter
@Setter
public class PocEntry extends BaseServerEntryEntity {

    private String email;
    private boolean groupEmail;
    private boolean enabled;
    private boolean owner;
    private boolean canManageAllServers;
    private boolean addedManually;
    private Timestamp addedOn;


    public static PocEntry buildNew(String email, Account account){
        PocEntry entry = new PocEntry();
        entry.setEmail(email);
        entry.setAccount(account);
        entry.setAddedOn(Timestamp.valueOf(LocalDateTime.now()));
        entry.setEnabled(true);
        return entry;
    }

    @PreRemove
    private void preRemove(){
        if(getAccount() != null){
            getAccount().getPocs().remove(this);
        }

        if(getServerEntry() != null){
            getServerEntry().getManagedBy().remove(this);
        }
    }


    @Override
    public Account getOwnerAccount() {
        return getAccount();
    }
}
