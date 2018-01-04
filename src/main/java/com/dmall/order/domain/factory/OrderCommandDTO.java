package com.dmall.order.domain.factory;


import com.dmall.order.domain.model.OrderItem;
import com.dmall.order.z.debug.spike.domain.model.Contact;

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
