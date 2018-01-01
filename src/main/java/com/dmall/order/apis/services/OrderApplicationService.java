package com.dmall.order.apis.services;

import com.dmall.order.domain.factory.OrderCommandDTO;
import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderEvent;
import com.dmall.order.domain.model.query.OrderBrief;
import com.dmall.order.domain.service.OrderCommandService;
import com.dmall.order.service.InventoryService;
import com.dmall.order.service.ProductService;
import com.dmall.order.service.dto.InventoryLockEventDTO;
import com.dmall.order.service.model.Inventory;
import com.dmall.order.service.model.Product;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderApplicationService {
    private OrderCommandService orderCommandService;
    private ProductService productService;
    private InventoryService inventoryService;


    @Autowired
    public OrderApplicationService(OrderCommandService orderCommandService,
                                   ProductService productService, InventoryService inventoryService) {
        this.orderCommandService = orderCommandService;
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

//TODO: 思考题：application service 和domain service职责上怎么划分
//       Order如果复杂化会怎么复杂？
    public Order submitOrder(OrderCommandDTO orderCommandDTO){
        List<String> skuIds = orderCommandDTO.getOrderItems().stream().map(orderItem -> orderItem.getSkuSnapShot().getSkuId().toString()).collect(Collectors.toList());

        String[] skus = skuIds.toArray(new String[]{});

        List<Product> prodcts = productService.findProdctsBySkuIn(skus);
        System.out.println(ToStringBuilder.reflectionToString(prodcts));

        if(prodcts == null){
            throw new RuntimeException("techinique problem");
        }

        if (prodcts.size() != skuIds.size()) {
            throw new RuntimeException("security problem");
        }

        Inventory inventory = inventoryService.getInventoryBySku("6009907");


        orderCommandDTO.getOrderItems().stream().forEach(orderItem -> {

            //TODO: 思考题，中间有一个锁定失败了怎么办？
            String sku = orderItem.getSkuSnapShot().getSkuId().toString();

            ResponseEntity<Inventory> inventoryResponse = inventoryService.lockInventory(sku, new InventoryLockEventDTO(orderItem));

            if (inventoryResponse == null) {
                throw new RuntimeException("techinique Problem");
            }

            if (!inventoryResponse.getStatusCode().equals(HttpStatus.CREATED)) {
                throw new RuntimeException("locked failed");
            }

        });

        //TODO: 这里漏了一个业务，是什么？
        Order createdOrder = orderCommandService.submitOrder(orderCommandDTO);
        return createdOrder;

    }

    public void postEvent(Long orderId, OrderEvent orderEvent) {
        if(orderEvent.getTicketName().equals("PaymentRecord")){
            orderEvent.setName(OrderEvent.Values.PAID.name());
        }
        orderCommandService.postEvent(orderId, orderEvent);
        //TODO: 这里还缺一些业务，缺了什么？有几种处理方式？
    }
}
