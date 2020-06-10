package com.winllc.acme.common;

import java.util.ArrayList;
import java.util.List;

public class AccountValidationResponse {

    private String accountKid;
    private boolean accountIsValid;
    private List<CAValidationRule> caValidationRules;

    public AccountValidationResponse(String accountKid){
        this.accountKid = accountKid;
    }

    private AccountValidationResponse(){}

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

    public List<CAValidationRule> getCaValidationRules() {
        if(caValidationRules == null) caValidationRules = new ArrayList<>();
        return caValidationRules;
    }

    public void setCaValidationRules(List<CAValidationRule> caValidationRules) {
        this.caValidationRules = caValidationRules;
    }
}
