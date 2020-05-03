package com.winllc.acme.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.Strings;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.util.io.pem.PemObject;
import sun.security.provider.X509Factory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.io.IOUtils.LINE_SEPARATOR;
import static sun.security.provider.X509Factory.BEGIN_CERT;
import static sun.security.provider.X509Factory.END_CERT;

public class CertUtil {

    public static X509Certificate base64ToCert(String certB64) throws CertificateException, IOException {
        certB64 = removeHeaderFooter(certB64, "CERTIFICATE");

        String adjusted = certB64.replaceAll("(?m)^[ \t]*\r?\n", "");

        byte[] encodedCert = Base64.getMimeDecoder().decode(adjusted);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(encodedCert);

        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) certFactory.generateCertificate(inputStream);
    }

    public static PKCS10CertificationRequest csrBase64ToPKC10Object(String csrBase64) throws Exception {
        try {
            csrBase64 = removeHeaderFooter(csrBase64, "CERTIFICATE REQUEST");

            //String adjusted = csrBase64.replaceAll("(?m)^[ \t]*\r?\n", "");
            //byte[] utfBytes = adjusted.getBytes(StandardCharsets.UTF_8);
            byte[] data = org.apache.commons.codec.binary.Base64.decodeBase64(csrBase64);
            //byte encodedCert[] = Base64.getUrlDecoder().decode(csrBase64);
            //ByteArrayInputStream inputStream  =  new ByteArrayInputStream(encodedCert);

            return new PKCS10CertificationRequest(data);

        } catch (IOException ex) {
            //LOG.error("getPKCS10CertRequest: unable to parse csr: " + ex.getMessage());
            ex.printStackTrace();
        }
        throw new Exception("Bad CSR");
    }

    public static PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(String pem) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PKCS10CertificationRequest csr = null;

        try(ByteArrayInputStream pemStream = new ByteArrayInputStream(pem.getBytes("UTF-8"))) {
            Reader pemReader = new BufferedReader(new InputStreamReader(pemStream));
            PEMParser pemParser = new PEMParser(pemReader);

            try {
                Object parsedObj = pemParser.readObject();

                //System.out.println("PemParser returned: " + parsedObj);

                if (parsedObj instanceof PKCS10CertificationRequest) {
                    csr = (PKCS10CertificationRequest) parsedObj;

                }
            } catch (IOException ex) {
                //LOG.error("IOException, convertPemToPublicKey", ex);
            }

            return csr;
        } catch (UnsupportedEncodingException ex) {
            //LOG.error("UnsupportedEncodingException, convertPemToPublicKey", ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Could not parse PEM");
    }

    public static List<String> extractX509CSRDnsNames(String csr) throws Exception {

        PKCS10CertificationRequest certReq = csrBase64ToPKC10Object(csr);

        //String newCsr = "-----BEGIN CERTIFICATE REQUEST-----\n" + csr + "\n-----END CERTIFICATE REQUEST-----";
       // PKCS10CertificationRequest certReq = convertPemToPKCS10CertificationRequest(newCsr);

        List<String> dnsNames = new ArrayList<>();
        Attribute[] attributes = certReq.getAttributes(PKCSObjectIdentifiers.pkcs_9_at_extensionRequest);
        for (Attribute attribute : attributes) {
            for (ASN1Encodable value : attribute.getAttributeValues()) {
                Extensions extensions = Extensions.getInstance(value);
                GeneralNames gns = GeneralNames.fromExtensions(extensions, Extension.subjectAlternativeName);
                for (GeneralName name : gns.getNames()) {
                    if (name.getTagNo() == GeneralName.dNSName) {
                        dnsNames.add(((DERIA5String) name.getName()).getString());
                    }
                }
            }
        }
        return dnsNames;
    }

    public static String[] certAndChainsToPemArray(X509Certificate certificate, Certificate[] chain) throws CertificateEncodingException {
        String[] full;
        if(chain != null) {
            full = new String[chain.length + 1];
            full[0] = convertToTrustedPem(certificate);
            for (int i = 0; i < chain.length; i++) {
                full[i + 1] = formatCrtFileContents(chain[i]);
            }
        }else{
            full = new String[1];
            full[0] = formatCrtFileContents(certificate);
        }
        return full;
    }

    public static Certificate[] trustChainStringToCertArray(String trustChainString){
        String[] lines = trustChainString.split("\\r?\\n");

        List<Certificate> trustChains = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        for(String line : lines){
            if(line.contains(END_CERT)){
                temp.add(line);

                String certString = String.join("\n", temp);

                try {
                    Certificate cert = base64ToCert(certString);
                    trustChains.add(cert);
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                temp = new LinkedList<>();
            }else{
                temp.add(line);
            }
        }

        return trustChains.toArray(new Certificate[0]);
    }


    public static String formatCrtFileContents(final Certificate certificate) throws CertificateEncodingException {
        final Base64.Encoder encoder = Base64.getMimeEncoder(64, LINE_SEPARATOR.getBytes());

        final byte[] rawCrtText = certificate.getEncoded();
        final String encodedCertText = new String(encoder.encode(rawCrtText));
        final String prettified_cert = BEGIN_CERT + LINE_SEPARATOR + encodedCertText + LINE_SEPARATOR + END_CERT;
        return prettified_cert;
    }

    public static String convertToTrustedPem(X509Certificate cert) throws CertificateEncodingException {
        String base64 = formatCrtFileContents(cert);
        //base64 = base64.replace("BEGIN", "BEGIN TRUSTED").replace("END", "END TRUSTED");
        return base64;
    }

    public static String certificationRequestToPEM(PKCS10CertificationRequest csr) throws IOException {
        PemObject pemCSR = new PemObject("CERTIFICATE REQUEST", csr.getEncoded());

        StringWriter str = new StringWriter();
        JcaPEMWriter pemWriter = new JcaPEMWriter(str);
        pemWriter.writeObject(pemCSR);
        pemWriter.close();
        str.close();
        return str.toString();
    }

    public static List<String> getDNSSubjectAlts(X509Certificate cert) {
        LinkedList<String> subjectAltList = new LinkedList<>();
        Collection c = null;
        try {
            c = cert.getSubjectAlternativeNames();
        } catch (CertificateParsingException cpe) {
            cpe.printStackTrace();
        }
        if (c != null) {
            for (Object o : c) {
                List list = (List) o;
                int type = (Integer) list.get(0);
                // If type is 2, then we've got a dNSName
                if (type == 2) {
                    String s = (String) list.get(1);
                    subjectAltList.add(s);
                }
            }
        }
        if (!subjectAltList.isEmpty()) {
            return subjectAltList;
        } else {
            return new ArrayList<>();
        }

    }

    /*
    public String buildPKCS7Base64(Certificate[] chain) throws Exception {
        List<Certificate> chainList = Arrays.asList(chain);
        //anchors.load(trustStoreInput, password);
        X509CertSelector target = new X509CertSelector();
        target.setCertificate((X509Certificate) anchors.getCertificate(caKeystoreAlias));
        PKIXBuilderParameters params = new PKIXBuilderParameters(anchors, target);
        CertStoreParameters intermediates = new CollectionCertStoreParameters(chainList);
        params.addCertStore(CertStore.getInstance("Collection", intermediates));
        CertPathBuilder builder = CertPathBuilder.getInstance("PKIX");

        PKIXCertPathBuilderResult result = (PKIXCertPathBuilderResult) builder.build(params);

        String base64 = com.sun.org.apache.xml.internal.security.utils.Base64.encode(result.getCertPath().getEncoded());
    }
     */

    private static String removeHeaderFooter(String b64, String remove){
        if(b64.contains("----BEGIN")) {
            b64 = b64.replaceAll("-----BEGIN "+remove+"-----\r\n","");
            b64 = b64.replaceAll("\r\n-----END "+remove+"-----","");
            b64 = b64.replaceAll("-----BEGIN "+remove+"-----\n","");
            b64 = b64.replaceAll("\n-----END "+remove+"-----","");
        }
        return b64;
    }

}
