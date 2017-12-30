package com.dmall.order.infrastructure.persistent;

import com.dmall.order.domain.model.Role;
import com.dmall.order.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
