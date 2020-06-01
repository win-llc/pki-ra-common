package com.winllc.acme.common.model.data;

import java.util.Arrays;

public class CertData extends DataObject<String[]> {

    //First index is cert
    private String[] certChain;
    private String issuerDn;
    private Long serialNumber;

    public CertData(String[] object, String directory, String accountId) {
        super(object, directory, accountId);
    }

    @Override
    public String buildUrl(String baseURL) {
        return buildBaseUrl(baseURL) + "cert/" + getId();
    }

    public String[] getCertChain() {
        if(certChain == null) certChain = new String[0];
        return certChain;
    }

    public void setCertChain(String[] certChain) {
        this.certChain = certChain;
    }

    public String getIssuerDn() {
        return issuerDn;
    }

    public void setIssuerDn(String issuerDn) {
        this.issuerDn = issuerDn;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String buildReturnString(){
        StringBuilder builder = new StringBuilder();
        String[] chain = getObject();
        for(int i = 0; i < chain.length; i++){
            builder.append(chain[i]);
            if(i < chain.length) builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "CertData{" +
                "certChain=" + Arrays.toString(certChain) +
                ", issuerDn='" + issuerDn + '\'' +
                ", serialNumber=" + serialNumber +
                "} " + super.toString();
    }
}
