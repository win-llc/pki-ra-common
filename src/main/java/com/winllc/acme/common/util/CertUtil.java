package com.winllc.acme.common.util;

import com.winllc.acme.common.SubjectAltNames;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509ExtensionUtils;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.*;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;

import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static org.apache.commons.io.IOUtils.LINE_SEPARATOR;

public class CertUtil {

    public static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    public static final String END_CERT = "-----END CERTIFICATE-----";

    public static X509Certificate base64ToCert(String certB64) throws CertificateException, IOException {
        certB64 = removeHeaderFooter(certB64, "CERTIFICATE");

        String adjusted = certB64.replaceAll("(?m)^[ \t]*\r?\n", "");
        adjusted = adjusted.replaceAll("-", "+").replaceAll("_", "/");

        byte[] encodedCert = Base64.getMimeDecoder().decode(adjusted.getBytes("UTF-8"));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(encodedCert);

        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) certFactory.generateCertificate(inputStream);
    }

    public static PKCS10CertificationRequest csrBase64ToPKC10Object(String csrBase64) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

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

        try (ByteArrayInputStream pemStream = new ByteArrayInputStream(pem.getBytes("UTF-8"))) {
            Reader pemReader = new BufferedReader(new InputStreamReader(pemStream));
            PEMParser pemParser = new PEMParser(pemReader);

            try {
                Object parsedObj = pemParser.readObject();

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
        if (chain != null) {
            full = new String[chain.length + 1];
            full[0] = convertToTrustedPem(certificate);
            for (int i = 0; i < chain.length; i++) {
                full[i + 1] = formatCrtFileContents(chain[i]);
            }
        } else {
            full = new String[1];
            full[0] = formatCrtFileContents(certificate);
        }
        return full;
    }

    public static Certificate[] trustChainStringToCertArray(String trustChainString) {
        String[] lines = trustChainString.split("\\r?\\n");

        List<Certificate> trustChains = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        for (String line : lines) {
            if (line.contains(END_CERT)) {
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
            } else {
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

    private static String removeHeaderFooter(String b64, String remove) {
        //todo fix this, not working

        BufferedReader in = new BufferedReader(new StringReader(b64));
        StringBuilder builder = new StringBuilder();

        try {
            List<String> lines = new LinkedList<>();
            String str;
            while ((str = in.readLine()) != null) {
                if (!str.contains("-----BEGIN") && !str.contains("-----END")) {
                    builder.append(str).append("\n");
                    lines.add(str);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        /*
        if (b64.contains("-----BEGIN")) {
            b64 = b64.replaceAll("-----BEGIN " + remove + "-----\r\n", "");
            b64 = b64.replaceAll("\r\n-----END " + remove + "-----", "");
            b64 = b64.replaceAll("-----BEGIN " + remove + "-----\n", "");
            b64 = b64.replaceAll("\n-----END " + remove + "-----", "");
        }

         */
        return builder.toString();
    }

    public static PKCS10CertificationRequest generatePKCS10(String dn, PublicKey publicKey, PrivateKey privateKey)
            throws Exception {
        // generate PKCS10 certificate request
        PKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(
                new X500Principal(dn), publicKey);
        JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder("SHA256withRSA");
        ContentSigner signer = csBuilder.build(privateKey);
        PKCS10CertificationRequest csr = p10Builder.build(signer);
        return csr;
    }

    public static X509Certificate generateSelfSignedCertificate(final KeyPair keyPair,
                                           final String hashAlgorithm,
                                           final String dn,
                                           final int days)
            throws OperatorCreationException, CertificateException, CertIOException
    {
        final Instant now = Instant.now();
        final Date notBefore = Date.from(now);
        final Date notAfter = Date.from(now.plus(Duration.ofDays(days)));

        final ContentSigner contentSigner = new JcaContentSignerBuilder(hashAlgorithm).build(keyPair.getPrivate());
        final X500Name x500Name = new X500Name(dn);
        final X509v3CertificateBuilder certificateBuilder =
                new JcaX509v3CertificateBuilder(x500Name,
                        BigInteger.valueOf(now.toEpochMilli()),
                        notBefore,
                        notAfter,
                        x500Name,
                        keyPair.getPublic())
                        .addExtension(Extension.subjectKeyIdentifier, false, createSubjectKeyId(keyPair.getPublic()))
                        .addExtension(Extension.authorityKeyIdentifier, false, createAuthorityKeyId(keyPair.getPublic()))
                        .addExtension(Extension.basicConstraints, true, new BasicConstraints(true));

        return new JcaX509CertificateConverter()
                .setProvider(new BouncyCastleProvider()).getCertificate(certificateBuilder.build(contentSigner));
    }

    private static SubjectKeyIdentifier createSubjectKeyId(final PublicKey publicKey) throws OperatorCreationException {
        final SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
        final DigestCalculator digCalc =
                new BcDigestCalculatorProvider().get(new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1));

        return new X509ExtensionUtils(digCalc).createSubjectKeyIdentifier(publicKeyInfo);
    }

    /**
     * Creates the hash value of the authority public key.
     *
     * @param publicKey of the authority certificate
     *
     * @return AuthorityKeyIdentifier hash
     *
     * @throws OperatorCreationException
     */
    private static AuthorityKeyIdentifier createAuthorityKeyId(final PublicKey publicKey)
            throws OperatorCreationException {
        final SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
        final DigestCalculator digCalc =
                new BcDigestCalculatorProvider().get(new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1));

        return new X509ExtensionUtils(digCalc).createAuthorityKeyIdentifier(publicKeyInfo);
    }

    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    public static String toPEM(Object key) {
        StringWriter sw = new StringWriter();
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(sw);
        try {
            jcaPEMWriter.writeObject(key);
            jcaPEMWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static X509Certificate signCSR(PKCS10CertificationRequest csr, SubjectAltNames subjectAltNames, int validity,
                                    KeyStore keystore, String alias, char[] password) throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());

            PrivateKey cakey = (PrivateKey) keystore.getKey(alias, password);
            Certificate[] chain = keystore.getCertificateChain(alias);
            Certificate root = chain[1];
            X509Certificate intermediate = (X509Certificate) chain[0];

            AlgorithmIdentifier sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find("SHA256withRSA");
            AlgorithmIdentifier digAlgId = new DefaultDigestAlgorithmIdentifierFinder().find(sigAlgId);
            X500Name issuer = new JcaX509CertificateHolder(intermediate).getSubject();
            BigInteger serial = new BigInteger(16, new SecureRandom());
            Date from = new Date();
            Date to = new Date(System.currentTimeMillis() + (validity * 86400000L));


            X509v3CertificateBuilder certgen = new X509v3CertificateBuilder(issuer, serial, from, to,
                    new X500Name("cn=" + "test"), csr.getSubjectPublicKeyInfo());
            certgen.addExtension(X509Extension.basicConstraints, false, new BasicConstraints(false));
            //certgen.addExtension(X509Extension.subjectKeyIdentifier, false, new SubjectKeyIdentifier(csr.getSubjectPublicKeyInfo()));

            certgen.addExtension(X509Extension.authorityKeyIdentifier, false, new AuthorityKeyIdentifierStructure(intermediate.getPublicKey()));
            List<Integer> keyUsages = new ArrayList<Integer>();

            keyUsages.add(KeyUsage.digitalSignature);

            if (keyUsages.size() == 0) {
                keyUsages.add(KeyUsage.digitalSignature);
            }

            Iterator<Integer> usageIterable = keyUsages.iterator();
            int usageBit = 0;
            while (usageIterable.hasNext()) {
                Integer usage = usageIterable.next();
                usageBit = usageBit | usage;
            }

            certgen.addExtension(X509Extension.keyUsage, false, new KeyUsage(usageBit));
            List<KeyPurposeId> keyPurposeIds = new ArrayList<>();
            keyPurposeIds.add(KeyPurposeId.id_kp_clientAuth);
            keyPurposeIds.add(KeyPurposeId.id_kp_serverAuth);

            if (keyPurposeIds.size() > 0) {
                org.bouncycastle.asn1.x509.ExtendedKeyUsage eku =
                        new org.bouncycastle.asn1.x509.ExtendedKeyUsage(keyPurposeIds.toArray(new KeyPurposeId[0]));
                certgen.addExtension(X509Extension.extendedKeyUsage, false, eku);
            }

            if (subjectAltNames != null) {
                List<GeneralName> generalNameList = new ArrayList<>();
                for (String dns : subjectAltNames.getValuesForType(SubjectAltNames.SubjAltNameType.DNS)) {
                    GeneralName altName = new GeneralName(GeneralName.dNSName, dns);
                    generalNameList.add(altName);
                }

                GeneralNames subjectAltName = new GeneralNames(generalNameList.toArray(new GeneralName[0]));
                certgen.addExtension(X509Extensions.SubjectAlternativeName, false, subjectAltName);
            }

            AsymmetricKeyParameter foo = PrivateKeyFactory.createKey(cakey.getEncoded());
            ContentSigner signer = new BcRSAContentSignerBuilder(sigAlgId, digAlgId).build(foo);

            X509CertificateHolder holder = certgen.build(signer);
            byte[] certencoded = holder.toASN1Structure().getEncoded();

            CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
            signer = new JcaContentSignerBuilder("SHA256withRSA").build(cakey);
            generator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().build()).build(signer, intermediate));
            generator.addCertificate(new X509CertificateHolder(certencoded));
            generator.addCertificate(new X509CertificateHolder(intermediate.getEncoded()));
            generator.addCertificate(new X509CertificateHolder(root.getEncoded()));
            CMSTypedData content = new CMSProcessableByteArray(certencoded);
            CMSSignedData signeddata = generator.generate(content, true);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write("-----BEGIN PKCS #7 SIGNED DATA-----\n".getBytes("ISO-8859-1"));
            out.write(org.bouncycastle.util.encoders.Base64.encode(signeddata.getEncoded()));
            out.write("\n-----END PKCS #7 SIGNED DATA-----\n".getBytes("ISO-8859-1"));
            out.close();
            //return new String(out.toByteArray(), "ISO-8859-1");
            X509CertificateHolder x509CertificateHolder = new X509CertificateHolder(certencoded);
            X509Certificate issuedCert = new JcaX509CertificateConverter().setProvider("BC")
                    .getCertificate(x509CertificateHolder);
            //internal store only
            return issuedCert;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
