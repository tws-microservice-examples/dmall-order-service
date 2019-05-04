package com.dmall.order.apis.dto;

import com.dmall.order.model.Contract;
import com.dmall.order.model.OrderStatus;
import com.dmall.order.model.query.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderWithoutItemsDTO {
    @JsonIgnore
    private Long id;

    private Contract customerContact;

    private String createdDate;

    private OrderStatus status;

    public String getOrderItems() {
        return orderItems;
    }

    private String orderItems;

    public Contract getCustomerContact() {
        return customerContact;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public OrderStatus getStatus() {
        return status;
    }



    public OrderWithoutItemsDTO(OrderBrief order) {
        this.id = order.getId();
        this.createdDate = order.getCreatedDate().toString();
        this.status = order.getStatus();

    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }
}
