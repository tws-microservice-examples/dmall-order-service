package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.CustomerContact;
import com.dmall.order.domain.model.query.OrderBrief;
import com.dmall.order.domain.model.query.OrderBriefQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerContactRepositoryImpl extends CrudRepository<CustomerContact, Long> {
}
