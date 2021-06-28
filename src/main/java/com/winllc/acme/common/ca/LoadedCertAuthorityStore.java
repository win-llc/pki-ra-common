package com.winllc.acme.common.ca;

import com.winllc.acme.common.domain.CertAuthorityConnectionInfo;
import com.winllc.acme.common.keystore.ApplicationKeystore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.ldap.LdapName;
import java.lang.reflect.Constructor;
import java.security.KeyStore;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LoadedCertAuthorityStore implements ApplicationContextAware {

    private static final Logger log = LogManager.getLogger(LoadedCertAuthorityStore.class);

    private final Map<String, CertAuthority> loadedCertAuthorities = new ConcurrentHashMap<>();
    private ApplicationKeystore applicationKeystore;
    private ApplicationContext applicationContext;

    public LoadedCertAuthorityStore(ApplicationKeystore applicationKeystore, ApplicationContext applicationContext){
        this.applicationKeystore = applicationKeystore;
        setApplicationContext(applicationContext);
    }

    //Build CA object for use
    public void loadCertAuthority(CertAuthorityConnectionInfo info) {
        Optional<CertAuthority> optionalCertAuthority = buildCertAuthority(info);
        if (optionalCertAuthority.isPresent()) {
            CertAuthority ca = optionalCertAuthority.get();

            try {
                ca.getTrustChain();
            } catch (Exception e) {
                log.error("Could not get trust chain for: "+info.getName(), e);
            }

            loadedCertAuthorities.put(ca.getName(), ca);
        }
    }

    public void unloadCertAuthority(String name){
        loadedCertAuthorities.remove(name);
    }

    public CertAuthority getLoadedCertAuthority(String name){
        return loadedCertAuthorities.get(name);
    }

    public Optional<CertAuthority> getLoadedCertAuthorityByIssuerDN(Principal principal)
            throws InvalidNameException {
        Name ldapName = new LdapName(principal.getName());
        return getAllCertAuthorities().stream()
                .filter(c -> {
                    try {
                        return c.getIssuerName().equals(ldapName);
                    } catch (Exception e) {
                        log.error("Could not get issuer by DN: "+ldapName);
                    }
                    return false;
                }).findFirst();
    }

    public void addLoadedCertAuthority(CertAuthority ca){
        loadedCertAuthorities.put(ca.getName(), ca);
    }


    private Optional<CertAuthority> buildCertAuthority(CertAuthorityConnectionInfo info) {

            try {
                String certAuthorityClassName = info.getCertAuthorityClassName();
                Class<?> clazz = Class.forName(certAuthorityClassName);
                Constructor<?> ctor = clazz.getConstructor(CertAuthorityConnectionInfo.class, ApplicationContext.class,
                        KeyStore.class, String.class);
                Object object = ctor.newInstance(new Object[] { info, applicationContext, applicationKeystore.getKeyStore(),
                        applicationKeystore.getKeystorePassword() });

                if(object instanceof CertAuthority){
                    CertAuthority ca = (CertAuthority) object;
                    return Optional.of(ca);
                }else{
                    log.error("Could not load cert authority, not a CertAuthority" + info.getName());
                }
            }catch (Exception e){
                log.error(e);
            }

        return Optional.empty();
    }

    public List<CertAuthority> getAllCertAuthorities(){
        return new ArrayList<>(loadedCertAuthorities.values());
    }

    public Map<String, CertAuthority> getLoadedCertAuthorities() {
        return loadedCertAuthorities;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
