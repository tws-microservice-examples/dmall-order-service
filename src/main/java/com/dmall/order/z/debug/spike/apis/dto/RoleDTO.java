package com.dmall.order.z.debug.spike.apis.dto;

import com.dmall.order.z.debug.spike.domain.model.Role;

public class RoleDTO {

    private final String name;

    public RoleDTO(Role role) {
        this.name = role.getName();
    }

    public String getName() {
        return name;
    }
}
