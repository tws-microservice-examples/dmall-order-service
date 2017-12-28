package com.dmall.order.domain.model;

public interface OrderRepository {
    Order findOne(Long id);

    Order save(Order order);
}
