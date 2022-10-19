package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.Account;
import com.winllc.acme.common.domain.BaseAccountEntity;
import com.winllc.acme.common.domain.BaseServerEntryEntity;
import com.winllc.acme.common.domain.ServerEntry;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseServerEntryRepository<T extends BaseServerEntryEntity> extends BaseAccountRepository<T> {
    List<T> findAllByServerEntry(ServerEntry account);
}
