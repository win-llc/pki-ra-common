package com.winllc.acme.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.ldap.odm.annotations.Transient;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "managedserver")
@Getter
@Setter
public class ManagedServer extends BaseEntity {

    @Column(unique = true)
    private String uniqueId;
    @Column
    private String fqdn;
    @Column
    private String serverEntryId;
    @Column
    private String domain;
    @Column
    private String project;
    @Column
    private String projectId;
    @Column
    private LocalDateTime latestCertIssuedOn;
    @Column
    private LocalDateTime latestCertExpiresOn;
    @Column
    private String latestCertIssuer;
    @Column
    private String latestCertSerial;
    @Column
    private String latestCertSubject;

    public ManagedServer(String fqdn, String projectId) {
        this.fqdn = fqdn;
        this.projectId = projectId;
        this.uniqueId = fqdn+"_"+projectId;
    }

    public ManagedServer() {

    }


}
