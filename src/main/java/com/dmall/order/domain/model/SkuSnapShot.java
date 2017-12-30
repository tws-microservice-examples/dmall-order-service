package com.dmall.order.domain.model;

import com.dmall.order.domain.common.ValueObject;
import com.dmall.order.domain.model.query.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name="jx_sku_snapshot")
@Entity
public class SkuSnapShot implements ValueObject<SkuSnapShot> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    public Long getSkuId() {
        return skuId;
    }

    public double getPrice() {
        return price;
    }

    private Long skuId;
    private double price;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ORDER_ITEM_ID")
    private OrderItem orderItem;
    //

    public SkuSnapShot(){

    }



    @Override
    public boolean sameValueAs(SkuSnapShot other) {
        return other.skuId.equals(skuId) && other.price == price;
    }
}