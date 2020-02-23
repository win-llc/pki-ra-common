package com.winllc.acme.common.ra;

public class RACertificateIssueRequest extends RACertificateRequest {
    private String accountKid;
    private String csr;
    private String dnsNames;

    private RACertificateIssueRequest(){}

    public RACertificateIssueRequest(String accountKid, String csr, String dnsNames, String certAuthorityName) {
        super(certAuthorityName);
        this.accountKid = accountKid;
        this.csr = csr;
        this.dnsNames = dnsNames;
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

}
