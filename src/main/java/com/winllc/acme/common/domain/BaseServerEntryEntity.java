package com.winllc.acme.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseServerEntryEntity extends BaseAccountEntity {

    @ManyToOne
    @JoinColumn(name="serverEntry_fk")
    private ServerEntry serverEntry;

}
