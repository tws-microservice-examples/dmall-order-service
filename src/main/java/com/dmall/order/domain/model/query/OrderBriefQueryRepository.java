package com.dmall.order.domain.model.query;



import com.dmall.order.domain.core.Page;
import com.dmall.order.domain.core.Pageable;
import com.dmall.order.domain.model.OrderItem;

import java.util.List;

public interface OrderBriefQueryRepository {

    Page<OrderItem> findOrderItemsByOrderId(Long id, Pageable pageable);

    OrderBrief findOne(Long id);

}