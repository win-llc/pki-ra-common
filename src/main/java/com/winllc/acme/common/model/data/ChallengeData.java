package com.winllc.acme.common.model.data;

import com.winllc.acme.common.model.acme.Challenge;

public class ChallengeData extends DataObject<Challenge> {

    private String authorizationId;

    public ChallengeData(Challenge object, String directory) {
        super(object, directory);
    }

    //public ChallengeData(Challenge object, DirectoryData directoryData){
    //    super(object, directoryData.getName());
    //}

    @Override
    public String buildUrl(String baseURL) {
        return buildBaseUrl(baseURL) + "chall/" + getId();
    }

    public String getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }

    @Override
    public String toString() {
        return "ChallengeData{" +
                "authorizationId='" + authorizationId + '\'' +
                "} " + super.toString();
    }
}
