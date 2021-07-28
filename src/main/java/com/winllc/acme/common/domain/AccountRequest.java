package com.winllc.acme.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_request")
public class AccountRequest extends BaseEntity {

    private String accountOwnerEmail;
    private String requestedByEmail;
    private String projectName;
    @Column(nullable = false)
    private String state;
    private String securityPolicyServerProjectId;

    @JsonIgnore
    public static AccountRequest createNew(){
        AccountRequest request = new AccountRequest();
        request.setState("new");
        return request;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRequestedByEmail() {
        return requestedByEmail;
    }

    public void setRequestedByEmail(String requestedByEmail) {
        this.requestedByEmail = requestedByEmail;
    }

    public String getAccountOwnerEmail() {
        return accountOwnerEmail;
    }

    public void setAccountOwnerEmail(String accountOwnerEmail) {
        this.accountOwnerEmail = accountOwnerEmail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSecurityPolicyServerProjectId() {
        return securityPolicyServerProjectId;
    }

    public void setSecurityPolicyServerProjectId(String securityPolicyServerProjectId) {
        this.securityPolicyServerProjectId = securityPolicyServerProjectId;
    }

    public void approve(){
        setState("approve");
    }

    public void reject(){
        setState("reject");
    }
}
