package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.UniqueEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface UniqueEntityRepository<T extends UniqueEntity> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {

    List<T> findAll();
    Optional<T> findDistinctByUuidEquals(String uuid);
}
