package com.winllc.acme.common.model.requestresponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.JWSObject;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AccountRequest {
    //optional
    private String status;
    //optional
    private String[] contact;
    //optional
    private Boolean termsOfServiceAgreed = true;
    //optional
    private Boolean onlyReturnExisting;
    //optional
    @JsonProperty("externalAccountBinding")
    private ExternalAccountBinding externalAccountBinding;

    private String resource;

    public AccountRequest() {
    }


    @JsonIgnore
    public JWSObject buildExternalAccountJWSObject() throws ParseException {
        return JWSObject.parse(externalAccountBinding.toString());
    }


    /*
    public JWSObject buildExternalAccount() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(externalAccountBinding.getPayload().toJSONObject().toJSONString(), JWSObject.class);
    }

     */
}
