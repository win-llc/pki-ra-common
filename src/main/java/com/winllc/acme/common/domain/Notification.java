package com.winllc.acme.common.domain;

import com.winllc.acme.common.constants.AuditRecordType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "notification")
@Getter
@Setter
public class Notification extends BaseEntity {

    private String forUser;
    @Column(nullable = false)
    private ZonedDateTime created;
    private ZonedDateTime expiresOn;
    private ZonedDateTime dueBy;
    @Column
    private Boolean notificationRead = false;
    private String message;

    @Column
    private Boolean isTask = false;
    private Long taskObjectId;
    private String taskObjectClass;
    @Column
    private Boolean taskComplete = false;
    private AuditRecordType type;
    @Transient
    private String link;

    public static Notification buildNew(){
        Notification notification = new Notification();
        notification.setCreated(ZonedDateTime.now());
        return notification;
    }

    public static Notification buildNew(String forUserName){
        Notification notification = buildNew();
        notification.setForUser(forUserName);
        return notification;
    }

    public Notification markAsTask(AbstractPersistable<Long> taskObj, ZonedDateTime dueBy){
        this.isTask = true;
        this.taskObjectClass = taskObj.getClass().getCanonicalName();
        this.taskObjectId = taskObj.getId();
        this.dueBy = dueBy;
        return this;
    }

    public Notification clone(){
        Notification cloned = new Notification();
        cloned.setCreated(this.getCreated());
        cloned.setMessage(this.getMessage());
        cloned.setForUser(this.getForUser());
        cloned.setDueBy(this.getDueBy());
        cloned.setExpiresOn(this.getExpiresOn());
        cloned.setNotificationRead(this.getNotificationRead());
        cloned.setIsTask(this.isTask);
        cloned.setTaskObjectClass(this.getTaskObjectClass());
        cloned.setTaskObjectId(this.getTaskObjectId());
        cloned.setTaskComplete(this.getTaskComplete());
        cloned.setType(this.getType());
        return cloned;
    }

    public Notification addMessage(String message){
        this.message = message;
        return this;
    }


    @Override
    public String toString() {
        return "Notification{" +
                "forUser='" + forUser + '\'' +
                ", created=" + created +
                ", expiresOn=" + expiresOn +
                ", notificationRead=" + notificationRead +
                ", message='" + message + '\'' +
                "} " + super.toString();
    }
}
