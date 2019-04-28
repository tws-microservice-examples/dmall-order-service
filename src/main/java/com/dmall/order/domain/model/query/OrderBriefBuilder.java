package com.dmall.order.domain.model.query;

import com.dmall.order.domain.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public final class OrderBriefBuilder {
  private Long id;
  private List<OrderItem> orderItemReads = new ArrayList<>();

  private OrderBriefBuilder() {
  }

  public static OrderBriefBuilder anOrderBrief() {
    return new OrderBriefBuilder();
  }

  public OrderBriefBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public OrderBriefBuilder withOrderItem(List<OrderItem> orderItemReads) {
    this.orderItemReads = orderItemReads;
    return this;
  }

  public OrderBrief build() {
    OrderBrief orderBrief = new OrderBrief();
    orderBrief.setId(id);
    orderBrief.setOrderItemReads(orderItemReads);
    return orderBrief;
  }
}
