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

    @OneToOne(cascade=CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private CustomerContact customerContact;

    @Transient
    private List<OrderItemRead> orderItemReads = new ArrayList<>();

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


    public List<OrderItemRead> getOrderItemReads() {
        return orderItemReads;
    }

    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public OrderStatus getStatus(){
        OrderEvent orderEvent = this.orderEvents.get(orderEvents.size() - 1);

        return OrderStatus.getByOrderEvent(orderEvent);
    }


    public void setOrderItemReads(List<OrderItemRead> orderItemReads) {
        this.orderItemReads = orderItemReads;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }
}
