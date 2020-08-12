package com.winllc.acme.common.model.data;

import com.winllc.acme.common.util.SecurityUtil;

import java.util.*;

public abstract class DataObject<T> {
    private String id;
    private T object;
    private String accountId;
    private String directory;
    private String transactionId;

    protected DataObject(){}

    public DataObject(T obj) {
        this.id = SecurityUtil.generateRandomString(10);
        this.object = obj;
        this.directory = "";
    }

    public DataObject(T obj, String directory) {
        this.id = SecurityUtil.generateRandomString(10);
        this.object = obj;
        this.directory = directory;
    }

    public DataObject(T obj, String directory, String accountId) {
        this.id = SecurityUtil.generateRandomString(10);
        this.object = obj;
        this.accountId = accountId;
        this.directory = directory;
    }

    public abstract String buildUrl(String baseUrl);

    protected String buildBaseUrl(String baseURL){
        return baseURL + directory + "/";
    }

    public T getObject(){
        return this.object;
    }

    public DataObject<T> updateObject(T object){
        this.object = object;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDirectory() {
        return directory;
    }

    public <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
        if (c == null)
            return Collections.emptyList();
        List<T> list = new ArrayList<T>(c);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size())
            pageSize = list.size();
        int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
        List<List<T>> pages = new ArrayList<>(numPages);
        for (int pageNum = 0; pageNum < numPages;)
            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        return pages;
    }

    public void addTransactionId(UUID uuid){
        this.transactionId = uuid.toString();
    }

    public UUID getTransactionIdAsUUID(){
        if(this.transactionId != null){
            return UUID.fromString(this.transactionId);
        }else{
            return null;
        }
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "id='" + id + '\'' +
                ", object=" + object +
                ", accountId='" + accountId + '\'' +
                ", directory='" + directory + '\'' +
                '}';
    }
}
