package com.winllc.acme.common.client;

import com.winllc.acme.common.CertRevocationStatus;
import com.winllc.acme.common.CertificateDetails;
import com.winllc.acme.common.model.acme.Identifier;
import com.winllc.acme.common.ra.RACertificateIssueRequest;
import com.winllc.acme.common.ra.RACertificateRevokeRequest;
import com.winllc.acme.common.util.CertUtil;
import com.winllc.acme.common.util.HttpCommandUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CertAuthorityConnection {

    private static final Logger log = LogManager.getLogger(CertAuthorityConnection.class);

    private final String issueCertPath = "/ca/issueCertificate";
    private final String revokeCertPath = "/ca/revokeCertificate";
    private final String trustChainPath = "/ca/trustChain/";
    private final String certDetailsPath = "/ca/certDetails/";

    private final String baseUrl;
    private final String caConnectionName;

    public CertAuthorityConnection(String baseUrl, String caConnectionName) {
        this.baseUrl = baseUrl;
        this.caConnectionName = caConnectionName;
    }

    public boolean revokeCertificate(X509Certificate certificate, int reason){
        String fullUrl = baseUrl+revokeCertPath;

        Function<String, Boolean> processReturn = (content) -> {
            try {
                return true;
            } catch (Exception e) {
                log.error("Could not process revoke cert", e);
            }
            return false;
        };

        try {
            RACertificateRevokeRequest revokeRequest = new RACertificateRevokeRequest(caConnectionName);
            revokeRequest.setReason(reason);
            revokeRequest.setSerial(certificate.getSerialNumber().toString());

            return HttpCommandUtil.processCustomJsonPost(fullUrl, revokeRequest, 200, processReturn);
        }catch (Exception e){
            log.error("Could not revoke cert", e);
            return false;
        }
    }

    public X509Certificate issueCertificate(Set<Identifier> identifiers, String accountId,
                                            PKCS10CertificationRequest certificationRequest, String source)
            throws Exception{
        String fullUrl = baseUrl+issueCertPath;

        Function<String, X509Certificate> processCert = (content) -> {
            try {
                return CertUtil.base64ToCert(content);
            } catch (Exception e) {
                log.error("Could not covert base64 cert", e);
            }
            return null;
        };

        try {
            String csr = CertUtil.certificationRequestToPEM(certificationRequest);
            String dnsNames = identifiers.stream()
                    .map(Identifier::getValue)
                    .collect(Collectors.joining(","));

            RACertificateIssueRequest raCertificateRequest = new RACertificateIssueRequest(accountId, csr, dnsNames,
                    caConnectionName, source);

            log.info("Connecting to RA with: "+fullUrl);

            X509Certificate certificate = HttpCommandUtil.processCustomJsonPost(fullUrl, raCertificateRequest, 200, processCert);

            if(certificate != null){
                return certificate;
            }else{
                throw new Exception("Failed to build cert");
            }
        }catch (Exception e){
            log.error("Could not issuer cert", e);
        }

        throw new Exception("Could not issue certificate");
    }

    public Optional<CertificateDetails> getCertificateDetails(String serial) {
        String fullUrl = baseUrl+certDetailsPath+caConnectionName;
        try {
            URIBuilder builder = new URIBuilder(fullUrl);
            builder.setParameter("serial", serial);
            HttpGet httpGet = new HttpGet(builder.build());

            CertificateDetails details = HttpCommandUtil.process(httpGet, 200, CertificateDetails.class);

            if(details != null){
                return Optional.of(details);
            }

        } catch (Exception e) {
            log.error("Could not process", e);
        }

        return Optional.empty();
    }

    public CertRevocationStatus isCertificateRevoked(X509Certificate certificate) {
        Optional<CertificateDetails> optionalCertificateDetails = getCertificateDetails(certificate.getSerialNumber().toString());
        if(optionalCertificateDetails.isPresent()){
            CertificateDetails certificateDetails = optionalCertificateDetails.get();
            return certificateDetails.getStatus().equalsIgnoreCase("REVOKED") ? CertRevocationStatus.REVOKED : CertRevocationStatus.VALID;
        }

        return CertRevocationStatus.UNKNOWN;
    }

    public Certificate[] getTrustChain() throws Exception {
        String url = baseUrl+trustChainPath+caConnectionName;

        Function<String, Certificate[]> processTrustChain = (content) -> {
            try {
                return CertUtil.trustChainStringToCertArray(content);
            } catch (Exception e) {
                log.error("Conversion failed", e);
            }
            return null;
        };

        HttpGet httpGet = new HttpGet(url);

        try {
            return HttpCommandUtil.processCustom(httpGet, 200, processTrustChain);
        } catch (Exception e) {
            log.error("Could not process get", e);
            throw new Exception("Could not retrieve trust chain");
        }

    }
}
