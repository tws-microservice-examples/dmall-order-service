package com.dmall.order.service;

import com.dmall.order.factory.OrderCommandDTO;
import com.dmall.order.factory.OrderFactory;
import com.dmall.order.model.Order;
import com.dmall.order.model.OrderEvent;
import com.dmall.order.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderCommandService {
    private OrderRepository orderRepository;
    private OrderFactory orderFactory;

    @Autowired
    public OrderCommandService(OrderRepository orderRepository,
                               OrderFactory orderFactory) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;

    }


    public Order submitOrder(OrderCommandDTO orderCommandDTO){

        Order result = new Order();
        result.setContactId(orderCommandDTO.getCustomerContactId());

        result.setOrderItems(orderCommandDTO.getOrderItems());


        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setName(OrderEvent.Values.CREATED.name());
        result.addEvent(orderEvent);
        Order order = result;
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
        order.addEvent(orderEvent);
        orderRepository.save(order);
    }
}
