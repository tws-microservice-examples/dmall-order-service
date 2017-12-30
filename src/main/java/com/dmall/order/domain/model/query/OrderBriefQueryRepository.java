package com.dmall.order.domain.model.query;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderBriefQueryRepository {

    Page<OrderItemRead> findOrderItemsByOrderId(Long id, Pageable pageable);

    Page<OrderBrief> findAll(Pageable pageable);

    OrderBrief findOne(Long id);

    List<OrderEvent> findAllOrderEventsByOrderId(Long id);
}