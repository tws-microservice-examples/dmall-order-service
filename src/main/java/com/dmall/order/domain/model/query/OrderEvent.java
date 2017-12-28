package com.dmall.order.domain.model.query;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name="jx_order_event")
@Entity
public class OrderEvent {

    enum Values{
        CREATED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String name;

    private Long ticketId;

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
