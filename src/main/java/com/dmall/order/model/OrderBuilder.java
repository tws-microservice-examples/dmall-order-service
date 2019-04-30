package com.dmall.order.model;

import java.util.ArrayList;
import java.util.List;

public final class OrderBuilder {
  private Long id;
  private String contactId;
  private List<OrderItem> orderItems = new ArrayList<>();

  private OrderBuilder() {
  }

  public static OrderBuilder anOrder() {
    return new OrderBuilder();
  }

  public OrderBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public OrderBuilder withContactId(String contactId) {
    this.contactId = contactId;
    return this;
  }

  public OrderBuilder withOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
    return this;
  }

  public Order build() {
    Order order = new Order();
    order.setId(id);
    order.setContactId(contactId);
    order.setOrderItems(orderItems);
    return order;
  }
}
