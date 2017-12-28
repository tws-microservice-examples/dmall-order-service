package com.dmall.order.domain.model.query;

public interface OrderQueryRepository {
    Order findOne(Long id);

}