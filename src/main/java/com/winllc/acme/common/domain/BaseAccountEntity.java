package com.winllc.acme.common.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseAccountEntity extends UniqueEntity implements AccountOwnedEntity {
    @ManyToOne
    @JoinColumn(name="accountOwner_fk")
    private Account account;

    @Override
    public Account getOwnerAccount() {
        return account;
    }
}
