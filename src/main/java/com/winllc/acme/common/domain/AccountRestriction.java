package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winllc.acme.common.constants.AccountRestrictionAction;
import com.winllc.acme.common.constants.AccountRestrictionType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "accountrestriction")
public class AccountRestriction extends BaseEntity implements AccountOwnedEntity, TaskEntity {

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

    public AccountRestrictionType getType() {
        return type;
    }

    public void setType(AccountRestrictionType type) {
        this.type = type;
    }

    public AccountRestrictionAction getAction() {
        return action;
    }

    public void setAction(AccountRestrictionAction action) {
        this.action = action;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public ZonedDateTime getDueBy() {
        return dueBy;
    }

    public void setDueBy(ZonedDateTime dueBy) {
        this.dueBy = dueBy;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAddedByUser() {
        return addedByUser;
    }

    public void setAddedByUser(String addedByUser) {
        this.addedByUser = addedByUser;
    }

    public String getMarkedCompletedByUser() {
        return markedCompletedByUser;
    }

    public void setMarkedCompletedByUser(String markedCompletedByUser) {
        this.markedCompletedByUser = markedCompletedByUser;
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
