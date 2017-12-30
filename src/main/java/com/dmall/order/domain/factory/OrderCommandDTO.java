package com.dmall.order.domain.factory;


import com.dmall.order.domain.model.CustomerContact;
import com.dmall.order.domain.model.OrderItem;

import java.util.List;

public class OrderCommandDTO {

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    List<OrderItem> orderItems;

    private CustomerContact customerContact;


    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }
}
