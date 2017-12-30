package com.dmall.order.z.debug.infrastructure.persistent;

import com.dmall.order.domain.model.OrderEvent;
import org.springframework.data.repository.CrudRepository;

public interface OrderEventRepositoryDebug extends CrudRepository<OrderEvent, Long> {

}
