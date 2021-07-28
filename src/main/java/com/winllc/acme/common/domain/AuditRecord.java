package com.winllc.acme.common.domain;

import com.winllc.acme.common.constants.AuditRecordType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_record")
public class AuditRecord extends BaseEntity {

    @Column(nullable = false)
    private AuditRecordType type;
    private Timestamp timestamp;
    private String accountKid;
    private String source;

    private String objectClass;
    private String objectUuid;

    public AuditRecord(){}

    public static AuditRecord buildNew(AuditRecordType type){
        AuditRecord record = new AuditRecord();
        record.timestamp = Timestamp.valueOf(LocalDateTime.now());
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

    public AuditRecordType getType() {
        return type;
    }

    public void setType(AuditRecordType type) {
        this.type = type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAccountKid() {
        return accountKid;
    }

    public void setAccountKid(String accountKid) {
        this.accountKid = accountKid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public String getObjectUuid() {
        return objectUuid;
    }

    public void setObjectUuid(String objectUuid) {
        this.objectUuid = objectUuid;
    }
}
