package com.dmall.order.domain.factory;

import com.dmall.order.domain.model.CustomerContact;
import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    public Order createNewOrderEntity(OrderCommandDTO orderCommandDTO){

        Order result = new Order();
        CustomerContact customerContact = orderCommandDTO.getCustomerContact();
        customerContact.setOrder(result);
        result.setCustomerContact(customerContact);

        result.setOrderItes(orderCommandDTO.getOrderItes());
        result.getOrderItes().stream().forEach(orderItem -> {
            orderItem.setOrder(result);
        });

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setName(OrderEvent.Values.CREATED.name());
        orderEvent.setOrder(result);
        result.addEvent(orderEvent);
        return result;
    }
}
