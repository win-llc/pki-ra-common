package com.winllc.acme.common;

import java.util.List;

public class AccountValidationResponse {

    private boolean accountIsValid;
    private List<CAValidationRule> caValidationRules;

    public boolean isAccountIsValid() {
        return accountIsValid;
    }

    public void setAccountIsValid(boolean accountIsValid) {
        this.accountIsValid = accountIsValid;
    }

    public List<CAValidationRule> getCaValidationRules() {
        return caValidationRules;
    }

    public void setCaValidationRules(List<CAValidationRule> caValidationRules) {
        this.caValidationRules = caValidationRules;
    }
}
