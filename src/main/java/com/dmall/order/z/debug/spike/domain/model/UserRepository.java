package com.dmall.order.z.debug.spike.domain.model;

public interface UserRepository {
    User findOne(Long id);

    User save(User user);
}
