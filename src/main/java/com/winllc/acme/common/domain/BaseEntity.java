package com.winllc.acme.common.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
public class BaseEntity extends AbstractPersistable<Long> {

    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(Long id){
        super.setId(id);
    }
}
