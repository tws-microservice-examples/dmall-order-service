package com.dmall.order.factory;


import com.dmall.order.model.OrderItem;

import java.util.List;

public class OrderCommandDTO {

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    List<OrderItem> orderItems;

    private String customerContactId;


    public String getCustomerContactId() {
        return customerContactId;
    }

    public void setCustomerContactId(String customerContactId) {
        this.customerContactId = customerContactId;
    }
}
