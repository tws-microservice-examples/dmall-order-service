package com.dmall.order.domain.model.query;


import com.dmall.order.domain.model.OrderItem;
import com.dmall.order.domain.model.query.Order;
import com.dmall.order.domain.model.query.OrderEvent;

import java.util.List;

public interface OrderQueryRepository {
    Order findOne(Long id);

    List<OrderEvent> findAllOrderEventsByOrderId(Long id);

}