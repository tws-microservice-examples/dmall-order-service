package com.dmall.order.factory;

import com.dmall.order.model.Order;
import com.dmall.order.model.OrderEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFactory {
    public Order createNewOrderEntity(OrderCommandDTO orderCommandDTO){

        Order result = new Order();
        result.setContactId(orderCommandDTO.getCustomerContactId());

        result.setOrderItems(orderCommandDTO.getOrderItems());


        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setName(OrderEvent.Values.CREATED.name());
        addEvent(result, orderEvent);
        return result;
    }

    private void addEvent(Order result, OrderEvent orderEvent) {
        List<OrderEvent> orderEvents = result.getOrderEvents();
        orderEvents.add(orderEvent);
    }

}
