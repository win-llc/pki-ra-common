package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.BaseEntity;
import com.winllc.acme.common.domain.ManagedServer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface PagingRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

    List<T> findAll();
}
