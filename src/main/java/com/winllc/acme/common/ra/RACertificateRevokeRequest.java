package com.winllc.acme.common.ra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RACertificateRevokeRequest extends RACertificateRequest {

    private Long requestId;
    private String serial;
    private Integer reason;

    public RACertificateRevokeRequest() {
    }

    public RACertificateRevokeRequest(String certAuthorityName) {
        super(certAuthorityName);
    }


    @Override
    public String toString() {
        return "RACertificateRevokeRequest{" +
                "requestId=" + requestId +
                ", serial='" + serial + '\'' +
                ", reason=" + reason +
                "} " + super.toString();
    }
}
