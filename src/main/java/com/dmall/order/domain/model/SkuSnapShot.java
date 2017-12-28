package com.dmall.order.domain.model;

import com.dmall.order.domain.common.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class SkuSnapShot implements ValueObject<SkuSnapShot> {
    private String skuId;
    private double price;
    //

    public SkuSnapShot(String skuId, double price) {
        this.skuId = skuId;
        this.price = price;
    }

    public String getSkuId() {
        return skuId;
    }
    
    //

    @Override
    public boolean sameValueAs(SkuSnapShot other) {
        return other.skuId.equals(skuId) && other.price == price;
    }
}
