package com.winllc.acme.common.domain;

import com.winllc.acme.common.constants.AuditRecordType;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {

    private String forUser;
    @Column(nullable = false)
    private ZonedDateTime created;
    private ZonedDateTime expiresOn;
    private ZonedDateTime dueBy;
    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean notificationRead = false;
    private String message;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean isTask = false;
    private Long taskObjectId;
    private String taskObjectClass;
    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean taskComplete = false;
    private AuditRecordType type;
    private String pathToUiView;

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
        cloned.setTask(this.getTask());
        cloned.setTaskObjectClass(this.getTaskObjectClass());
        cloned.setTaskObjectId(this.getTaskObjectId());
        cloned.setTaskComplete(this.getTaskComplete());
        cloned.setType(this.getType());
        cloned.setPathToUiView(this.getPathToUiView());
        return cloned;
    }

    public Notification addMessage(String message){
        this.message = message;
        return this;
    }

    public String getForUser() {
        return forUser;
    }

    public void setForUser(String forUser) {
        this.forUser = forUser;
    }


    public Boolean getNotificationRead() {
        return notificationRead;
    }

    public void setNotificationRead(Boolean notificationRead) {
        this.notificationRead = notificationRead;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(ZonedDateTime expiresOn) {
        this.expiresOn = expiresOn;
    }

    public ZonedDateTime getDueBy() {
        return dueBy;
    }

    public void setDueBy(ZonedDateTime dueBy) {
        this.dueBy = dueBy;
    }

    public Boolean getTask() {
        return isTask;
    }

    public void setTask(Boolean task) {
        isTask = task;
    }

    public Long getTaskObjectId() {
        return taskObjectId;
    }

    public void setTaskObjectId(Long taskObjectId) {
        this.taskObjectId = taskObjectId;
    }

    public String getTaskObjectClass() {
        return taskObjectClass;
    }

    public void setTaskObjectClass(String taskObjectClass) {
        this.taskObjectClass = taskObjectClass;
    }

    public Boolean getTaskComplete() {
        return taskComplete;
    }

    public void setTaskComplete(Boolean taskComplete) {
        this.taskComplete = taskComplete;
    }

    public AuditRecordType getType() {
        return type;
    }

    public void setType(AuditRecordType type) {
        this.type = type;
    }

    public String getPathToUiView() {
        return pathToUiView;
    }

    public void setPathToUiView(String pathToUiView) {
        this.pathToUiView = pathToUiView;
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
