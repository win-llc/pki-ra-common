package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.Account;
import com.winllc.acme.common.domain.Domain;
import com.winllc.acme.common.domain.DomainPolicy;

import java.util.List;
import java.util.Optional;

public interface DomainPolicyRepository extends BaseAccountRepository<DomainPolicy> {
    List<DomainPolicy> findAllByTargetDomainEquals(Domain targetDomain);
    Optional<DomainPolicy> findDistinctByAccountAndTargetDomain(Account account, Domain domain);

    List<DomainPolicy> findAllByAccount(Account account);
}
