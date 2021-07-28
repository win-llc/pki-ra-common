package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.Account;
import com.winllc.acme.common.domain.ServerEntry;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ServerEntryRepository extends UniqueEntityRepository<ServerEntry> {

    Optional<ServerEntry> findDistinctByFqdnEquals(String fqdn);
    List<ServerEntry> findAllByAccount(Account account);
    List<ServerEntry> findAllByAccountId(Long id);
    Optional<ServerEntry> findDistinctByFqdnEqualsAndAccountEquals(String fqdn, Account account);
    Optional<ServerEntry> findDistinctByDistinguishedNameIgnoreCaseAndAccount(String distinguishedName, Account account);
}
