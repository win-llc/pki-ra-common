package com.winllc.acme.common.repository;

import com.winllc.acme.common.domain.AccountRequest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AccountRequestRepository extends BaseRepository<AccountRequest> {

    List<AccountRequest> findAllByStateEquals(String state);
    Integer countAllByStateEquals(String state);
    List<AccountRequest> findAllByRequestedByEmailEquals(String email);
}
