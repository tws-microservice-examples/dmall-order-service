package com.dmall.order.z.debug.infrastructure.persistent;

import com.dmall.order.domain.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepositoryDebug extends CrudRepository<OrderItem, Long> {
}
