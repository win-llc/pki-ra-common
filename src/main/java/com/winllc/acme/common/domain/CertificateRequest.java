package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nimbusds.jose.util.Base64;
import com.winllc.acme.common.util.CertUtil;
import com.winllc.ra.integration.ca.SubjectAltName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "certificaterequest",
        uniqueConstraints={
                @UniqueConstraint(columnNames = {"certAuthorityName", "issuedCertificateSerial"})
        })
@Getter
@Setter
public class CertificateRequest extends BaseServerEntryEntity {

    @Column(length = 2000)
    private String csr;
    private ZonedDateTime submittedOn;
    private ZonedDateTime reviewedOn;
    private String certAuthorityName;
    private String status;
    @Column(length = 2000)
    private String issuedCertificate;
    private String issuedCertificateSerial;
    @Column(length = 1000)
    private String publicKeyBase64;
    private String requestedBy;
    private String adminReviewer;
    private String primaryDnsName;
    private String requestedDnsNames;


    public static CertificateRequest build(){
        CertificateRequest request = new CertificateRequest();
        request.setSubmittedOn(ZonedDateTime.now());
        request.setStatus("new");
        return request;
    }

    @PreRemove
    private void preRemove(){
        if(getAccount() != null){
            getAccount().getCertificateRequests().remove(this);
        }

        if(getServerEntry() != null){
            getServerEntry().getCertificateRequests().remove(this);
        }
    }

    @JsonIgnore
    public void addPublicKey(PublicKey publicKey){
        this.publicKeyBase64 = Base64.encode(publicKey.getEncoded()).toString();
    }

    @JsonIgnore
    public void addIssuedCertificate(X509Certificate certificate) throws CertificateEncodingException {
        this.issuedCertificate = CertUtil.formatCrtFileContents(certificate);
        addPublicKey(certificate.getPublicKey());
        setIssuedCertificateSerial(certificate.getSerialNumber().toString());
    }


    public Set<String> getRequestedDnsNamesAsSet(){
        if(StringUtils.isNotBlank(requestedDnsNames)){
            return Stream.of(requestedDnsNames.split(",")).collect(Collectors.toSet());
        }else{
            return new HashSet<>();
        }
    }


    public void addRequestedDnsName(String name){
        Set<String> temp = getRequestedDnsNamesAsSet();
        temp.add(name);
        setRequestedDnsNames(String.join(",", temp));
    }

    public void removeRequestedDnsName(String name){
        Set<String> temp = getRequestedDnsNamesAsSet();
        temp.remove(name);
        setRequestedDnsNames(String.join(",", temp));
    }


}
