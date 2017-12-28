package com.dmall.order.domain.service;

import com.dmall.order.domain.model.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        List<OrderEvent> orderEvents = orderBriefQueryRepository.findAllOrderEventsByOrderId(id);
        one.apply(orderEvents);

        return one;
    }

    public OrderBrief findOrderBriefWithDetailById(Long id) {
        OrderBrief one = orderBriefQueryRepository.findOne(id);
        List<OrderEvent> orderEvents = orderBriefQueryRepository.findAllOrderEventsByOrderId(id);
        one.apply(orderEvents);

        Pageable pageable = new PageRequest(0,2);
        Page<OrderItem> orderItemsPage = orderBriefQueryRepository.findOrderItemsByOrderId(one.getId(), pageable);
        one.setOrderItems(orderItemsPage.getContent());
        return one;
    }
}
