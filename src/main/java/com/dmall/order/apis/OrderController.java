package com.dmall.order.apis;

import com.dmall.order.apis.common.ApiForResponse;
import com.dmall.order.apis.common.HttpFacadeBaseClass;
import com.dmall.order.apis.dto.OrderWithoutItemsDTO;
import com.dmall.order.domain.model.query.Order;
import com.dmall.order.domain.model.query.OrderBrief;
import com.dmall.order.domain.model.query.OrderEvent;
import com.dmall.order.domain.model.query.OrderQueryRepository;
import com.dmall.order.domain.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends HttpFacadeBaseClass {

    private OrderQueryRepository orderQueryRepository;
    private OrderQueryService orderQueryService;


    @Autowired
    public OrderController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/{id}")
    public final ApiForResponse<OrderWithoutItemsDTO> findById(@PathVariable("id") final long id) {
        OrderBrief order = orderQueryService.findOrderBriefById(id);
        OrderWithoutItemsDTO orderWithoutItemsDTO = new OrderWithoutItemsDTO(order);
        orderWithoutItemsDTO.setOrderItems(String.format("/api/v1/orders/%d/orderItems", order.getId()));

        ApiForResponse<OrderWithoutItemsDTO> orderApiForResponse = new ApiForResponse<>(order.getId(), orderWithoutItemsDTO);
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

