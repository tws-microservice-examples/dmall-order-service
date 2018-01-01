package com.dmall.order.domain.model.query;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//TODO: Event要不要合成一个？
@Table(name="jx_order_event")
@Entity
public class OrderEventRead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String name;

    private Long ticketId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private OrderBrief order;

    public String getName() {
        return name;
    }

    public Long getTicketId() {
        return ticketId;
    }

}
