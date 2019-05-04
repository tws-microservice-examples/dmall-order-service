package com.dmall.order.service;

import com.dmall.order.factory.OrderCommandDTO;
import com.dmall.order.model.Order;
import com.dmall.order.model.OrderEvent;
import org.springframework.stereotype.Service;

@Service
public interface OrderApplicationService {

  Order submitOrder(OrderCommandDTO orderCommandDTO);

  void postEvent(Long orderId, OrderEvent orderEvent);
}
