package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.User;
import com.dmall.order.domain.model.UserRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryImpl extends UserRepository, CrudRepository<User, Long> {
}
