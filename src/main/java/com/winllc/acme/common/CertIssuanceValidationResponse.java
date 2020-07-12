package com.winllc.acme.common;

import java.util.ArrayList;
import java.util.List;

public class CertIssuanceValidationResponse {

    private String accountKid;
    private boolean accountIsValid;
    private List<CertIssuanceValidationRule> certIssuanceValidationRules;

    public CertIssuanceValidationResponse(String accountKid){
        this.accountKid = accountKid;
    }

    public CertIssuanceValidationResponse(){}

    public String getAccountKid() {
        return accountKid;
    }

    public void setAccountKid(String accountKid) {
        this.accountKid = accountKid;
    }

    public boolean isAccountIsValid() {
        return accountIsValid;
    }

    public void setAccountIsValid(boolean accountIsValid) {
        this.accountIsValid = accountIsValid;
    }

    public List<CertIssuanceValidationRule> getCertIssuanceValidationRules() {
        if(certIssuanceValidationRules == null) certIssuanceValidationRules = new ArrayList<>();
        return certIssuanceValidationRules;
    }

    public void setCertIssuanceValidationRules(List<CertIssuanceValidationRule> certIssuanceValidationRules) {
        this.certIssuanceValidationRules = certIssuanceValidationRules;
    }
}
