package com.winllc.acme.common;


//A set of rules will be associated with each CA, rules dictate how challenges created for orders
public class CertIssuanceValidationRule {
    private String identifierType;
    private String baseDomainName;
    private boolean requireDnsChallenge = false;
    private boolean requireHttpChallenge = false;
    private boolean allowIssuance;
    private boolean allowHostnameIssuance;

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public String getBaseDomainName() {
        return baseDomainName;
    }

    public void setBaseDomainName(String baseDomainName) {
        this.baseDomainName = baseDomainName;
    }

    public boolean isRequireDnsChallenge() {
        return requireDnsChallenge;
    }

    public void setRequireDnsChallenge(boolean requireDnsChallenge) {
        this.requireDnsChallenge = requireDnsChallenge;
    }

    public boolean isRequireHttpChallenge() {
        return requireHttpChallenge;
    }

    public void setRequireHttpChallenge(boolean requireHttpChallenge) {
        this.requireHttpChallenge = requireHttpChallenge;
    }

    public boolean isAllowIssuance() {
        return allowIssuance;
    }

    public void setAllowIssuance(boolean allowIssuance) {
        this.allowIssuance = allowIssuance;
    }

    public boolean isAllowHostnameIssuance() {
        return allowHostnameIssuance;
    }

    public void setAllowHostnameIssuance(boolean allowHostnameIssuance) {
        this.allowHostnameIssuance = allowHostnameIssuance;
    }




}
