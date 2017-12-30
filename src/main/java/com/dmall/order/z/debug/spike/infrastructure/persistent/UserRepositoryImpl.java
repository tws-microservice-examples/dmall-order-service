package com.dmall.order.z.debug.spike.infrastructure.persistent;

import com.dmall.order.z.debug.spike.domain.model.User;
import com.dmall.order.z.debug.spike.domain.model.UserRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryImpl extends UserRepository, CrudRepository<User, Long> {
}
