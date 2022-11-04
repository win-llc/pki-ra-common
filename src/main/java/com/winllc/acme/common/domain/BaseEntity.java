package com.winllc.acme.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity extends AbstractPersistable<Long> {

    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date creationDate;

    public void setId(Long id){
        super.setId(id);
    }
}
