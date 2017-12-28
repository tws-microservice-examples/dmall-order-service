package com.dmall.order.domain.model.query;

import com.dmall.order.domain.common.DomainEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity(name = "jx_order")
public class Order implements DomainEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Customer_Contact_ID")
    private CustomerContact customerContact;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Transient
    private List<OrderEvent> orderEvents = new ArrayList<>();

    public Order() {
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


}
