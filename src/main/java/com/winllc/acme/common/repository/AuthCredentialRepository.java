package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.AuthCredential;
import com.winllc.acme.common.domain.AuthCredentialHolder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AuthCredentialRepository extends PagingAndSortingRepository<AuthCredential, Long> {
    List<AuthCredential> findAll();

    Optional<AuthCredential> findDistinctByKeyIdentifier(String kid);
    List<AuthCredential> findAllByParentEntity(AuthCredentialHolder holder);
    List<AuthCredential> findAllByParentEntityAndValidEquals(AuthCredentialHolder holder, Boolean valid);
}
