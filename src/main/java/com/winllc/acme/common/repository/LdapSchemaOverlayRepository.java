package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.LdapSchemaOverlay;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LdapSchemaOverlayRepository extends BaseRepository<LdapSchemaOverlay> {

}
