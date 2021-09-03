package com.winllc.acme.common.cache;

import com.winllc.acme.common.ca.CachedCertificate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CachedCertificateRepository extends ElasticsearchRepository<CachedCertificate, String> {
}
