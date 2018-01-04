package com.dmall.order.domain.model.query;

import com.dmall.order.domain.common.DomainEntity;
import com.dmall.order.z.debug.spike.domain.model.Contact;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CONTACT_ID")
    private Contact customerContact;

    @Transient
    private List<OrderItemRead> orderItemReads = new ArrayList<>();

    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Transient
    private List<OrderEventRead> orderEventReads = new ArrayList<>();

    public OrderBrief() {
    }


    public void apply(List<OrderEventRead> orderEventReads) {
        this.orderEventReads = orderEventReads;
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

    public Contact getCustomerContact() {
        return customerContact;
    }

    public OrderStatus getStatus(){
        OrderEventRead orderEventRead = this.orderEventReads.get(orderEventReads.size() - 1);

        return OrderStatus.getByOrderEvent(orderEventRead);
    }


    public void setOrderItemReads(List<OrderItemRead> orderItemReads) {
        this.orderItemReads = orderItemReads;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }
}
