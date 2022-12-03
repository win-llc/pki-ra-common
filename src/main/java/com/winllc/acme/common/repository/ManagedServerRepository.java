package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.ManagedServer;
import com.winllc.acme.common.domain.ServerSettings;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ManagedServerRepository extends PagingRepository<ManagedServer> {

    Optional<ManagedServer> findOneByUniqueId(String uid);
}
