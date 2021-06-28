package com.winllc.acme.common.keystore;

import com.winllc.acme.common.config.ApplicationKeystoreProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ApplicationKeystore implements InitializingBean {
    //todo properly save keystore as file when changes made

    private static final Logger log = LogManager.getLogger(ApplicationKeystore.class);

    private final ApplicationKeystoreProperties configuration;

    private KeyStore ks;

    public ApplicationKeystore(ApplicationKeystoreProperties configuration) {
        this.configuration = configuration;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        load();
    }

    private void load() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        //Resource resource = resourceLoader.getResource(keystoreLocation);
        //InputStream input = resource.getInputStream();

        InputStream is = new FileInputStream(configuration.getLocation());

        ks = KeyStore.getInstance(configuration.getType());
        ks.load(is, configuration.getPassword().toCharArray());
    }

    private void persist(){
        try(FileOutputStream fos = new FileOutputStream(configuration.getLocation());){
            ks.store(fos, configuration.getPassword().toCharArray());
        } catch (Exception e) {
            log.error("Could not save keystore", e);
        }
    }

    public void addKey(KeyEntryWrapper keyEntryWrapper) throws KeyStoreException {
        ks.setKeyEntry(keyEntryWrapper.getAlias(), keyEntryWrapper.getKey(), configuration.getPassword().toCharArray(),
                new Certificate[]{keyEntryWrapper.getCertificate()});
        persist();
    }

    public void addCertificate(KeyEntryWrapper keyEntryWrapper) throws Exception {
        //ks.setCertificateEntry(keyEntryWrapper.getAlias(), keyEntryWrapper.getCertificate());

        KeyEntryWrapper currentKeyEntry = getKeyEntry(keyEntryWrapper.getAlias());
        ks.setKeyEntry(keyEntryWrapper.getAlias(), currentKeyEntry.getKey(),
                configuration.getPassword().toCharArray(), new Certificate[]{keyEntryWrapper.getCertificate()});
        persist();
    }

    public void addChain(KeyEntryWrapper keyEntryWrapper){
        for(int i = 0; i < keyEntryWrapper.getChain().length; i++){
            Certificate chainEntry = keyEntryWrapper.getChain()[i];
            try {
                ks.setCertificateEntry(keyEntryWrapper.getAlias()+"_chain"+i, chainEntry);
            } catch (KeyStoreException e) {
                log.error("Could not add chain", e);
            }
        }
        persist();
    }

    public void addEntry(KeyEntryWrapper keyEntryWrapper) throws KeyStoreException {
        if(keyEntryWrapper.getKey() != null){
            addKey(keyEntryWrapper);
        }

        if(keyEntryWrapper.getChain() != null){
            addChain(keyEntryWrapper);
        }
    }

    public void deleteKey(String alias) throws KeyStoreException {
        ks.deleteEntry(alias);
        persist();
    }

    public Key getKey(String alias) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        return ks.getKey(alias, configuration.getPassword().toCharArray());
    }

    public Certificate getCertificate(String alias) throws KeyStoreException {
        return ks.getCertificate(alias);
    }

    public KeyEntryWrapper getKeyEntry(String alias) throws Exception {
        KeyEntryWrapper keyEntryWrapper = new KeyEntryWrapper();
        try {
            Key key = getKey(alias);
            keyEntryWrapper.setKey(key);
        } catch (Exception e) {
            log.error("Could not get key entry: "+alias);
        }

        try {
            Certificate cert = getCertificate(alias);
            keyEntryWrapper.setCertificate(cert);
        } catch (Exception e) {
            log.error("Could not get cert entry: "+alias);
        }

        try {
            Certificate[] chain = ks.getCertificateChain(alias);
            keyEntryWrapper.setChain(chain);
        } catch (KeyStoreException e) {
            log.error("Could not get chain: "+alias);
        }

        //Don't return if nothing found
        if(keyEntryWrapper.getKey() == null && keyEntryWrapper.getCertificate() == null &&
            keyEntryWrapper.getChain() == null) throw new Exception("Could not find any entries for: "+alias);

        keyEntryWrapper.setAlias(alias);
        return keyEntryWrapper;
    }

    public List<String> getAllAliases() throws KeyStoreException {
        List<String> list = new ArrayList<>();
        Enumeration<String> aliases = ks.aliases();
        while(aliases.hasMoreElements()){
            String alias = aliases.nextElement();
            list.add(alias);
        }
        return list;
    }

    public KeyStore getKeyStore(){
        return ks;
    }

    public String getKeystorePassword() {
        return configuration.getPassword();
    }
}
