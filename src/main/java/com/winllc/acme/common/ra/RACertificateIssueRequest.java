package com.winllc.acme.common.ra;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RACertificateIssueRequest extends RACertificateRequest {
    private Long existingCertificateRequestId = null;
    private String accountKid;
    private String subjectNameRequest;
    private String csr;
    private String dnsNames;
    private String source;

    private RACertificateIssueRequest(){}

    public RACertificateIssueRequest(String accountKid, String csr, String primaryDnsName, String dnsNames, String certAuthorityName, String source) {
        super(certAuthorityName);
        this.accountKid = accountKid;
        this.csr = csr;
        this.subjectNameRequest = primaryDnsName;
        this.dnsNames = dnsNames;
        this.source = source;
    }

    public RACertificateIssueRequest(String accountKid, String csr, String dnsNames, String certAuthorityName, String source) {
        super(certAuthorityName);
        this.accountKid = accountKid;
        this.csr = csr;
        this.dnsNames = dnsNames;
        this.source = source;
    }

    @JsonIgnore
    public List<String> getDnsNameList(){
        if(StringUtils.isNotBlank(dnsNames)){
            return Stream.of(dnsNames.replace(" ", "").split(",")).collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }

    public String getAccountKid() {
        return accountKid;
    }

    public void setAccountKid(String accountKid) {
        this.accountKid = accountKid;
    }

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public String getSubjectNameRequest() {
        return subjectNameRequest;
    }

    public void setSubjectNameRequest(String subjectNameRequest) {
        this.subjectNameRequest = subjectNameRequest;
    }

    public String getDnsNames() {
        return dnsNames;
    }

    public void setDnsNames(String dnsNames) {
        this.dnsNames = dnsNames;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getExistingCertificateRequestId() {
        return existingCertificateRequestId;
    }

    public void setExistingCertificateRequestId(Long existingCertificateRequestId) {
        this.existingCertificateRequestId = existingCertificateRequestId;
    }
}
