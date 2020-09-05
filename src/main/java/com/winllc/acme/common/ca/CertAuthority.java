package com.winllc.acme.common.ca;

import com.winllc.acme.common.CertSearchParam;
import com.winllc.acme.common.CertificateDetails;
import com.winllc.acme.common.SubjectAltNames;
import com.winllc.acme.common.contants.CertificateStatus;
import com.winllc.acme.common.domain.CertAuthorityConnectionInfo;

import javax.naming.Name;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;

public interface CertAuthority {

    List<ConnectionProperty> getRequiredProperties();
    String getName();
    Name getIssuerName() throws Exception;
    CertAuthorityConnectionInfo getConnectionInfo();
    X509Certificate issueCertificate(String csr, String dn, SubjectAltNames sans) throws Exception;
    boolean revokeCertificate(String serial, int reason) throws Exception;
    CertificateStatus getCertificateStatus(String serial) throws Exception;
    List<CertificateDetails> search(CertSearchParam params);
    Certificate[] getTrustChain() throws Exception;
    X509Certificate getCertificateBySerial(String serial) throws Exception;
}
