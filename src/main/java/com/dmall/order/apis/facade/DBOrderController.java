package com.dmall.order.apis.facade;

import com.dmall.order.apis.common.ApiForRequest;
import com.dmall.order.apis.common.ApiForResponse;
import com.dmall.order.apis.dto.OrderDTO;
import com.dmall.order.domain.order.Order;
import com.dmall.order.domain.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping("/api/orders")
public class DBOrderController extends HttpFacadeBaseClass {

    private OrderRepository orderRepository;


    @Autowired
    public DBOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public final ApiForResponse<Order> findById(@PathVariable("id") final long id) {
        Order order = orderRepository.findOne(id);
        ApiForResponse<Order> orderApiForResponse = new ApiForResponse<>(order.getId(), order);
        return orderApiForResponse;
    }

    @PostMapping
    public final ResponseEntity createOrder(@RequestBody final ApiForRequest<OrderDTO> req) {
        OrderDTO attributes = req.getAttributes();
        Order order = new Order(attributes.getCustomer(),
                attributes.getShop(),
                attributes.getPet());

        orderRepository.save(order);

        return buildResponseEntity(create(format("/api/orders/%d", order.getId())), HttpStatus.CREATED);
    }

}

