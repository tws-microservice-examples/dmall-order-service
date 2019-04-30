package com.dmall.order.infrastructure.persistent;

import com.dmall.order.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

  private long idCounter = 0;

  List<Order> getOrders() {
    return orders;
  }

  private List<Order> orders = new ArrayList<>();

  public OrderRepositoryImpl(){
    List<OrderItem> orderItems = Arrays.asList(
            OrderItemBuilder.anOrderItem().withAmount(1)
                    .withSkuSnapShot(SkuSnapShotBuilder.aSkuSnapShot().withSkuId(3L).withPrice(6).build())
                    .build(),
            OrderItemBuilder.anOrderItem().withAmount(4)
                    .withSkuSnapShot(SkuSnapShotBuilder.aSkuSnapShot().withSkuId(2L).withPrice(4).build())
                    .build());
    long id = ++idCounter;
    Order order = OrderBuilder.anOrder().withOrderItems(orderItems).withContactId("1").withId(id).build();

    order.addEvent(OrderEventBuilder.anOrderEvent().withName(OrderEvent.Values.CREATED.name()).build());

    orders.add(order);
  }

  @Override
  public Order findOne(Long id) {
    return orders.stream().filter(order -> order.getId() == id).findFirst().get();
  }

  @Override
  public Order save(Order order) {
    if(order.getId()==null){
      order.setId(++idCounter);
      orders.add(order);
    } else {
      Order theOrder = orders.stream().filter(existOrder -> existOrder.getId() == order.getId()).findFirst().get();
      int index = orders.indexOf(theOrder);
      orders.set(index, order);
    }
    return order;
  }

}
