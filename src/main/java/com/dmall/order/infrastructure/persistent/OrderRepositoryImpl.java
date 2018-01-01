package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderEvent;
import com.dmall.order.domain.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import javax.xml.ws.soap.Addressing;

@Transactional
public interface OrderRepositoryImpl extends OrderRepository, CrudRepository<Order, Long> {

}
