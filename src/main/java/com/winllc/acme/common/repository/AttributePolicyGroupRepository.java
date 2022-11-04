package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.Account;
import com.winllc.acme.common.domain.AttributePolicyGroup;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AttributePolicyGroupRepository extends PagingRepository<AttributePolicyGroup> {

    List<AttributePolicyGroup> findAllByAccount(Account account);
    Optional<AttributePolicyGroup> findDistinctByName(String name);
}
