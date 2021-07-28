package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.CertAuthorityType;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CertAuthorityTypeRepository extends BaseRepository<CertAuthorityType> {
    Optional<CertAuthorityType> findDistinctByName(String name);
}
