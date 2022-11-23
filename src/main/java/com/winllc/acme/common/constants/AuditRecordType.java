package com.winllc.acme.common.constants;

import lombok.Getter;

@Getter
public enum AuditRecordType {
    CERTIFICATE_ISSUED("/certificate"),
    CERTIFICATE_REVOKED("/certificate"),
    SERVER_ENTRY_ADDED("/serverEntry"),
    SERVER_ENTRY_UPDATED("/serverEntry"),
    SERVER_ENTRY_REMOVED("/serverEntry"),
    ACCOUNT_ADDED("/account"),
    ACCOUNT_POCS_UPDATED("/account"),
    ACCOUNT_REMOVED("/account"),
    DOMAIN_LINK_TO_ACCOUNT_REQUEST_CREATED("/linkToDomain/view"),
    DOMAIN_LINK_TO_ACCOUNT_REQUEST_APPROVED("/linkToDomain/view"),
    DOMAIN_LINK_TO_ACCOUNT_REQUEST_REJECTED("/linkToDomain/view"),
    OPENID_ENABLED(null),
    OPENID_DISABLED(null);

    private final String uiBasePath;

    AuditRecordType(String uiBasePath){
        this.uiBasePath = uiBasePath;
    }
}
