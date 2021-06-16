package com.winllc.acme.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winllc.acme.common.util.CertUtil;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.bouncycastle.jce.provider.X509AttrCertParser;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CertificateDetails {

    private String serial;
    private String subject;
    private String issuer;
    private String status;
    private String certificateBase64;
    private String certificatePrettyPrint;
    private String caName;
    private ZonedDateTime validFrom;
    private ZonedDateTime validTo;
    private boolean canRequestRevocation = false;

    public CertificateDetails(){}

    public CertificateDetails(X509Certificate x509Certificate, String status){
        this.serial = x509Certificate.getSerialNumber().toString();
        this.issuer = x509Certificate.getIssuerDN().getName();
        this.subject = x509Certificate.getSubjectDN().getName();
        this.validFrom = x509Certificate.getNotBefore().toInstant().atZone(ZoneId.systemDefault());
        this.validTo = x509Certificate.getNotAfter().toInstant().atZone(ZoneId.systemDefault());
        this.status = status;
        try {
            this.certificateBase64 = CertUtil.formatCrtFileContents(x509Certificate);
            this.certificatePrettyPrint = x509Certificate.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkCanRevoke();
    }

    @JsonIgnore
    private void checkCanRevoke(){
        if(status.contentEquals("VALID")){
            this.canRequestRevocation = true;
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

    public String getCertificatePrettyPrint() {
        return certificatePrettyPrint;
    }

    public void setCertificatePrettyPrint(String certificatePrettyPrint) {
        this.certificatePrettyPrint = certificatePrettyPrint;
    }

    public boolean isCanRequestRevocation() {
        return canRequestRevocation;
    }

    public void setCanRequestRevocation(boolean canRequestRevocation) {
        this.canRequestRevocation = canRequestRevocation;
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
