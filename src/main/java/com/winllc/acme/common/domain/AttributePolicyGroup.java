package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attributepolicygroup")
public class AttributePolicyGroup extends BaseEntity {

    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "attributePolicyGroup")
    private Set<AttributePolicy> attributePolicies;
    @ManyToOne
    @JoinColumn(name="account_fk")
    private Account account;
    @ManyToOne
    @JoinColumn(name="ldapSchemaOverlay_fk")
    private LdapSchemaOverlay ldapSchemaOverlay;

    @PreRemove
    private void preRemove(){
        if(account != null && !CollectionUtils.isEmpty(account.getPolicyGroups())){
            account.getPolicyGroups().remove(this);
        }

        if(attributePolicies != null) {
            for (AttributePolicy attributePolicy : attributePolicies) {
                attributePolicy.setAttributePolicyGroup(null);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LdapSchemaOverlay getLdapSchemaOverlay() {
        return ldapSchemaOverlay;
    }

    public void setLdapSchemaOverlay(LdapSchemaOverlay ldapSchemaOverlay) {
        this.ldapSchemaOverlay = ldapSchemaOverlay;
    }

    public Set<AttributePolicy> getAttributePolicies() {
        if(attributePolicies == null) attributePolicies = new HashSet<>();
        return attributePolicies;
    }

    public void setAttributePolicies(Set<AttributePolicy> attributePolicies) {
        this.attributePolicies = attributePolicies;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
