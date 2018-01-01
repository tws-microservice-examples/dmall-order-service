package com.dmall.order.z.debug.spike.apis;

import com.dmall.order.apis.common.ApiForResponse;
import com.dmall.order.apis.common.HttpFacadeBaseClass;
import com.dmall.order.z.debug.spike.apis.dto.UserDTO;
import com.dmall.order.z.debug.spike.domain.model.*;
import com.dmall.order.z.debug.spike.infrastructure.persistent.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

//TODO: 思考题： User Domain怎么拆出去
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends HttpFacadeBaseClass {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ContactRepository contactRepository;


    @Autowired
    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.contactRepository = contactRepository;
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


        ApiForResponse<UserDTO> userApiForResponse = new ApiForResponse<>(savedUser.getId(), userDTO);
        return userApiForResponse;
    }

    @Transactional
    @GetMapping("/{id}")
    public  ApiForResponse<UserDTO> findUserById(@PathVariable("id") final long id) {

        User user = userRepository.findOne(id);
        UserDTO userDTO = new UserDTO(user);

        ApiForResponse<UserDTO> userApiForResponse = new ApiForResponse<>(user.getId(), userDTO);
        return userApiForResponse;
    }


    @Transactional
    @PostMapping(path="/{id}/contacts",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiForResponse<Contact> createOrder(@PathVariable("id") final long userId, @RequestBody Contact contact) {

        Contact result = contactRepository.save(contact);

        ApiForResponse<Contact> contactApiForResponse = new ApiForResponse<>(-1L, result);
        return contactApiForResponse;
    }

}

