package com.dmall.order.model;

import com.dmall.order.common.ValueObject;



public class SkuSnapShot implements ValueObject<SkuSnapShot> {

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

    public double getPrice() {
        return price;
    }

    private double price;



    public SkuSnapShot(){

    }



    @Override
    public boolean sameValueAs(SkuSnapShot other) {
        return other.skuId.equals(skuId) && other.price == price;
    }
}