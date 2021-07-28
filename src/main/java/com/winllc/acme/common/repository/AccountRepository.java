package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.Account;
import com.winllc.acme.common.domain.PocEntry;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends UniqueEntityRepository<Account> {

    List<Account> findAll();
    Optional<Account> findDistinctByProjectName(String projectName);
    //Optional<Account> findByKeyIdentifierEquals(String kid);
    List<Account> findAllByPocsIn(Collection<PocEntry> pocEntries);
    List<Account> findAllByPocsContaining(PocEntry poc);
    List<Account> findAllByPocsEmailEquals(String email);
    void deleteByKeyIdentifierEquals(String kid);
}
