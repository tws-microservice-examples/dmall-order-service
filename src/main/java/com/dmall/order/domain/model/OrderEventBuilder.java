package com.dmall.order.domain.model;

public final class OrderEventBuilder {
  private String name;

  private OrderEventBuilder() {
  }

  public static OrderEventBuilder anOrderEvent() {
    return new OrderEventBuilder();
  }

  public OrderEventBuilder withName(String name) {
    this.name = name;
    return this;
  }


  public OrderEvent build() {
    OrderEvent orderEvent = new OrderEvent();
    orderEvent.setName(name);
    return orderEvent;
  }
}
