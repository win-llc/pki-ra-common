package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.EstServerProperties;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EstServerPropertiesRepository extends BaseRepository<EstServerProperties> {
    EstServerProperties findByName(String name);
}
