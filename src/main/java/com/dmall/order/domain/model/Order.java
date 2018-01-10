package com.dmall.order.domain.model;

import com.dmall.order.domain.common.DomainEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @OneToOne(cascade=CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private CustomerContact customerContact;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @OneToMany(cascade=CascadeType.ALL, mappedBy="order")
    private List<OrderEvent> orderEvents = new ArrayList<>();

    //TODO: 不应该有setter，尤其值对象，一旦创建了不应该修改。因为对象是核心，而不是数据库
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

    public boolean hasMoreThanOneSkuInOneOrder() {
        return getOrderItems().stream()
                .anyMatch(orderItem -> getOrderItems().stream()
                        .filter(anyOrderItem -> anyOrderItem.getSkuSnapShot().getSkuId().equals(orderItem.getSkuSnapShot().getSkuId()))
                        .collect(Collectors.toList()).size()
                        > 1);
    }
}
