package com.dmall.order.apis;

import com.dmall.order.apis.common.ApiForResponse;
import com.dmall.order.apis.common.HttpFacadeBaseClass;
import com.dmall.order.apis.dto.OrderCreatedResponseDTO;
import com.dmall.order.apis.dto.UserDTO;
import com.dmall.order.domain.factory.OrderCommandDTO;
import com.dmall.order.apis.dto.OrderWithoutItemsDTO;
import com.dmall.order.domain.factory.OrderFactory;
import com.dmall.order.domain.model.*;
import com.dmall.order.domain.model.query.OrderBrief;
import com.dmall.order.domain.model.query.OrderQueryRepository;
import com.dmall.order.domain.service.OrderQueryService;
import com.dmall.order.infrastructure.persistent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends HttpFacadeBaseClass {

    private OrderQueryRepository orderQueryRepository;
    private OrderQueryService orderQueryService;
    private OrderFactory orderFactory;
    private OrderRepository orderRepository;
    private OrderRepositoryImpl orderRepositoryImpl;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private OrderItemRepositoryImpl orderItemRepository;
    private SkuRepository skuRepository;
    private CustomerContactRepositoryImpl customerContactRepository;


    @Autowired
    public OrderController(OrderQueryRepository orderQueryRepository, OrderQueryService orderQueryService,
                           OrderFactory orderFactory,
                           OrderRepository orderRepository,
                           OrderRepositoryImpl orderRepositoryImpl, UserRepository userRepository,
                           RoleRepository roleRepository,
                           OrderItemRepositoryImpl orderItemRepository, SkuRepository skuRepository, CustomerContactRepositoryImpl customerContactRepository) {
        this.orderQueryRepository = orderQueryRepository;
        this.orderQueryService = orderQueryService;
        this.orderFactory = orderFactory;
        this.orderRepository = orderRepository;
        this.orderRepositoryImpl = orderRepositoryImpl;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.orderItemRepository = orderItemRepository;
        this.skuRepository = skuRepository;
        this.customerContactRepository = customerContactRepository;
    }

    @Transactional
    @GetMapping("/{id}")
    public ApiForResponse<OrderWithoutItemsDTO> findById(@PathVariable("id") final long id) {
        System.out.println("all customerContact");
        customerContactRepository.findAll().forEach(customerContact -> System.out.println("customer contact:"+customerContact.getId()));

        System.out.println("all order,crrent param:"+id);
        orderRepositoryImpl.findAll().forEach(order -> {
            System.out.println("orderId:" + order.getId());
            order.getOrderItes().stream().forEach(orderIte -> System.out.println(orderIte.getAmount()));
        });
        skuRepository.findAll().forEach(skuSnapShot -> System.out.println("skuId: "+skuSnapShot.getSkuId()));

        OrderBrief order = orderQueryService.findOrderBriefById(id);

        OrderWithoutItemsDTO orderWithoutItemsDTO = new OrderWithoutItemsDTO(order);
        orderWithoutItemsDTO.setOrderItems(String.format("/api/v1/orders/%d/orderItems", order.getId()));

        ApiForResponse<OrderWithoutItemsDTO> orderApiForResponse = new ApiForResponse<>(order.getId(), orderWithoutItemsDTO);
        return orderApiForResponse;
    }

    @Transactional
    @PostMapping(path="",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiForResponse<OrderCreatedResponseDTO> createOrder(@RequestBody OrderCommandDTO orderRequest) {

        Order order = orderFactory.createOrderEntity(orderRequest);

        Order savedOrder = orderRepository.save(order);


        Iterable<OrderIte> all = orderItemRepository.findAll();
        all.forEach(a -> System.out.println(a.getOrder().getId()));

        OrderCreatedResponseDTO result = new OrderCreatedResponseDTO();
        result.setUri(String.format("/orders/%d", savedOrder.getId()));
        ApiForResponse<OrderCreatedResponseDTO> orderApiForResponse = new ApiForResponse<>(savedOrder.getId(), result);
        return orderApiForResponse;
    }

    @Transactional
    @PostMapping(path="/users",  produces = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping("/users/{id}")
    public  ApiForResponse<UserDTO> findUserById(@PathVariable("id") final long id) {

        Iterable<Role> all = roleRepository.findAll();
//        all.forEach(role -> System.out.println(role.getUser().getId()));

        User user = userRepository.findOne(id);
        UserDTO userDTO = new UserDTO(user);

//      System.out.println(user.getRoles()
//                .get(0)
//                .getName());

        ApiForResponse<UserDTO> orderApiForResponse = new ApiForResponse<>(user.getId(), userDTO);
        return orderApiForResponse;
    }

}

