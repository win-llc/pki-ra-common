package com.winllc.acme.common.client.ca;

import com.winllc.acme.common.util.CertUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Objects;

@Document(indexName = "cachedcertificate", createIndex = true)
@Getter
@Setter
public class CachedCertificate implements Comparable<CachedCertificate> {

    @Id
    private String id;
    private String dn;
    private String issuer;
    private String caName;
    private String serial;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date validFrom;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date validTo;
    private String status;
    private String signatureAlgorithm;
    private Boolean latestForDn;
    @Field(index = false)
    private String base64Certificate;

    public CachedCertificate() {
    }

    public CachedCertificate(X509Certificate certificate, String status) throws CertificateEncodingException {
        String thumbprint = DigestUtils.sha1Hex(certificate.getEncoded());
        setId(thumbprint);
        setBase64Certificate(CertUtil.formatCrtFileContents(certificate));
        setDn(certificate.getSubjectX500Principal().getName());
        setIssuer(certificate.getIssuerX500Principal().getName());
        setSerial(certificate.getSerialNumber().toString());
        setValidFrom(certificate.getNotBefore());
        setValidTo(certificate.getNotAfter());
        setSignatureAlgorithm(certificate.getSigAlgName());
        setStatus(status);
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
                ", caName='" + caName + '\'' +
                ", serial=" + serial +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", signatureAlgorithm='" + signatureAlgorithm + '\'' +
                "} " + super.toString();
    }
}
