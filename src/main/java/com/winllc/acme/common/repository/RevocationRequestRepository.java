package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.RevocationRequest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RevocationRequestRepository extends PagingRepository<RevocationRequest> {

    Optional<RevocationRequest> findDistinctByIssuerDnAndSerial(String issuer, String serial);
    List<RevocationRequest> findAllByStatus(String status);
}
