package com.winllc.acme.common.model.requestresponse;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface RequestValidator {

    @JsonIgnore
    boolean isValid();
}
