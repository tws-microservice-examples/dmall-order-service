package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryImpl extends OrderRepository, JpaRepository<Order, Long> {
}
