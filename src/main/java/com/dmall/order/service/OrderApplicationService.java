package com.dmall.order.service;

import com.dmall.order.factory.OrderCommandDTO;
import com.dmall.order.model.Order;
import com.dmall.order.model.OrderEvent;
import org.springframework.stereotype.Service;

@Service
public interface OrderApplicationService {
  //TODO: 思考题：application service 和domain service职责上怎么划分
  //       Order如果复杂化会怎么复杂？
  Order submitOrder(OrderCommandDTO orderCommandDTO);

  void postEvent(Long orderId, OrderEvent orderEvent);
}
