package com.dmall.order.factory;

import com.dmall.order.model.Order;
import com.dmall.order.model.OrderEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    public Order createNewOrderEntity(OrderCommandDTO orderCommandDTO){

        Order result = new Order();
        result.setContactId(orderCommandDTO.getCustomerContactId());

        result.setOrderItems(orderCommandDTO.getOrderItems());


        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setName(OrderEvent.Values.CREATED.name());
        result.addEvent(orderEvent);
        return result;
    }
}
