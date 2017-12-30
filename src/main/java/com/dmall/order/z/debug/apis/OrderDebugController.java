package com.dmall.order.z.debug.apis;

import com.dmall.order.apis.common.ApiForResponse;
import com.dmall.order.apis.common.HttpFacadeBaseClass;
import com.dmall.order.apis.dto.OrderWithoutItemsDTO;
import com.dmall.order.domain.model.query.OrderBrief;
import com.dmall.order.domain.service.OrderQueryService;
import com.dmall.order.z.debug.infrastructure.persistent.CustomerContactRepositoryDebug;
import com.dmall.order.z.debug.infrastructure.persistent.OrderRepositoryDebug;
import com.dmall.order.z.debug.infrastructure.persistent.SkuRepositoryDebug;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/debug/orders")
public class OrderDebugController extends HttpFacadeBaseClass {

    private OrderQueryService orderQueryService;
    private OrderRepositoryDebug orderRepositoryDebug;
    private SkuRepositoryDebug skuRepositoryDebug;
    private CustomerContactRepositoryDebug customerContactRepository;


    @Autowired
    public OrderDebugController(OrderQueryService orderQueryService,
                                OrderRepositoryDebug orderRepositoryDebug,
                                SkuRepositoryDebug skuRepositoryDebug, CustomerContactRepositoryDebug customerContactRepository) {
        this.orderQueryService = orderQueryService;
        this.orderRepositoryDebug = orderRepositoryDebug;
        this.skuRepositoryDebug = skuRepositoryDebug;
        this.customerContactRepository = customerContactRepository;
    }

    @Transactional
    @GetMapping("/{id}")
    public ApiForResponse<OrderWithoutItemsDTO> findById(@PathVariable("id") final long id) {
        System.out.println("all customerContact");
        customerContactRepository.findAll().forEach(customerContact -> System.out.println("customer contact:"+customerContact.getId()));

        System.out.println("all order,crrent param:"+id);
        orderRepositoryDebug.findAll().forEach(order -> {
            System.out.println("order:" + ToStringBuilder.reflectionToString(order));
            order.getOrderItes().stream().forEach(orderIte -> ToStringBuilder.reflectionToString(orderIte));
        });
        skuRepositoryDebug.findAll().forEach(skuSnapShot -> System.out.println("skuId: "+skuSnapShot.getSkuId()));

        OrderBrief order = orderQueryService.findOrderBriefById(id);

        OrderWithoutItemsDTO orderWithoutItemsDTO = new OrderWithoutItemsDTO(order);
        orderWithoutItemsDTO.setOrderItems(String.format("/api/v1/orders/%d/orderItems", order.getId()));

        ApiForResponse<OrderWithoutItemsDTO> orderApiForResponse = new ApiForResponse<>(order.getId(), orderWithoutItemsDTO);
        return orderApiForResponse;
    }

}

