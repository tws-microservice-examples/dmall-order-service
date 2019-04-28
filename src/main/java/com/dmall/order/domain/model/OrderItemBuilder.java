package com.dmall.order.domain.model;

public final class OrderItemBuilder {
  private Integer amount;
  private SkuSnapShot skuSnapShot;

  private OrderItemBuilder() {
  }

  public static OrderItemBuilder anOrderItem() {
    return new OrderItemBuilder();
  }

  public OrderItemBuilder withAmount(Integer amount) {
    this.amount = amount;
    return this;
  }


  public OrderItemBuilder withSkuSnapShot(SkuSnapShot skuSnapShot) {
    this.skuSnapShot = skuSnapShot;
    return this;
  }

  public OrderItem build() {
    OrderItem orderItem = new OrderItem();
    orderItem.setAmount(amount);
    orderItem.setSkuSnapShot(skuSnapShot);
    return orderItem;
  }
}
