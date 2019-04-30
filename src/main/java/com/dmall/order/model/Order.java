package com.dmall.order.model;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order {

    private Long id;

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    private String contactId;

    private List<OrderItem> orderItems = new ArrayList<>();

    private ZonedDateTime createdDate = ZonedDateTime.now();

    public List<OrderEvent> getOrderEvents() {
        return orderEvents;
    }

    private List<OrderEvent> orderEvents = new ArrayList<>();

    public Order() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public List<OrderItem> getOrderItems() {
        return orderItems;
    }


}
