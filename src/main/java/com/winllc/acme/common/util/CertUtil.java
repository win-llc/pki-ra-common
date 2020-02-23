package com.winllc.acme.common.util;

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
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.*;
import java.util.*;
import java.util.stream.Collectors;

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

            String adjusted = csrBase64.replaceAll("(?m)^[ \t]*\r?\n", "");

            byte[] data = Base64.getMimeDecoder().decode(adjusted);
            return new PKCS10CertificationRequest(data);

        } catch (IOException ex) {
            //LOG.error("getPKCS10CertRequest: unable to parse csr: " + ex.getMessage());
            ex.printStackTrace();
        }
        throw new Exception("Bad CSR");
    }

    public static List<String> extractX509CSRDnsNames(String csr) throws Exception {

        PKCS10CertificationRequest certReq = csrBase64ToPKC10Object(csr);

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
        String[] full = new String[chain.length + 1];
        full[0] = convertToPem(certificate);
        for(int i = 0; i < chain.length; i++){
            full[i + 1] = convertToPem(chain[i]);
        }
        return full;
    }

    public static Certificate[] trustChainStringToCertArray(String trustChainString){
        String[] lines = trustChainString.split("\\r?\\n");

        List<Certificate> trustChains = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        for(String line : lines){
            if(line.contains(X509Factory.END_CERT)){
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

    public static String convertToPem(Certificate cert) throws CertificateEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(X509Factory.BEGIN_CERT);
        stringBuilder.append("\n");
        stringBuilder.append(encoder.encodeToString(cert.getEncoded()));
        stringBuilder.append("\n");
        stringBuilder.append(X509Factory.END_CERT);
        return stringBuilder.toString().replaceAll("(?m)^[ \t]*\r?\n", "");
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
