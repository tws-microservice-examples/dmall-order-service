package com.dmall.order.domain.order;

public interface OrderRepository {
    Order findOne(Long id);

    Order save(Order order);
}
