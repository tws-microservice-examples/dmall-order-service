package com.dmall.order.domain.model.query;

import com.dmall.order.domain.common.DomainEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@javax.persistence.Entity(name = "jx_order")
public class Order implements DomainEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Customer_Contact_ID")
    private CustomerContact customerContact;
//    @Enumerated(STRING)
//    private OrderStatus orderStatus = OrderStatus.NOT_COMPLETED;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    public Order() {
    }

    public Order(CustomerContact customerContact, List<Long> orderItemIds) {
//        this.customerContact = customerContact;
//        this.orderItemIds = orderItemIds;

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


//    public OrderStatus getOrderStatus() {
//        return orderStatus;
//    }

    @Override
    public boolean sameIdentityAs(Long otherId) {
        return this.id.equals(otherId);
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }

//    public CustomerContact getCustomerContact() {
//        return customerContact;
//    }

}
