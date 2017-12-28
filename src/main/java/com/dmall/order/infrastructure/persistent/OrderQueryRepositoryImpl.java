package com.dmall.order.infrastructure.persistent;


import com.dmall.order.domain.model.query.Order;
import com.dmall.order.domain.model.query.OrderEvent;
import com.dmall.order.domain.model.query.OrderQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderQueryRepositoryImpl extends OrderQueryRepository, JpaRepository<Order, Long> {

    @Override
    @Query("select oe from OrderEvent oe where oe.order.id =?1")
    List<OrderEvent> findAllOrderEventsByOrderId(Long id);
}
