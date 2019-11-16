package com.winllc.acme.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class HttpCommandUtil {

    public static <T> T process(HttpRequestBase request, int successCode, Class<T> returnClass) throws Exception {

        Function<String, T> jsonResultProcess = (content) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            if(StringUtils.isNotBlank(content)) {
                try {
                    return objectMapper.readValue(content, returnClass);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }else{
                return null;
            }
        };

        return processCustom(request, successCode, jsonResultProcess);
    }

    public static <T> T processCustom(HttpRequestBase request, int successCode, Function<String, T> func) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();

        try {
            //Execute and get the response.
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                if(response.getStatusLine().getStatusCode() == successCode){

                    String result = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8.name());
                    return func.apply(result);
                }else{
                    throw new HttpException();
                }
            }else{
                throw new HttpException();
            }
        }catch (Exception e){
            throw e;
        }finally {
            request.completed();
            HttpClientUtils.closeQuietly(httpclient);
        }
    }
}
