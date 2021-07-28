package com.winllc.acme.common.domain;

import java.util.Set;

public interface DomainCertIssuanceRestrictionHolder {
    Set<DomainPolicy> getDomainIssuanceRestrictions();
}
