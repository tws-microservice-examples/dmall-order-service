package com.dmall.order.domain.model;

import com.dmall.order.domain.common.ValueObject;
import javax.persistence.Embeddable;

@Embeddable
public class OrderItem implements ValueObject<OrderItem> {
    private SkuSnapShot skuSnapShot;
    private Integer amount;

    public OrderItem() {
    }

    public OrderItem(Integer amount, SkuSnapShot skuSnapShot) {
        this.skuSnapShot = skuSnapShot;
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public SkuSnapShot getSkuSnapShot() {
        return skuSnapShot;
    }

    @Override
    public boolean sameValueAs(OrderItem other) {
        return other.skuSnapShot.getSkuId().equals(skuSnapShot.getSkuId())
                && other.amount.equals(amount);
    }


}
