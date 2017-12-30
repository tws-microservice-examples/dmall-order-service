package com.dmall.order.domain.model;

public interface UserRepository {
    User findOne(Long id);

    User save(User user);
}
