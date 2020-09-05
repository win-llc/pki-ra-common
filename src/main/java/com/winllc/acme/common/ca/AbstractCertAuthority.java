package com.winllc.acme.common.ca;

import com.winllc.acme.common.domain.CertAuthorityConnectionInfo;
import com.winllc.acme.common.util.CertUtil;

import javax.naming.Name;
import javax.naming.ldap.LdapName;
import java.security.KeyStore;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public abstract class AbstractCertAuthority implements CertAuthority {

    protected CertAuthorityConnectionInfo info;
    protected KeyStore applicationKeystore;
    protected String keystorePassword;

    protected String name;

    public AbstractCertAuthority(CertAuthorityConnectionInfo info, KeyStore applicationKeystore, String keystorePassword){
        this.info = info;
        this.name = info.getName();
        this.applicationKeystore = applicationKeystore;
        this.keystorePassword = keystorePassword;
    }

    protected CertAuthorityConnectionInfo getInfo(){
        return this.info;
    }

    @Override
    public CertAuthorityConnectionInfo getConnectionInfo() {
        return info;
    }

    @Override
    public Certificate[] getTrustChain() throws Exception {
        String trustChain = getInfo().getTrustChainBase64();

        return CertUtil.trustChainStringToCertArray(trustChain);
    }

    @Override
    public Name getIssuerName() throws Exception {
        Certificate[] chain = getTrustChain();
        if(chain != null && chain.length > 0){
            Certificate issuerCert = chain[0];
            if(issuerCert instanceof X509Certificate){
                Principal principal = ((X509Certificate) issuerCert).getSubjectDN();
                return new LdapName(principal.getName());
            }else{
                throw new RuntimeException("Unexpected cert type: "+issuerCert.getClass().getCanonicalName());
            }
        }else{
            throw new RuntimeException("Can't get Issuer name, no chain found");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
