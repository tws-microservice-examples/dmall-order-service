package com.dmall.order.domain.service;

import com.dmall.order.domain.factory.OrderCommandDTO;
import com.dmall.order.domain.factory.OrderFactory;
import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderEvent;
import com.dmall.order.domain.model.OrderEventRepository;
import com.dmall.order.domain.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderCommandService {
    private OrderRepository orderRepository;
    private OrderFactory orderFactory;
    private OrderEventRepository orderEventRepository;

    @Autowired
    public OrderCommandService(OrderRepository orderRepository,
                               OrderFactory orderFactory,
                               OrderEventRepository orderEventRepository) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderEventRepository = orderEventRepository;
    }


    public Order submitOrder(OrderCommandDTO orderCommandDTO){
        Order order = orderFactory.createNewOrderEntity(orderCommandDTO);
        boolean moreThanOneSkuInOneOrder = order.getOrderItems().stream()
                .anyMatch(orderItem -> order.getOrderItems().stream()
                        .filter(anyOrderItem -> anyOrderItem.getSkuSnapShot().getSkuId().equals(orderItem))
                        .collect(Collectors.toList()).size()
                        > 1);
        if(moreThanOneSkuInOneOrder){
            throw new RuntimeException();
        }
        return orderRepository.save(order);
    }

    public void postEvent(Long orderId, OrderEvent orderEvent) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new RuntimeException();
        }
        orderEvent.setOrder(order);
        orderEventRepository.save(orderEvent);
    }
}
