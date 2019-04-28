package com.dmall.order.infrastructure.persistent;


import com.dmall.order.domain.core.Page;
import com.dmall.order.domain.core.Pageable;
import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderItem;
import com.dmall.order.domain.model.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderBriefQueryRepositoryImpl implements OrderBriefQueryRepository {

    private OrderRepositoryImpl orderRepository;

    @Autowired
    public OrderBriefQueryRepositoryImpl(OrderRepositoryImpl orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Query("select oe from OrderItemRead oe where oe.order.id =?1 ORDER BY oe.id DESC")
    public Page<OrderItem> findOrderItemsByOrderId(Long id, Pageable pageable) {
        Order theOrder = orderRepository.getOrders().stream().filter(order -> order.getId() == id).findFirst().get();
        List<OrderItem> orderItems = theOrder.getOrderItems();
        return new Page<>(orderItems, pageable.getPage(), pageable.getSize(), orderItems.size());
    }

    @Override
    public OrderBrief findOne(Long id) {
        Order one = orderRepository.findOne(id);

        OrderBrief result = OrderBriefBuilder.anOrderBrief().withId(one.getId()).withOrderItem(one.getOrderItems()).build();
        result.apply(one.getOrderEvents());
        return result;
    }

}
