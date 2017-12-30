package com.dmall.order.z.debug.infrastructure.persistent;

import com.dmall.order.domain.model.CustomerContact;
import org.springframework.data.repository.CrudRepository;

public interface CustomerContactRepositoryDebug extends CrudRepository<CustomerContact, Long> {
}
