package com.winllc.acme.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accountrequest")
@Getter
@Setter
public class AccountRequest extends BaseEntity {

    private String accountOwnerEmail;
    private String requestedByEmail;
    private String projectName;
    //@Column(nullable = false)
    private String state;
    private String securityPolicyServerProjectId;

    @JsonIgnore
    public static AccountRequest createNew(){
        AccountRequest request = new AccountRequest();
        request.setState("new");
        return request;
    }

    public void approve(){
        setState("approve");
    }

    public void reject(){
        setState("reject");
    }
}
