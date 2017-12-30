package com.dmall.order.apis;

import com.dmall.order.apis.common.ApiForResponse;
import com.dmall.order.apis.common.HttpFacadeBaseClass;
import com.dmall.order.apis.dto.OrderCreatedResponseDTO;
import com.dmall.order.apis.services.OrderApplicationService;
import com.dmall.order.domain.factory.OrderCommandDTO;
import com.dmall.order.apis.dto.OrderWithoutItemsDTO;
import com.dmall.order.domain.factory.OrderFactory;
import com.dmall.order.domain.model.*;
import com.dmall.order.domain.model.query.OrderBrief;
import com.dmall.order.domain.model.query.OrderItemRead;
import com.dmall.order.domain.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends HttpFacadeBaseClass {

    private OrderQueryService orderQueryService;
    private OrderFactory orderFactory;
    private OrderRepository orderRepository;
    private OrderApplicationService orderApplicationService;

    @Autowired
    public OrderController(OrderQueryService orderQueryService,
                           OrderFactory orderFactory,
                           OrderRepository orderRepository, OrderApplicationService orderApplicationService) {
        this.orderQueryService = orderQueryService;
        this.orderFactory = orderFactory;
        this.orderRepository = orderRepository;
        this.orderApplicationService = orderApplicationService;
    }

    @Transactional
    @GetMapping("/{id}")
    public ApiForResponse<OrderWithoutItemsDTO> findById(@PathVariable("id") final long id) {


        OrderBrief order = orderQueryService.findOrderBriefById(id);

        OrderWithoutItemsDTO orderWithoutItemsDTO = new OrderWithoutItemsDTO(order);
        orderWithoutItemsDTO.setOrderItems(String.format("/api/v1/orders/%d/orderItems", order.getId()));

        ApiForResponse<OrderWithoutItemsDTO> orderApiForResponse = new ApiForResponse<>(order.getId(), orderWithoutItemsDTO);
        return orderApiForResponse;
    }

    @Transactional
    @PostMapping(path="",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiForResponse<OrderCreatedResponseDTO> createOrder(@RequestBody OrderCommandDTO orderRequest) {
        Order savedOrder = orderApplicationService.submitOrder(orderRequest);


        OrderCreatedResponseDTO result = new OrderCreatedResponseDTO();
        result.setUri(String.format("/orders/%d", savedOrder.getId()));
        ApiForResponse<OrderCreatedResponseDTO> orderApiForResponse = new ApiForResponse<>(savedOrder.getId(), result);
        return orderApiForResponse;
    }


    @Transactional
    @GetMapping("/{id}/orderItems")
    public ApiForResponse<List<OrderItemRead>> findItemsByOrderId(@PathVariable("id") final long id, Pageable pageable) {

        Page<OrderItemRead> orderItems = orderQueryService.findAllItemsByOrder(id, pageable);

        ApiForResponse<List<OrderItemRead>> orderApiForResponse = new ApiForResponse<>(id, orderItems.getContent());
        return orderApiForResponse;
    }
}

