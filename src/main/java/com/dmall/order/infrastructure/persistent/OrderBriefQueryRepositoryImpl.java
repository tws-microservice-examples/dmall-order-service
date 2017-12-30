package com.dmall.order.infrastructure.persistent;


import com.dmall.order.domain.model.query.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderBriefQueryRepositoryImpl extends OrderBriefQueryRepository, JpaRepository<OrderBrief, Long> {


    @Override
    @Query("select oe from OrderEvent oe where oe.order.id =?1")
    List<OrderEvent> findAllOrderEventsByOrderId(Long id);

    @Override
    @Query("select oe from OrderItemRead oe where oe.order.id =?1 ORDER BY oe.id DESC")
    Page<OrderItemRead> findOrderItemsByOrderId(Long id, Pageable pageable);

}
