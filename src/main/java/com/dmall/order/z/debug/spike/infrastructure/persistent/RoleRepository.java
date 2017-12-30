package com.dmall.order.z.debug.spike.infrastructure.persistent;

import com.dmall.order.z.debug.spike.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
