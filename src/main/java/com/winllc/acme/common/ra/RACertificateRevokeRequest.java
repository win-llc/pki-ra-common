package com.winllc.acme.common.ra;

public class RACertificateRevokeRequest extends RACertificateRequest {

    private Integer requestId;
    private String serial;
    private Integer reason;

    public RACertificateRevokeRequest() {
    }

    public RACertificateRevokeRequest(String certAuthorityName) {
        super(certAuthorityName);
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }
}
