package com.winllc.acme.common.ra;

import com.winllc.acme.common.domain.UniqueEntity;

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
