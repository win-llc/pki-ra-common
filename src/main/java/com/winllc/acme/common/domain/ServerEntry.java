package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Transient;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.util.CollectionUtils;

import javax.naming.Name;
import javax.persistence.*;
import java.util.*;

@Entity
@Entry(objectClasses = {"top", "untypedObject"})
@Table(name = "serverentry")
@Getter
@Setter
public class ServerEntry extends BaseAccountEntity implements AuthCredentialHolderInteface {

    //allow pre-authz tracking per account

    @org.springframework.ldap.odm.annotations.Id
    @Transient
    @javax.persistence.Transient
    @JsonIgnore
    private Name dn;
    @Transient
    private String hostname;
    @Attribute(name = "cn")
    @EntityVariableField
    private String fqdn;
    @Column(name = "distinguished_name")
    private String distinguishedName;
    @JsonIgnore
    @ElementCollection
    @Transient
    private List<String> alternateDnsValues = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="domainParent_fk")
    @Transient
    private Domain domainParent;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="account_fk")
    @Transient
    private Account account;
    @JsonIgnore
    @OneToMany(mappedBy = "serverEntry")
    @Transient
    private Set<CertificateRequest> certificateRequests;
    @JsonIgnore
    @OneToMany(mappedBy = "serverEntry")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @Transient
    private Set<AuthCredential> authCredentials;
    @Transient
    private String openidClientId;
    @Transient
    private String openidClientRedirectUrl;
    @Transient
    private Boolean acmeAllowPreAuthz;

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "serverEntry", fetch = FetchType.LAZY)
    private Set<PocEntry> managedBy;


    public static ServerEntry buildNew(){
        ServerEntry serverEntry = new ServerEntry();
        serverEntry.setUuid(UUID.randomUUID().toString());
        serverEntry.setCreationDate(new Date());
        return serverEntry;
    }

    @PreRemove
    private void preRemove(){
        if(domainParent != null){
            setDomainParent(null);
        }

        if(account != null){
            Set<ServerEntry> serverEntries = account.getServerEntries();
            if(!CollectionUtils.isEmpty(serverEntries)){
                account.getServerEntries().remove(this);
            }
        }

        if(!CollectionUtils.isEmpty(certificateRequests)){
            for(CertificateRequest request : certificateRequests){
                request.setServerEntry(null);
            }
        }

        if(!CollectionUtils.isEmpty(authCredentials)){
            for(AuthCredential credential : authCredentials){
                credential.setServerEntry(null);
            }
        }

        if(!CollectionUtils.isEmpty(managedBy)){
            for(PocEntry pocEntry : managedBy){
                pocEntry.setServerEntry(null);
            }
        }
    }


    @Override
    public Optional<AuthCredential> getLatestAuthCredential() {
        return getAuthCredentials().stream()
                .sorted()
                .findFirst();
    }

    public void setAuthCredentials(Set<AuthCredential> authCredentials) {
        this.authCredentials = authCredentials;
    }

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    @Override
    public Account getOwnerAccount() {
        return getAccount();
    }

    @JsonIgnore
    public Name buildDn(String baseDn){
        LdapNameBuilder builder;
        if(StringUtils.isNotBlank(baseDn)){
            builder = LdapNameBuilder.newInstance(baseDn);
        }else{
            builder = LdapNameBuilder.newInstance();
        }

        this.dn = builder
                .add("cn", fqdn)
                .build();
        this.distinguishedName = this.dn.toString();
        return builder.build();
    }

    @Override
    public String toString() {
        return "ServerEntry{" +
                "hostname='" + hostname + '\'' +
                ", fqdn='" + fqdn + '\'' +
                ", openidClientId='" + openidClientId + '\'' +
                "} " + super.toString();
    }
}
