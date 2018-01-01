package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.OrderEvent;
import com.dmall.order.domain.model.OrderEventRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderEventRepositoryImpl extends OrderEventRepository, CrudRepository<OrderEvent, Long> {
}
