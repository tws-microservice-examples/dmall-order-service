package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.OrderIte;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrderItemRepositoryImpl extends CrudRepository<OrderIte, Long> {
}
