package com.winllc.acme.common.model.data;

import com.winllc.acme.common.model.acme.Order;

public class OrderData extends DataObject<Order> {

    public OrderData(Order object, String directory, String accountId) {
        super(object, directory, accountId);
    }

    @Override
    public String buildUrl(String baseURL) {
        return buildBaseUrl(baseURL) + "order/" + getId();
    }

    @Override
    public String toString() {
        return "OrderData{} " + super.toString();
    }
}
