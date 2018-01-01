package com.dmall.order.z.debug.spike.infrastructure.persistent;

import com.dmall.order.z.debug.spike.domain.model.Contact;
import com.dmall.order.z.debug.spike.domain.model.ContactRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepositoryImpl extends ContactRepository, CrudRepository<Contact, Long> {
}
