package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.AcmeServerConnectionInfo;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AcmeServerConnectionInfoRepository extends BaseRepository<AcmeServerConnectionInfo> {
    AcmeServerConnectionInfo findByName(String name);
}
