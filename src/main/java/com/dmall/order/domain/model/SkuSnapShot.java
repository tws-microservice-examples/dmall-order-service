package com.dmall.order.domain.model;

import com.dmall.order.domain.common.ValueObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


//TODO: 思考题：这回我就不问该不该合了，我问一个问题，这货跟Product是不是一个东西？这里面有哪些歧义？
@Table(name="jx_sku_snapshot")
@Entity
public class SkuSnapShot implements ValueObject<SkuSnapShot> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSkuId() {
        return skuId;
    }

    private Long skuId;

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

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