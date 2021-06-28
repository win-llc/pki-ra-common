package com.winllc.acme.common.ca;

import com.winllc.acme.common.domain.BaseEntity;
import com.winllc.acme.common.util.CertUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Document(indexName = "cachedcertificate")
public class CachedCertificate implements Comparable<CachedCertificate> {

    @Id
    private String id;
    private String dn;
    private String issuer;
    private String caName;
    private Long serial;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date validFrom;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date validTo;
    private String status;
    private String signatureAlgorithm;
    private Boolean latestForDn;
    private String base64Certificate;

    public CachedCertificate() {
    }

    public CachedCertificate(X509Certificate certificate, String status) throws CertificateEncodingException {
        String thumbprint = DigestUtils.sha1Hex(certificate.getEncoded());
        setId(thumbprint);
        setBase64Certificate(CertUtil.formatCrtFileContents(certificate));
        setDn(certificate.getSubjectDN().getName());
        setIssuer(certificate.getIssuerDN().getName());
        setSerial(certificate.getSerialNumber().longValue());
        setValidFrom(certificate.getNotBefore());
        setValidTo(certificate.getNotAfter());
        setSignatureAlgorithm(certificate.getSigAlgName());
        setStatus(status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public Boolean getLatestForDn() {
        return latestForDn;
    }

    public void setLatestForDn(Boolean latestForDn) {
        this.latestForDn = latestForDn;
    }

    public String getBase64Certificate() {
        return base64Certificate;
    }

    public void setBase64Certificate(String base64Certificate) {
        this.base64Certificate = base64Certificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CachedCertificate)) return false;
        //if (!super.equals(o)) return false;
        CachedCertificate that = (CachedCertificate) o;
        return getIssuer().equals(that.getIssuer()) && getSerial().equals(that.getSerial());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIssuer(), getSerial());
    }

    @Override
    public int compareTo(CachedCertificate o) {
        return o.getValidFrom().compareTo(getValidFrom());
    }

    @Override
    public String toString() {
        return "CachedCertificate{" +
                "dn='" + dn + '\'' +
                ", issuer='" + issuer + '\'' +
                ", serial=" + serial +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", signatureAlgorithm='" + signatureAlgorithm + '\'' +
                "} " + super.toString();
    }
}
