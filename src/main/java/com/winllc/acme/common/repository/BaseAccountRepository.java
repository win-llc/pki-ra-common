package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.Account;
import com.winllc.acme.common.domain.BaseAccountEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseAccountRepository<T extends BaseAccountEntity> extends BaseRepository<T> {
    List<T> findAllByAccount(Account account);
}
