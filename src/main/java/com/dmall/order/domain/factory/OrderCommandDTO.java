package com.dmall.order.domain.factory;


import com.dmall.order.domain.model.CustomerContact;
import com.dmall.order.domain.model.OrderIte;

import java.util.List;

public class OrderCommandDTO {

    public List<OrderIte> getOrderItes() {
        return orderItes;
    }

    public void setOrderItes(List<OrderIte> orderItes) {
        this.orderItes = orderItes;
    }

    List<OrderIte> orderItes;

    private CustomerContact customerContact;


    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }
}
