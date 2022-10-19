package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AuthCredentialRepository extends BaseServerEntryRepository<AuthCredential> {

    Optional<AuthCredential> findDistinctByKeyIdentifier(String kid);

}
