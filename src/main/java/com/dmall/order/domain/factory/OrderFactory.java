package com.dmall.order.domain.factory;

import com.dmall.order.domain.model.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    public Order createOrderEntity(OrderCommandDTO orderCommandDTO){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        System.out.println(gson.toJson(orderCommandDTO).toString());

        Order result = new Order();
        result.setCustomerContact(orderCommandDTO.getCustomerContact());
        result.setOrderItes(orderCommandDTO.getOrderItes());
        result.getOrderItes().stream().forEach(orderItem -> {
            orderItem.setOrder(result);
        });

        result.getOrderItes().stream().forEach(orderItem -> System.out.println(ToStringBuilder.reflectionToString(orderItem)));

        return result;
    }
}
