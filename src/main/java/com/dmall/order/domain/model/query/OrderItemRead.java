package com.dmall.order.domain.model.query;

import com.dmall.order.domain.common.ValueObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name="jx_order_item")
@Entity
public class OrderItemRead implements ValueObject<OrderItemRead> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
//
//    private Long skuSnapshotId;
//    private double price;

    private Integer amount;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private OrderBrief order;

    @OneToOne(cascade=CascadeType.ALL, mappedBy = "orderItemRead",fetch = FetchType.EAGER)
    private SkuSnapShot skuSnapShot;


    public OrderItemRead() {
    }

//    public OrderItem(Integer amount, double price, String skuId) {
//        this.amount = amount;
//    }
//
    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean sameValueAs(OrderItemRead other) {
        return other.id.equals(id);
    }

    public SkuSnapShot getSkuSnapShot() {
        return skuSnapShot;
    }
}
