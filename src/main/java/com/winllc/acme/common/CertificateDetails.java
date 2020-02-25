package com.winllc.acme.common;

import com.winllc.acme.common.util.CertUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Optional;

public class CertificateDetails {
    private String serial;
    private String issuer;
    private String status;
    private String certificateBase64;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCertificateBase64() {
        return certificateBase64;
    }

    public void setCertificateBase64(String certificateBase64) {
        this.certificateBase64 = certificateBase64;
    }

    public Optional<X509Certificate> buildX509(){
        if(StringUtils.isNotBlank(certificateBase64)){
            try {
                return Optional.of(CertUtil.base64ToCert(certificateBase64));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}