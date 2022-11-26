package com.winllc.acme.common.ra;

import com.winllc.acme.common.domain.UniqueEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RACertificateRequest {
    private String certAuthorityName;

    protected RACertificateRequest(){}

    public RACertificateRequest(String certAuthorityName) {
        this.certAuthorityName = certAuthorityName;
    }

}
