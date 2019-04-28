package com.dmall.order.domain.model.query;

import com.dmall.order.domain.common.DomainEntity;
import com.dmall.order.domain.model.OrderEvent;
import com.dmall.order.domain.model.OrderItem;
import com.dmall.order.domain.model.OrderStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderBrief implements DomainEntity<Long> {
    private Long id;


    private List<OrderItem> orderItemReads = new ArrayList<>();

    private ZonedDateTime createdDate = ZonedDateTime.now();

    private List<OrderEvent> orderEvents = new ArrayList<>();

    public OrderBrief() {
    }


    public void apply(List<OrderEvent> orderEvents) {
        this.orderEvents = orderEvents;
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


    public List<OrderItem> getOrderItemReads() {
        return orderItemReads;
    }


    public OrderStatus getStatus(){
        OrderEvent orderEvent = this.orderEvents.get(orderEvents.size() - 1);

        return OrderStatus.getByOrderEvent(orderEvent);
    }


    public void setOrderItemReads(List<OrderItem> orderItemReads) {
        this.orderItemReads = orderItemReads;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }
}
