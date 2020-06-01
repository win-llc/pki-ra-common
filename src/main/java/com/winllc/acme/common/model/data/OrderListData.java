package com.winllc.acme.common.model.data;

import com.winllc.acme.common.model.acme.OrderList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//Section 7.1.2.1
public class OrderListData extends DataObject<OrderList> {
    private static final int pageSize = 10;

    public OrderListData(OrderList object, String directory) {
        super(object, directory);
    }

    public void addOrder(String baseURL, OrderData order){
        List<String> list = Arrays.asList(getObject().getOrders());
        List<String> temp = new ArrayList<>(list);
        temp.add(order.buildUrl(baseURL));
        getObject().setOrders(temp.toArray(new String[0]));
    }

    @Override
    public String buildUrl(String baseURL) {
        return buildBaseUrl(baseURL) + "orders/" + getId();
    }

    //e.g. Link: <https://example.com/acme/orders/rzGoeA?cursor=2>;rel="next"
    public Optional<String> buildPaginatedLink(String baseURL, int currentRequestedPage){
        //only include rel=next if there are more pages
        int numberOfPages = getNumberOfPages() - 1;
        if(numberOfPages == currentRequestedPage){
            return Optional.empty();
        }

        return Optional.of("<" + buildUrl(baseURL) + "?cursor=" + (currentRequestedPage + 1) + ">;rel=\"next\"");
    }


    public int getNumberOfPages(){
        return getPages(Arrays.asList(getObject().getOrders()), pageSize).size();
    }

    public OrderList buildPaginatedOrderList(int page){
        String[] orders = getObject().getOrders();
        List<List<String>> pages = getPages(Arrays.asList(orders), pageSize);
        if(pages.size() > page){
            OrderList orderList = new OrderList();
            orderList.setOrders(pages.get(page).toArray(new String[0]));
            return orderList;
        }else{
            throw new ArrayIndexOutOfBoundsException("Bad page");
        }
    }

    @Override
    public String toString() {
        return "OrderListData{} " + super.toString();
    }
}
