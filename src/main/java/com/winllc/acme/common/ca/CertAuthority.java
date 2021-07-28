package com.winllc.acme.common.ca;

import com.winllc.acme.common.CertSearchParam;
import com.winllc.acme.common.CertificateDetails;
import com.winllc.acme.common.SubjectAltNames;
import com.winllc.acme.common.constants.CertificateStatus;
import com.winllc.acme.common.domain.CertAuthorityConnectionInfo;
import org.springframework.context.ApplicationContextAware;

import javax.naming.Name;
import java.security.cert.Certificate;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface CertAuthority extends ApplicationContextAware {

    static List<ConnectionProperty> getRequiredProperties(){
        return new ArrayList<>();
    }

    String getName();
    String getType();
    Name getIssuerName() throws Exception;
    CertAuthorityConnectionInfo getConnectionInfo();
    X509Certificate issueCertificate(String csr, String dn, SubjectAltNames sans) throws Exception;
    boolean revokeCertificate(String serial, int reason) throws Exception;
    CertificateStatus getCertificateStatus(String serial) throws Exception;
    List<CertificateDetails> search(CertSearchParam params);
    Certificate[] getTrustChain() throws Exception;
    X509Certificate getCertificateBySerial(String serial) throws Exception;
    X509CRL getCrl();

    void performOnResults(CertSearchParam param, Function<List<CertificateDetails>, Boolean> operation);
}
