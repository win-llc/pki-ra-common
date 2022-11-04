package com.winllc.acme.common.domain;

import com.winllc.acme.common.constants.AuditRecordType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "auditrecord")
@Getter
@Setter
public class AuditRecord extends BaseEntity {

    @Column(nullable = false)
    private AuditRecordType type;
    private ZonedDateTime timestamp;
    private String accountKid;
    private String source;

    private String objectClass;
    private String objectUuid;

    public AuditRecord(){}

    public static AuditRecord buildNew(AuditRecordType type){
        AuditRecord record = new AuditRecord();
        record.timestamp = ZonedDateTime.now();
        record.type = type;
        return record;
    }

    public static AuditRecord buildNew(AuditRecordType type, UniqueEntity uniqueEntity){
        AuditRecord auditRecord = buildNew(type);
        auditRecord.setObjectClass(uniqueEntity.getClass().getCanonicalName());
        if(uniqueEntity.getUuid() != null) {
            auditRecord.setObjectUuid(uniqueEntity.getUuid().toString());
        }else{
            auditRecord.setObjectUuid("none");
        }
        return auditRecord;
    }

    public AuditRecord addAccountKid(String accountKid){
        this.setAccountKid(accountKid);
        return this;
    }

    public AuditRecord addSource(String source){
        this.setSource(source);
        return this;
    }
}
