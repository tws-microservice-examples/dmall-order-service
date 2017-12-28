package com.dmall.order.apis;

import com.dmall.order.apis.common.ApiForRequest;
import com.dmall.order.apis.common.ApiForResponse;
import com.dmall.order.apis.common.HttpFacadeBaseClass;
import com.dmall.order.apis.dto.OrderDTO;
import com.dmall.order.apis.dto.OrderSimpleDTO;
import com.dmall.order.domain.model.OrderRepository;
import com.dmall.order.domain.model.query.Order;
import com.dmall.order.domain.model.query.OrderEvent;
import com.dmall.order.domain.model.query.OrderQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends HttpFacadeBaseClass {

    private OrderQueryRepository orderQueryRepository;


    @Autowired
    public OrderController(OrderQueryRepository orderRepository) {
        this.orderQueryRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public final ApiForResponse<Order> findById(@PathVariable("id") final long id) {
        Order order = orderQueryRepository.findOne(id);
        List<OrderEvent> orderEvents = orderQueryRepository.findAllOrderEventsByOrderId(id);
        order.apply(orderEvents);

        //orika
        ApiForResponse<Order> orderApiForResponse = new ApiForResponse<>(order.getId(), order);
        return orderApiForResponse;
    }

//    @GetMapping("/{id}/events")
//    public final ApiForResponse<OrderEvent> findEventsById(@PathVariable("id") final long id) {
//        List<OrderEvent> orderEvents = orderQueryRepository.findAllOrderEventsByOrderId(id);
//
//        //orika
//        ApiForResponse<OrderEvent> orderApiForResponse = new ApiForResponse<>(1L, orderEvents.get(0));
//        return orderApiForResponse;
//    }
}

