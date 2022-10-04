package com.winllc.acme.common.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "managedserver")
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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    public String getServerEntryId() {
        return serverEntryId;
    }

    public void setServerEntryId(String serverEntryId) {
        this.serverEntryId = serverEntryId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getLatestCertIssuedOn() {
        return latestCertIssuedOn;
    }

    public void setLatestCertIssuedOn(LocalDateTime latestCertIssuedOn) {
        this.latestCertIssuedOn = latestCertIssuedOn;
    }

    public LocalDateTime getLatestCertExpiresOn() {
        return latestCertExpiresOn;
    }

    public void setLatestCertExpiresOn(LocalDateTime latestCertExpiresOn) {
        this.latestCertExpiresOn = latestCertExpiresOn;
    }

    public String getLatestCertIssuer() {
        return latestCertIssuer;
    }

    public void setLatestCertIssuer(String latestCertIssuer) {
        this.latestCertIssuer = latestCertIssuer;
    }

    public String getLatestCertSerial() {
        return latestCertSerial;
    }

    public void setLatestCertSerial(String latestCertSerial) {
        this.latestCertSerial = latestCertSerial;
    }

    public String getLatestCertSubject() {
        return latestCertSubject;
    }

    public void setLatestCertSubject(String latestCertSubject) {
        this.latestCertSubject = latestCertSubject;
    }
}
