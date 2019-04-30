package com.dmall.order.model.query;



import com.dmall.order.core.Page;
import com.dmall.order.core.Pageable;
import com.dmall.order.model.OrderItem;

public interface OrderBriefQueryRepository {

    Page<OrderItem> findOrderItemsByOrderId(Long id, Pageable pageable);

    OrderBrief findOne(Long id);

}