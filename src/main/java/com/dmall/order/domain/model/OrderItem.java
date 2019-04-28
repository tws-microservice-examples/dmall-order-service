package com.dmall.order.domain.model;

import com.dmall.order.domain.common.ValueObject;



public class OrderItem implements ValueObject<OrderItem> {


    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setSkuSnapShot(SkuSnapShot skuSnapShot) {
        this.skuSnapShot = skuSnapShot;
    }

    public Integer getAmount() {
        return amount;
    }

    private Integer amount;

    public SkuSnapShot getSkuSnapShot() {
        return skuSnapShot;
    }

    private SkuSnapShot skuSnapShot;

    public OrderItem() {
    }

    public boolean sameValueAs(OrderItem other) {
        return other.skuSnapShot.getSkuId().equals(skuSnapShot.getSkuId()) && other.amount.equals(this.amount);
    }


}
