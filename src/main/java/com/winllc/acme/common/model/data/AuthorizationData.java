package com.winllc.acme.common.model.data;

import com.winllc.acme.common.model.acme.Authorization;

public class AuthorizationData extends DataObject<Authorization> {

    private String orderId;
    private Boolean preAuthz = false;

    @Override
    public String buildUrl(String baseURL) {
        return buildBaseUrl(baseURL) + "authz/" + getId();
    }

    private AuthorizationData(){}

    public AuthorizationData(Authorization object, String directory){
        super(object, directory);
    }

    public AuthorizationData(Authorization authorization, String directory, String orderId){
        this(authorization, directory);
        this.orderId = orderId;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Boolean getPreAuthz() {
        return preAuthz;
    }

    public void setPreAuthz(Boolean preAuthz) {
        this.preAuthz = preAuthz;
    }

    @Override
    public String toString() {
        return "AuthorizationData{" +
                "orderId='" + orderId + '\'' +
                ", accountId='" + getAccountId() + '\'' +
                "} " + super.toString();
    }
}
