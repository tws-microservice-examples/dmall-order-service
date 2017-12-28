package com.dmall.order.domain.model.query;

import com.dmall.order.domain.common.DomainEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="jx_order")
@Entity
public class OrderBrief implements DomainEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Customer_Contact_ID")
    private CustomerContact customerContact;

    @Transient
    private List<OrderItem> orderItems = new ArrayList<>();

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Transient
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


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public OrderStatus getStatus(){
        OrderEvent orderEvent = this.orderEvents.get(orderEvents.size() - 1);

        return OrderStatus.getByOrderEvent(orderEvent);
    }


    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
