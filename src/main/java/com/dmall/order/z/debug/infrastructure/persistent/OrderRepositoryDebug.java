package com.dmall.order.z.debug.infrastructure.persistent;

import com.dmall.order.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepositoryDebug extends CrudRepository<Order, Long> {
}
