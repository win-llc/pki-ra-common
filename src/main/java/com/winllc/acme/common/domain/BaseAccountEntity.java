package com.winllc.acme.common.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BaseAccountEntity extends UniqueEntity implements AccountOwnedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accountOwner_fk")
    private Account account;

    @Override
    public Account getOwnerAccount() {
        return account;
    }
}
