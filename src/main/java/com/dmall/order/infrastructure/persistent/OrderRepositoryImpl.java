package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrderRepositoryImpl extends OrderRepository, CrudRepository<Order, Long> {
}
