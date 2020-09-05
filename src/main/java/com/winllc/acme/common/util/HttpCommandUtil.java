package com.winllc.acme.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.PrivateKeyDetails;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Map;
import java.util.function.Function;

public class HttpCommandUtil {

    private static final Logger log = LogManager.getLogger(HttpCommandUtil.class);

    public static <T> T process(HttpRequestBase request, int successCode, Class<T> returnClass) throws Exception {

        Function<String, T> jsonResultProcess = (content) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            if (StringUtils.isNotBlank(content)) {
                try {
                    return objectMapper.readValue(content, returnClass);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        };

        return processCustom(request, successCode, jsonResultProcess);
    }

    public static <T> T processCustom(HttpRequestBase request, int successCode, Function<String, T> func) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();

        return processCustomGeneric(httpclient, request, successCode, func);
    }

    public static <T> T processCustomWithClientAuth(HttpRequestBase request, int successCode, Function<String, T> func,
                                                    KeyStore keyStore, String keyStorePassword, String alias)
            throws IOException, HttpException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException {
        HttpClient httpclient = buildClientCertAuthentication(keyStore, keyStorePassword, alias);

        return processCustomGeneric(httpclient, request, successCode, func);
    }

    private static <T> T processCustomGeneric(HttpClient httpClient, HttpRequestBase request,
                                              int successCode, Function<String, T> func) throws HttpException, IOException {
        try {
            //Execute and get the response.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            String result = "";
            if (entity != null) result = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8.name());

            if (response.getStatusLine().getStatusCode() == successCode) {
                return func.apply(result);
            } else {
                throw new HttpException("Expected code: " + successCode + ", received: " + response.getStatusLine().getStatusCode());
            }
        } finally {
            request.completed();
            HttpClientUtils.closeQuietly(httpClient);
        }
    }

    public static <T> T processCustomJsonPost(String url, Object entity, int successCode, Function<String, T> func) throws Exception {
        HttpPost httppost = new HttpPost(url);
        ObjectMapper objectMapper = new ObjectMapper();
        String val = objectMapper.writeValueAsString(entity);
        StringEntity jsonEntity = new StringEntity(val);

        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Content-type", "application/json");
        httppost.setEntity(jsonEntity);

        return processCustom(httppost, successCode, func);
    }

    private static HttpClient buildClientCertAuthentication(KeyStore keyStore, String password, String alias)
            throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        PrivateKeyStrategy pks = new PrivateKeyStrategy() {
            @Override
            public String chooseAlias(Map<String, PrivateKeyDetails> aliases, Socket socket) {
                if(aliases.containsKey(alias)){
                    return alias;
                }else{
                    log.error("Could not find keystore alias: "+alias);
                    return null;
                }
            }
        };

        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore,
                        password.toCharArray(), pks)
                .loadTrustMaterial(null, new TrustAllStrategy())
                .build();

        SSLConnectionSocketFactory sslConnectionFactory =
                new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslConnectionFactory)
                .register("http", new PlainConnectionSocketFactory())
                .build();

        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(registry);

        HttpClient client = HttpClients.custom()
                .setConnectionManager(connManager)
                .setSSLSocketFactory(sslConnectionFactory)
                .setSSLHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .build();

        return client;
    }
}
