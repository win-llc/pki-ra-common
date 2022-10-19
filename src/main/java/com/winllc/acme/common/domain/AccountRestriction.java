package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winllc.acme.common.constants.AccountRestrictionAction;
import com.winllc.acme.common.constants.AccountRestrictionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "accountrestriction")
@Getter
@Setter
public class AccountRestriction extends BaseAccountEntity implements TaskEntity {

    private AccountRestrictionType type;
    private AccountRestrictionAction action;
    private ZonedDateTime createdOn;
    private ZonedDateTime dueBy;
    private boolean completed = false;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="account_fk")
    private Account account;
    private String addedByUser;
    private String markedCompletedByUser;

    @PreRemove
    private void preRemove(){
        if(account != null){
            account.getAccountRestrictions().remove(this);
        }
    }


    @Override
    public Account getOwnerAccount() {
        return getAccount();
    }

    @Override
    public boolean isComplete() {
        return isCompleted();
    }
}
