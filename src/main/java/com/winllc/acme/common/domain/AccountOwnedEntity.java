package com.winllc.acme.common.domain;

import javax.persistence.MappedSuperclass;

public interface AccountOwnedEntity {
    Account getOwnerAccount();
}
