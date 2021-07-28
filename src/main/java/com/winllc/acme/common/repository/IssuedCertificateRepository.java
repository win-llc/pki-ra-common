package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.IssuedCertificate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IssuedCertificateRepository extends BaseRepository<IssuedCertificate> {

}
