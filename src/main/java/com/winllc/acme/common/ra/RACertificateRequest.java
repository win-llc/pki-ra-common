package com.winllc.acme.common.ra;

public abstract class RACertificateRequest {
    private String certAuthorityName;

    protected RACertificateRequest(){}

    public RACertificateRequest(String certAuthorityName) {
        this.certAuthorityName = certAuthorityName;
    }

    public String getCertAuthorityName() {
        return certAuthorityName;
    }

    public void setCertAuthorityName(String certAuthorityName) {
        this.certAuthorityName = certAuthorityName;
    }
}
