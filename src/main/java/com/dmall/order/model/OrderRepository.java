package com.dmall.order.model;

public interface OrderRepository {
    Order findOne(Long id);

    Order save(Order order);
}
