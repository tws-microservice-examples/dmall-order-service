package com.dmall.order.domain.service;

import com.dmall.order.domain.core.Page;
import com.dmall.order.domain.core.Pageable;
import com.dmall.order.domain.model.OrderItem;
import com.dmall.order.domain.model.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryService {
    OrderBriefQueryRepository orderBriefQueryRepository;

    @Autowired
    public OrderQueryService(OrderBriefQueryRepository orderBriefQueryRepository) {
        this.orderBriefQueryRepository = orderBriefQueryRepository;
    }

    public OrderBrief findOrderBriefById(Long id){

        OrderBrief one = orderBriefQueryRepository.findOne(id);
        return one;
    }

    public OrderBrief findOrderBriefWithDetailById(Long id) {
        OrderBrief one = orderBriefQueryRepository.findOne(id);
        Pageable pageable = new Pageable(0,2);
        Page<OrderItem> orderItemsPage = orderBriefQueryRepository.findOrderItemsByOrderId(one.getId(), pageable);
        one.setOrderItemReads(orderItemsPage.getResults());
        return one;
    }

    public Page<OrderItem> findAllItemsByOrder(Long id, Pageable pageable) {
        Page<OrderItem> orderItemsPage = orderBriefQueryRepository.findOrderItemsByOrderId(id, pageable);

        return orderItemsPage;
    }
}
