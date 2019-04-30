package com.dmall.order.service;

import com.dmall.order.core.Page;
import com.dmall.order.core.Pageable;
import com.dmall.order.model.OrderItem;
import com.dmall.order.model.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
