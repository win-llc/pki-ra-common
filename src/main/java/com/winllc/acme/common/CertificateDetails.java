package com.winllc.acme.common;

import com.winllc.acme.common.util.CertUtil;
import org.apache.commons.lang3.StringUtils;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CertificateDetails {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    private String serial;
    private String subject;
    private String issuer;
    private String status;
    private String certificateBase64;
    private String caName;
    private ZonedDateTime validFrom;
    private ZonedDateTime validTo;

    public CertificateDetails(){}

    public CertificateDetails(X509Certificate x509Certificate){
        this.serial = x509Certificate.getSerialNumber().toString();
        this.issuer = x509Certificate.getIssuerDN().getName();
        this.subject = x509Certificate.getSubjectDN().getName();
        this.validFrom = x509Certificate.getNotBefore().toInstant().atZone(ZoneId.systemDefault());
        this.validTo = x509Certificate.getNotAfter().toInstant().atZone(ZoneId.systemDefault());
        try {
            this.certificateBase64 = CertUtil.formatCrtFileContents(x509Certificate);
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
    }

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public ZonedDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(ZonedDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public ZonedDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(ZonedDateTime validTo) {
        this.validTo = validTo;
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
