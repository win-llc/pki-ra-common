package com.winllc.acme.common.util;

import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.junit.jupiter.api.Test;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CertUtilTest {

    private final String testX509Cert = "-----BEGIN CERTIFICATE-----\n" +
            "MIID1DCCArygAwIBAgIBJTANBgkqhkiG9w0BAQsFADBtMRMwEQYKCZImiZPyLGQB\n" +
            "GRYDY29tMRowGAYKCZImiZPyLGQBGRYKd2lubGxjLWRldjETMBEGCgmSJomT8ixk\n" +
            "ARkWA3BraTESMBAGCgmSJomT8ixkARkWAmNhMREwDwYDVQQDDAhXSU4gUk9PVDAe\n" +
            "Fw0yMDA0MjYxMzA4MjFaFw0yMjA0MTYxMzA4MjFaMCYxJDAiBgNVBAMMG2luZ3Jl\n" +
            "c3Mua3ViZS53aW5sbGMtZGV2LmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCC\n" +
            "AQoCggEBAMoI4iJlZAve6SZHBVL4mkmzLQ4NjyJTSx+tF9qAmh5/IP6c2bxqqKr2\n" +
            "aauNaYStU+7oWMxu7FMk/1atfWQ4ruZoSO9Eqx1sQqNr2obz23gHtAwWvOFxmmYQ\n" +
            "kvpJPPuuU8qUGpLKy1bQMLioptDLbnbbFcZcBJGbhdyJmyjxC9sOIOXqGrfBdpqw\n" +
            "dD6R3POL1CGchwO4C821x7ngGOqlfX/ysfuJsVsACYXiowHGvSBXsZt8gSb8EeFv\n" +
            "Kdcfziv4RGAqXB/jl4pF0WUcqXTo1ZdFtCi2KvLYOXD/Kdm2gMQ3GiCD7VfQb7bC\n" +
            "44vr4azxo0sC51sx6US/Jjidh+LbXqsCAwEAAaOBxTCBwjAfBgNVHSMEGDAWgBRG\n" +
            "EhS/MD8CvuMH3HIn1ytachRxuzAmBgNVHREEHzAdghtpbmdyZXNzLmt1YmUud2lu\n" +
            "bGxjLWRldi5jb20wSAYIKwYBBQUHAQEEPDA6MDgGCCsGAQUFBzABhixodHRwOi8v\n" +
            "ZG9ndGFnLWNhLndpbmxsYy1kZXYuY29tOjgwODAvY2Evb2NzcDAOBgNVHQ8BAf8E\n" +
            "BAMCBLAwHQYDVR0lBBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCMA0GCSqGSIb3DQEB\n" +
            "CwUAA4IBAQBI5COMesYLsSxc2Tx54QtvzeNdecLqdboUpnVY842WcXCtI/CtvBhV\n" +
            "qKq4nCB7znpItuB7cgVn0Hxwtxr2w0wUfVtWxAklmj0Y3+sHFR3EG6zO3pbqPRT7\n" +
            "IBJvnvNLlmxMKy5zP1edn0DV/DFGJuBbMXOsVqw9xMNQj0IM9tIsjTT2tuU5AqVa\n" +
            "whrg05qNTuU3XRGc605eyzek0kXd6zrjaGS4YrN/9U533ncsEs1M+SIlpocvinRD\n" +
            "+2/vl1YfoDobxdSbWXYrgpxMBRYMbLcOwrXChT1v5FLYJqtpPEO4VkSQZkOy4vdR\n" +
            "JJjhv4LdCnyD/RT6lxXzMVzBqX5721Hu\n" +
            "-----END CERTIFICATE-----";

    private final String testRootCa = "-----BEGIN CERTIFICATE-----\n" +
            "MIIEBDCCAuygAwIBAgIBATANBgkqhkiG9w0BAQsFADBtMRMwEQYKCZImiZPyLGQB\n" +
            "GRYDY29tMRowGAYKCZImiZPyLGQBGRYKd2lubGxjLWRldjETMBEGCgmSJomT8ixk\n" +
            "ARkWA3BraTESMBAGCgmSJomT8ixkARkWAmNhMREwDwYDVQQDDAhXSU4gUk9PVDAe\n" +
            "Fw0yMDA0MDQxODE3NTRaFw00MDA0MDQxODE3NTRaMG0xEzARBgoJkiaJk/IsZAEZ\n" +
            "FgNjb20xGjAYBgoJkiaJk/IsZAEZFgp3aW5sbGMtZGV2MRMwEQYKCZImiZPyLGQB\n" +
            "GRYDcGtpMRIwEAYKCZImiZPyLGQBGRYCY2ExETAPBgNVBAMMCFdJTiBST09UMIIB\n" +
            "IjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt+B+eCFB412ZLI+Rkl9vRLLR\n" +
            "M3l/5xSUNeqj8rV6GAIv2JUSjD2by3o/52pncPJnc1iOCzxe79GXb1bXD6QJdNCJ\n" +
            "nDaUq585owtpBNFO+wl5cdtblmJaJJapuiM5xeis9E60ENulM3EKDanjtudWN62r\n" +
            "bGJxJ9m/LILt3x0wPad3Vsw/RA7bi66kxodBunioM9mc/4tlRE/GcVyYupfWYGh4\n" +
            "GHffH+q5HVHVn/NNpYWPtbddXgoihzuIOG6rIm2J+8nywO3i2zMZU7EFfWtUXPwn\n" +
            "ZqcGbp6UdbujctCYbcGP17KZgw6mbPnseS5kwlnkpjlvmZGdTS2D+MN0PR4aQQID\n" +
            "AQABo4GuMIGrMB8GA1UdIwQYMBaAFEYSFL8wPwK+4wfccifXK1pyFHG7MA8GA1Ud\n" +
            "EwEB/wQFMAMBAf8wDgYDVR0PAQH/BAQDAgHGMB0GA1UdDgQWBBRGEhS/MD8CvuMH\n" +
            "3HIn1ytachRxuzBIBggrBgEFBQcBAQQ8MDowOAYIKwYBBQUHMAGGLGh0dHA6Ly9k\n" +
            "b2d0YWctY2Eud2lubGxjLWRldi5jb206ODA4MC9jYS9vY3NwMA0GCSqGSIb3DQEB\n" +
            "CwUAA4IBAQCHUoQsQV3c+0tg7fL5E51HDB/sNJFQ/JPd93PJAq5KSWIdx3GjjkNb\n" +
            "bg2xonZz8x9A0M4WBODkTOX5DHrfnEK4I91yAezppKytKKGdx8258wVN1MV/kMdb\n" +
            "vGpWI4TrA/yzjZVOrDnJiBFPxGoep4ESnOEVP72oY92903KcytDKMbeFbTHSqZFl\n" +
            "O8t3TnqemWAK+q4CiNcRNKpLGRT2YPDFyKK1gIv0WSMnHSL4Nn0vIQnFEZgd/MIe\n" +
            "1iqwYSpQUEyzUMUUSVtb3aAGmxuPKN4p2hIpB+5KdU08vCt1W8kga+6szPb7umUg\n" +
            "w4cVuU8Kktg9dX8yDu4nr5KIh7s/Iog9\n" +
            "-----END CERTIFICATE-----";

    private final String testCsr = "-----BEGIN CERTIFICATE REQUEST-----\n" +
            "MIIDBTCCAe0CAQAwbTELMAkGA1UEBhMCVVMxCzAJBgNVBAgMAlZBMRMwEQYDVQQH\n" +
            "DApBbGV4YW5kcmlhMRAwDgYDVQQKDAdXSU4gTExDMQwwCgYDVQQLDANkZXYxHDAa\n" +
            "BgNVBAMME3Rlc3Qud2lubGxjLWRldi5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IB\n" +
            "DwAwggEKAoIBAQDTKNuEZ8YzMBSmfA1lV0zEY8Qs4kbMTZkz1YAALwUxwKaM3VHM\n" +
            "YdXiaIhP4lTHG7HUarwkf/9YMYYmgrXABb+GGCiXloqrqCvrWucL6S82asDYYXIc\n" +
            "US7qNpQ17po0wtwJvoQhcw+rYjqxK5BEYowDkcsJ0fhyVNRxJqMktvYyEQGzyoYC\n" +
            "Qgqls7OeRDBC+oGkp33h/E4aWU0tdSgUUTpm18o4wKNMminjs052da07c7+4KNKL\n" +
            "Lz8G9sYeFUrEPEGq9MnYHOXCOTUJ4xBqSrBIvhhEKA6AKGbP7c472nTp5TCdO18k\n" +
            "NvSQ40jUBUW4sUQI3SZc9+7ncJzHuc/ayKzXAgMBAAGgUzBRBgkqhkiG9w0BCQ4x\n" +
            "RDBCMAsGA1UdDwQEAwIEMDATBgNVHSUEDDAKBggrBgEFBQcDATAeBgNVHREEFzAV\n" +
            "ghN0ZXN0LndpbmxsYy1kZXYuY29tMA0GCSqGSIb3DQEBCwUAA4IBAQAiIRwq3RKv\n" +
            "ux3tZGj0+VH+vxFzMSwrI3E6mnx4m5GRiHG3E9naDOhvYV3sIHiVz72FDL7buUCp\n" +
            "QIFbZv31ObEPyAcM6MaygamRF3uCj9EHmJHKSqjjUEcM55N/M3x4U1kZuXGqlLYr\n" +
            "RkIPRKHpP0AYP6MeuYyq0Hi5lExkYp/NpfLtXvDjlUoj2hN9YVRXPJG75fR5elWi\n" +
            "nYMTI2uwZ9BcazTCwCnMDLJCWlUR0YmiWh1YKcaOIHlPxR0d3FcrLkRPEX1D6rdy\n" +
            "silS1FHbxy5XkId5Y8lHfaDfECNXQsLVn5ADp8aLC7b0kzt+ZBOhDYIIa05puPKh\n" +
            "q64syxSVFdfC\n" +
            "-----END CERTIFICATE REQUEST-----";

    private String testK8sCsr = "MIICfjCCAWYCAQAwADCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAK5cWF57Co3GPI2jn1CytZipOleNUq2p5vB7hS9GGtaBE8KJj8LmRmWm0smaXjY8X2iM7IUIy_p4FJQinJsFeIGja0YjS0syilOCdPZVBiQrTVblpWqr_faYjcK7lRnBr9FjLCElbBXawneHJCZNOTbNtT6K-W7v6GGcyTYmHQ9Y6oEMylGzyjKh7o0jtRBb0bOAeUIAIWGpZxSzWVnqhwg6e-XvJRGlLMqhokWOaUW_Gnjgr1PGH0oMbUINwzvQLEIh5e0YA3KdOGLnCZsmV3UZ6yUfHLkdM7bllNYWFK0SDD-wd9fOiErFLxj8JljwX4diBhh66w9nQMlDD-QVsOECAwEAAaA5MDcGCSqGSIb3DQEJDjEqMCgwJgYDVR0RBB8wHYIbaW5ncmVzcy5rdWJlLndpbmxsYy1kZXYuY29tMA0GCSqGSIb3DQEBCwUAA4IBAQArl1N7c8DO6xol2Ycg73gTf5sgLhQ7PGo18jSjNScVCEk1IfbjEPs1aBpfkmKKWqdPHYcFbWVYmbZ4Wwm8BoSCSCdAkoM3W8F2SY9eWf6FV5Ja50LfK82VQTFJblmm3_1llP6XOpIatg3nKyWaZHpw6GmiwLJVhKwpZi-bFhupTAqfYs20bskS89aelIgjeKCnRgzp_5Q0yyIT903vG7KrmwJ0hwSUxHHLYcBKU0nJvmPmDVQXFDO5cRxZHSL1dM968qkjhElfYszIV9gLcKUecV6UFna38rux5fBuy3JwF2DSfjemdJcLVQWnNLPr28D-bgVqIVnmLOGXA7kdk_5s";

    @Test
    void base64ToCert() {
        try {
            X509Certificate certificate = CertUtil.base64ToCert(testX509Cert);
            assert certificate != null;
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void csrBase64ToPKC10Object() {
        try {
            PKCS10CertificationRequest certificationRequest = CertUtil.csrBase64ToPKC10Object(testCsr);
            assertNotNull(certificationRequest);

            certificationRequest = CertUtil.csrBase64ToPKC10Object(testK8sCsr);
            assertNotNull(certificationRequest);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void extractX509CSRDnsNames() {
        try {
            List<String> sans = CertUtil.extractX509CSRDnsNames(testCsr);
            assertTrue(sans.contains("test.winllc-dev.com"));
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void certAndChainsToPemArray() {
        try {
            X509Certificate certificate = CertUtil.base64ToCert(testX509Cert);
            X509Certificate chain1 = CertUtil.base64ToCert(testRootCa);
            String[] strings = CertUtil.certAndChainsToPemArray(certificate, new Certificate[]{chain1});
            assertTrue(strings.length == 2);
        }catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void trustChainStringToCertArray() {
        Certificate[] certificates = CertUtil.trustChainStringToCertArray(testRootCa);
        assertTrue(certificates.length == 1);
    }

    @Test
    void formatCrtFileContents() {
        try {
            X509Certificate certificate = CertUtil.base64ToCert(testX509Cert);
            String s = CertUtil.formatCrtFileContents(certificate);
            assertTrue(s.contains("BEGIN CERTIFICATE"));
        }catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void certificationRequestToPEM() {
        try {
            PKCS10CertificationRequest certificationRequest = CertUtil.csrBase64ToPKC10Object(testCsr);
            String pem = CertUtil.certificationRequestToPEM(certificationRequest);
            assertTrue(pem.contains("BEGIN"));
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void getDNSSubjectAlts() {
        try {
            X509Certificate certificate = CertUtil.base64ToCert(testX509Cert);
            List<String> dnsSubjectAlts = CertUtil.getDNSSubjectAlts(certificate);
            assertTrue(dnsSubjectAlts.size() == 1);
        }catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}