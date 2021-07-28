package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.Account;
import com.winllc.acme.common.domain.PocEntry;
import com.winllc.acme.common.domain.ServerEntry;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PocEntryRepository extends BaseRepository<PocEntry> {
    List<PocEntry> findAllByAccount(Account account);
    List<PocEntry> findAllByEmailEquals(String email);
    Optional<PocEntry> findDistinctByEmailEqualsAndAccount(String email, Account account);
    void deleteAllByEmailInAndAccountEquals(List<String> emails, Account account);
    void deleteByEmailEqualsAndAccount(String email, Account account);

    List<PocEntry> findAllByManagesContaining(ServerEntry serverEntry);
}
