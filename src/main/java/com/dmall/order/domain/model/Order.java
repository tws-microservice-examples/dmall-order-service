package com.dmall.order.domain.model;

import com.dmall.order.domain.common.DomainEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "jx_order")
@Entity
public class Order implements DomainEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }

    public void setOrderItes(List<OrderIte> orderItes) {
        this.orderItes = orderItes;
    }

    public void setOrderEvents(List<OrderEvent> orderEvents) {
        this.orderEvents = orderEvents;
    }

    @OneToOne(cascade=CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private CustomerContact customerContact;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="order")
    private List<OrderIte> orderItes = new ArrayList<>();

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


    public List<OrderIte> getOrderItes() {
        return orderItes;
    }

//    public CustomerContact getCustomerContact() {
//        return customerContact;
//    }



}
