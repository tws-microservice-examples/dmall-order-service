package com.dmall.order.domain.model;

import com.dmall.order.domain.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name="jx_order_event")
@Entity
public class OrderEvent {

    public enum Values{
        CREATED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private Long ticketId;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    public String getName() {
        return name;
    }

    public Long getTicketId() {
        return ticketId;
    }

}

