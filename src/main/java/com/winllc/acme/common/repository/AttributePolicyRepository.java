package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.AttributePolicy;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AttributePolicyRepository extends BaseRepository<AttributePolicy> {
}
