package com.winllc.acme.common.repository;


import com.winllc.acme.common.domain.DomainLinkToAccountRequest;

import java.util.List;

public interface DomainLinkToAccountRequestRepository extends PagingRepository<DomainLinkToAccountRequest> {
    List<DomainLinkToAccountRequest> findAllByStatusEquals(String status);
    Integer countAllByStatusEquals(String status);
    List<DomainLinkToAccountRequest> findAllByAccountIdIn(List<Long> accountIds);
    List<DomainLinkToAccountRequest> findAllByAccountId(Long accountId);

}
