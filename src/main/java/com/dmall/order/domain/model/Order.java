package com.dmall.order.domain.model;

import com.dmall.order.domain.common.DomainEntity;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order implements DomainEntity<Long> {

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


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean sameIdentityAs(Long otherId) {
        return this.id.equals(otherId);
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }


    public void addEvent(OrderEvent orderEvent) {
        this.orderEvents.add(orderEvent);
    }

}
