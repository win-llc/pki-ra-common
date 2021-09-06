package com.winllc.acme.common.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class RequestEntity extends BaseEntity {

    @Column(nullable = false)
    protected String status;
    protected ZonedDateTime requestedOn;
    protected String requestedBy;
    protected ZonedDateTime statusUpdatedOn;
    protected String decisionMadeBy;

    public RequestEntity(String requestedBy) {
        this.requestedBy = requestedBy;
        this.requestedOn = ZonedDateTime.now();
        this.status = "new";
    }

    public RequestEntity() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public ZonedDateTime getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(ZonedDateTime requestedOn) {
        this.requestedOn = requestedOn;
    }

    public ZonedDateTime getStatusUpdatedOn() {
        return statusUpdatedOn;
    }

    public void setStatusUpdatedOn(ZonedDateTime statusUpdatedOn) {
        this.statusUpdatedOn = statusUpdatedOn;
    }

    public String getDecisionMadeBy() {
        return decisionMadeBy;
    }

    public void setDecisionMadeBy(String decisionMadeBy) {
        this.decisionMadeBy = decisionMadeBy;
    }
}
