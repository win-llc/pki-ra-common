package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.AppRole;
import com.winllc.acme.common.domain.EntityPermission;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface EntityPermissionRepository extends BaseRepository<EntityPermission> {
    Optional<EntityPermission> findFirstByEntityNameAndRole(String entityName, AppRole role);
}
