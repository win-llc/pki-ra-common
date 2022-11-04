package com.winllc.acme.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Document
@Getter
@Setter
public class CertificateAuthoritySettings extends SettingsDocument {

    private String type;
    private String name;
    private String issuerDn;
    private String mapsToCaConnectionName;
    private String baseUrl;
    private List<AdditionalSetting> additionalSettings = new ArrayList<>();

    public Optional<AdditionalSetting> getAdditionalSettingByKey(String key){
        for(AdditionalSetting additionalSetting : additionalSettings){
            if(additionalSetting.getName().contentEquals(key)) return Optional.of(additionalSetting);
        }
        return Optional.empty();
    }
}
