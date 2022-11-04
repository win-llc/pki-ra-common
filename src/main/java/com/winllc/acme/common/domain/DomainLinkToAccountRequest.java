package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "domainlinktoaccountrequest")
@Getter
@Setter
public class DomainLinkToAccountRequest extends BaseAccountEntity {

    @Column(nullable = false)
    private Long accountId;
    @ElementCollection
    @CollectionTable(name="domainLinkToAccountRequest_requestedDomainIds", joinColumns = @JoinColumn(name = "request_id"))
    @JsonIgnore
    private Set<Long> requestedDomainIds = new HashSet<>();
    @Column(nullable = false)
    private String status;

    private ZonedDateTime requestedOn;
    private String requestedBy;

    private ZonedDateTime statusUpdatedOn;
    private String decisionMadeBy;

    public static DomainLinkToAccountRequest buildNew(){
        DomainLinkToAccountRequest request = new DomainLinkToAccountRequest();
        request.setStatusRequested();
        return request;
    }

    public void setStatusRequested(){
        setStatus("new");
    }

    public void setStatusRejected(){
        setStatus("rejected");
    }

    public void setStatusApproved(){
        setStatus("approved");
    }

    @Override
    public String toString() {
        return "DomainLinkToAccountRequest{" +
                "accountId=" + accountId +
                ", requestedDomainIds=" + requestedDomainIds +
                ", status='" + status + '\'' +
                "} " + super.toString();
    }
}
