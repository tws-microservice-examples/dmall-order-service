package com.dmall.order.domain.model.query;


import java.util.List;

public interface OrderQueryRepository {
    Order findOne(Long id);

    List<OrderEventRead> findAllOrderEventsByOrderId(Long id);

}