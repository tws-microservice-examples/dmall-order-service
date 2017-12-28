package com.dmall.order.domain.model;

import com.dmall.order.domain.common.DomainEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity(name = "jx_order")
public class Order implements DomainEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
//    private CustomerContact customerContact;

//    private List<OrderEvent> orderEvents = new ArrayList<>();
//    private List<OrderItem> orderItems = new ArrayList<>();

    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    public Order() {
    }

//    public Order(CustomerContact customerContact, List<OrderItem> orderItems) {
//        this.customerContact = customerContact;
//        this.orderItems = orderItems;
//
//    }

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

//    public CustomerContact getCustomerContact() {
//        return customerContact;
//    }
//
//    public void setCustomerContact(CustomerContact customerContact) {
//        this.customerContact = customerContact;
//    }

//    public String getOrderStatus() {
//        return orderEvents.get(orderEvents.size() - 1).name();
//    }
//
//
//    public void completed() {
//        this.orderEvents.add(OrderEvent.COMPLETED);
//    }
//
//    public void canceled() {
//        this.orderEvents.add(OrderEvent.Cancled);
//    }

}
