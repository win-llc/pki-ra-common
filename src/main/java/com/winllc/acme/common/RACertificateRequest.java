package com.winllc.acme.common;

public class RACertificateRequest {
    private String accountKid;
    private String csr;
    private String dnsNames;
    private String certAuthorityName;

    private RACertificateRequest(){}

    public RACertificateRequest(String accountKid, String csr, String dnsNames, String certAuthorityName) {
        this.accountKid = accountKid;
        this.csr = csr;
        this.dnsNames = dnsNames;
        this.certAuthorityName = certAuthorityName;
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

    public String getDnsNames() {
        return dnsNames;
    }

    public void setDnsNames(String dnsNames) {
        this.dnsNames = dnsNames;
    }

    public String getCertAuthorityName() {
        return certAuthorityName;
    }

    public void setCertAuthorityName(String certAuthorityName) {
        this.certAuthorityName = certAuthorityName;
    }
}
