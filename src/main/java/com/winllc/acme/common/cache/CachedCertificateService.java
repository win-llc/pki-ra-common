package com.winllc.acme.common.cache;

import com.winllc.acme.common.ca.CachedCertificate;
import com.winllc.acme.common.util.CertUtil;
import com.winllc.ra.integration.ca.CertSearchParam;
import com.winllc.ra.integration.ca.CertSearchParams;
import com.winllc.ra.integration.ca.CertificateDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class CachedCertificateService {

    private static final Logger log = LogManager.getLogger(CachedCertificateService.class);
    private static final DateTimeFormatter ELASTICSEARCH_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");

    private final ElasticsearchOperations operations;

    public CachedCertificateService(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    private QueryBuilder certSearchParamTo(CertSearchParam param){
        QueryBuilder queryBuilder = null;
        String formattedDateTime;
        ZonedDateTime valueAsLocalDateTime;

        switch (param.getField()) {
            case REVOKED_ON:
                break;
            case SERIAL:
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("serial", param.getValue());
                        break;
                    case GREATER_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("serial")
                                .gt(param.getValue());
                        break;
                    case LESS_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("serial")
                                .lt(param.getValue());
                        break;
                    case CONTAINS:
                        if(param.getValue() instanceof List){
                            List<Long> serials = (List<Long>) param.getValue();
                            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                            for(Long serial : serials){
                                boolQueryBuilder.should().add(QueryBuilders.matchQuery("serial", serial));
                            }
                            queryBuilder = boolQueryBuilder;
                        }
                        break;
                }
                break;
            case ISSUER:
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("issuer", param.getValue());
                        break;
                }
                break;
            case CA_NAME:
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("caName", param.getValue());
                        break;
                }
                break;
            case STATUS:
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("status", param.getValue());
                        break;
                }
                break;
            case VALID_ON:
                valueAsLocalDateTime = param.getValueAsLocalDateTime();
                formattedDateTime = ELASTICSEARCH_DTF.format(valueAsLocalDateTime);
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("validFrom", formattedDateTime);
                        break;
                    case GREATER_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("validFrom").gt(formattedDateTime);
                        break;
                    case LESS_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("validFrom").lt(formattedDateTime);
                        break;
                    case BETWEEN:
                        queryBuilder = QueryBuilders.rangeQuery("validFrom")
                            .from(param.getBetweenFrom())
                                .to(param.getBetweenTo());
                        break;
                }
                break;
            case EXPIRES_ON:
                valueAsLocalDateTime = param.getValueAsLocalDateTime();
                formattedDateTime = ELASTICSEARCH_DTF.format(valueAsLocalDateTime);
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("validTo", formattedDateTime);
                        break;
                    case GREATER_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("validTo").gt(formattedDateTime);
                        break;
                    case LESS_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("validTo").lt(formattedDateTime);
                        break;
                    case BETWEEN:
                        queryBuilder = QueryBuilders.rangeQuery("validTo")
                                .from(param.getBetweenFrom())
                                .to(param.getBetweenTo());
                        break;
                }
                break;
            case SUBJECT:
                switch (param.getRelation()) {
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("dn", param.getValue());
                        break;
                    case CONTAINS:
                        queryBuilder = QueryBuilders.wildcardQuery("dn", "*"+param.getValue()+"*");
                        break;
                }
            }

        return queryBuilder;
    }

    private QueryBuilder aggregator(CertSearchParam parentParam){
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        if(parentParam.isRelational()){
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            List<QueryBuilder> queryList;
            if(parentParam.getRelation() == CertSearchParams.CertSearchParamRelation.AND){
                queryList = boolQueryBuilder.must();
            }else{
                queryList = boolQueryBuilder.should();
            }

            for(CertSearchParam param : parentParam.getParams()){
                QueryBuilder subQuery = aggregator(param);
                queryList.add(subQuery);
            }

            builder = boolQueryBuilder;
        }else{
            builder.must(certSearchParamTo(parentParam));
        }

        return builder;
    }

    public SearchHits<CachedCertificate> search(CertSearchParam parentParam){
        return search(parentParam, null);
    }

    public SearchHits<CachedCertificate> search(CertSearchParam parentParam, FieldSortBuilder sortBuilder, Pageable pageable){
        QueryBuilder aggregator = aggregator(parentParam);

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(aggregator);

        if(sortBuilder != null){
            searchQueryBuilder = searchQueryBuilder.withSort(sortBuilder);
        }

        if(pageable != null){
            searchQueryBuilder = searchQueryBuilder.withPageable(pageable);
        }

        SearchHits<CachedCertificate> search = operations.search(searchQueryBuilder.build(), CachedCertificate.class);
        return search;
    }

    public SearchHits<CachedCertificate> search(CertSearchParam parentParam, FieldSortBuilder sortBuilder){
        return search(parentParam, sortBuilder, null);
    }

    public Optional<CachedCertificate> findById(String id){
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("id", id));

        SearchHits<CachedCertificate> search = operations.search(searchQueryBuilder.build(), CachedCertificate.class);

        return search.stream()
                .map(h -> h.getContent())
                .findFirst();
    }

    public void persist(List<CertificateDetails> details, String caName){
        for(CertificateDetails detail : details){
            try {
                operations.save(searchResultToCached(detail, caName));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void persist(X509Certificate certificate, String status, String caName) throws CertificateEncodingException {
        CachedCertificate cached = new CachedCertificate(certificate, status);
        cached.setCaName(caName);
        operations.save(cached);
        log.debug("Cached certificate: "+cached);
    }

    public void update(List<CachedCertificate> updated){
        operations.save(updated);
    }

    public void update(CachedCertificate updated){
        operations.save(updated);
    }

    private CachedCertificate searchResultToCached(CertificateDetails cert, String caName) throws Exception{
        X509Certificate certificate = CertUtil.base64ToCert(cert.getCertificateBase64());
        CachedCertificate cached = new CachedCertificate(certificate, cert.getStatus());
        cached.setCaName(caName);
        return cached;
    }

    public void delete(){
        //todo
    }
}
