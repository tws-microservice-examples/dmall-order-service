package com.dmall.order.z.debug.spike.apis;

import com.dmall.order.apis.common.ApiForResponse;
import com.dmall.order.apis.common.HttpFacadeBaseClass;
import com.dmall.order.z.debug.spike.apis.dto.UserDTO;
import com.dmall.order.z.debug.spike.domain.model.Role;
import com.dmall.order.z.debug.spike.domain.model.User;
import com.dmall.order.z.debug.spike.domain.model.UserRepository;
import com.dmall.order.z.debug.spike.infrastructure.persistent.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends HttpFacadeBaseClass {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Autowired
    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Transactional
    @PostMapping(path="",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiForResponse<UserDTO> createOrder(@RequestBody User user) {
        List<Role> roles = user.getRoles().stream().map(role -> {
            role.setUser(user);
            return role;
        }).collect(Collectors.toList());

        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO(user);


        ApiForResponse<UserDTO> orderApiForResponse = new ApiForResponse<>(savedUser.getId(), userDTO);
        return orderApiForResponse;
    }

    @Transactional
    @GetMapping("/{id}")
    public  ApiForResponse<UserDTO> findUserById(@PathVariable("id") final long id) {

        User user = userRepository.findOne(id);
        UserDTO userDTO = new UserDTO(user);



        ApiForResponse<UserDTO> orderApiForResponse = new ApiForResponse<>(user.getId(), userDTO);
        return orderApiForResponse;
    }

}

