package com.dmall.order.z.debug.spike.apis.dto;

import com.dmall.order.z.debug.spike.domain.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<RoleDTO> roles;
    private String name;
    public UserDTO(User user){
        this.name = user.getName();
        this.roles = user.getRoles().stream().map(RoleDTO::new).collect(Collectors.toList());
    }
}
