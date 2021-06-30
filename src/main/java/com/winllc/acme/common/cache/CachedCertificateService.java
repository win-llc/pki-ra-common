package com.winllc.acme.common.cache;

import com.winllc.acme.common.CertSearchParam;
import com.winllc.acme.common.CertSearchParams;
import com.winllc.acme.common.CertificateDetails;
import com.winllc.acme.common.ca.CachedCertificate;
import com.winllc.acme.common.util.CertUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.elasticsearch.join.query.JoinQueryBuilders.hasChildQuery;

public class CachedCertificateService {
    
    private final ElasticsearchOperations operations;

    public CachedCertificateService(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    private QueryBuilder certSearchParamTo(CertSearchParam param){
        QueryBuilder queryBuilder = null;

        switch (param.getField()) {
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
            case STATUS:
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("status", param.getValue());
                        break;
                }
                break;
            case VALID_ON:
                LocalDateTime valueAsLocalDateTime = param.getValueAsLocalDateTime();
                Date from = Date.from(valueAsLocalDateTime.toInstant(ZoneOffset.UTC));
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("validFrom", from);
                        break;
                    case GREATER_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("validFrom").gt(from);
                        break;
                    case LESS_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("validFrom").lt(from);
                        break;
                    case BETWEEN:
                        queryBuilder = QueryBuilders.rangeQuery("validFrom")
                            .from(param.getBetweenFrom())
                                .to(param.getBetweenTo());
                        break;
                }
                break;
            case EXPIRES_ON:
                LocalDateTime vdt = param.getValueAsLocalDateTime();
                Date to = Date.from(vdt.toInstant(ZoneOffset.UTC));
                switch (param.getRelation()){
                    case EQUALS:
                        queryBuilder = QueryBuilders.matchQuery("validTo", to);
                        break;
                    case GREATER_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("validTo").gt(to);
                        break;
                    case LESS_THAN:
                        queryBuilder = QueryBuilders.rangeQuery("validTo").lt(to);
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

    public List<CachedCertificate> search(CertSearchParam parentParam){
        return search(parentParam, null, -1);
    }

    public List<CachedCertificate> search(CertSearchParam parentParam, FieldSortBuilder sortBuilder, int maxResults){

        QueryBuilder aggregator = aggregator(parentParam);

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(aggregator);

        if(sortBuilder != null){
            searchQueryBuilder = searchQueryBuilder.withSort(sortBuilder);
        }

        if(maxResults > 0) {
            searchQueryBuilder = searchQueryBuilder.withMaxResults(maxResults);
        }

        try {
            SearchHits<CachedCertificate> search = operations.search(searchQueryBuilder.build(), CachedCertificate.class);
            return search.stream()
                    .map(h -> h.getContent())
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>();
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

    public void persist(X509Certificate certificate) throws CertificateEncodingException {
        CachedCertificate cached = new CachedCertificate(certificate, "VALID");
        operations.save(cached);
    }

    public void persist(X509Certificate certificate, String status) throws CertificateEncodingException {
        CachedCertificate cached = new CachedCertificate(certificate, status);
        operations.save(cached);
    }

    public void update(List<CachedCertificate> updated){
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
