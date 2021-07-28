package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.LdapSchemaOverlay;
import com.winllc.acme.common.domain.LdapSchemaOverlayAttribute;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LdapSchemaOverlayAttributeRepository extends BaseRepository<LdapSchemaOverlayAttribute> {
    List<LdapSchemaOverlayAttribute> findAllByLdapSchemaOverlay(LdapSchemaOverlay overlay);
}
