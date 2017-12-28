package com.dmall.order.infrastructure.persistent;


import com.dmall.order.domain.model.query.Order;
import com.dmall.order.domain.model.query.OrderQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderQueryRepositoryImpl extends OrderQueryRepository, JpaRepository<Order, Long> {
}
