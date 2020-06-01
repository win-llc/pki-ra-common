package com.winllc.acme.common.model.requestresponse;

import org.apache.commons.lang3.StringUtils;

public class KeyChangeRequest implements RequestValidator {
    //required
    private String account;
    //required
    private String oldKey;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOldKey() {
        return oldKey;
    }

    public void setOldKey(String oldKey) {
        this.oldKey = oldKey;
    }

    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(account) && oldKey != null;
    }
}
