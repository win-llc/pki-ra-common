package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AccountOwnedEntity {
    @JsonIgnore
    Account getOwnerAccount();
}
