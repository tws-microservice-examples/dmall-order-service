package com.dmall.order.apis.services;

import com.dmall.order.factory.OrderCommandDTO;
import com.dmall.order.factory.OrderFactory;
import com.dmall.order.model.Order;
import com.dmall.order.model.OrderEvent;
import com.dmall.order.model.OrderRepository;
import com.dmall.order.service.OrderCommandService;
import com.dmall.order.service.InventoryService;
import com.dmall.order.service.ProductService;
import com.dmall.order.service.dto.InventoryLockEventDTO;
import com.dmall.order.service.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private OrderCommandService orderCommandService;
    private OrderRepository orderRepository;
    private ProductService productService;
    private InventoryService inventoryService;
    private OrderFactory orderFactory;


    @Autowired
    public OrderApplicationServiceImpl(OrderCommandService orderCommandService,
                                       OrderRepository orderRepository,
                                       ProductService productService,
                                       InventoryService inventoryService,
                                       OrderFactory orderFactory) {
        this.orderCommandService = orderCommandService;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.orderFactory = orderFactory;

    }

//TODO: 思考题：application service 和domain service职责上怎么划分
//       Order如果复杂化会怎么复杂？
    @Override
    public Order submitOrder(OrderCommandDTO orderCommandDTO){
        //TODO: 下面这两个业务该不该放到domain层？
        validSkuExist(orderCommandDTO);
        lockInventory(orderCommandDTO);

        //TODO: 这里漏了一个业务，是什么？提示：价格应该用谁的？

        Order result = new Order();
        result.setContactId(orderCommandDTO.getCustomerContactId());

        result.setOrderItems(orderCommandDTO.getOrderItems());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setName("CREATED");
        result.addEvent(orderEvent);
        Order order = result;
        boolean moreThanOneSkuInOneOrder = order.getOrderItems().stream()
                .anyMatch(orderItem -> order.getOrderItems().stream()
                        .filter(anyOrderItem -> anyOrderItem.getSkuSnapShot().getSkuId().equals(orderItem))
                        .collect(Collectors.toList()).size()
                        > 1);
        if(moreThanOneSkuInOneOrder){
            return null;
        }
        Order createdOrder = orderRepository.save(order);
        return createdOrder;

    }

    private void lockInventory(OrderCommandDTO orderCommandDTO) {
        orderCommandDTO.getOrderItems().stream().forEach(orderItem -> {

            //TODO: 思考题，中间有一个锁定失败了怎么办？
            String sku = orderItem.getSkuSnapShot().getSkuId().toString();

            ResponseEntity<Inventory> inventoryResponse = inventoryService.lockInventory(sku, new InventoryLockEventDTO(orderItem));

//            if (inventoryResponse == null) {
//                throw new RuntimeException("techinique Problem");
//            }
//
//            if (!inventoryResponse.getStatusCode().equals(HttpStatus.CREATED)) {
//                throw new RuntimeException("locked failed");
//            }

        });
    }

    private void validSkuExist(OrderCommandDTO orderCommandDTO) {
        List<String> skuIds = orderCommandDTO.getOrderItems().stream().map(orderItem -> orderItem.getSkuSnapShot().getSkuId().toString()).collect(Collectors.toList());

        String[] skus = skuIds.toArray(new String[]{});

        //List<Product> prodcts = productService.findProdctsBySkuIn(skus);
        //System.out.println(ToStringBuilder.reflectionToString(prodcts));

//        if(prodcts == null){
//            throw new RuntimeException("techinique problem");
//        }
//
//        if (prodcts.size() != skuIds.size()) {
//            throw new RuntimeException("security problem");
//        }
    }

    @Override
    public void postEvent(Long orderId, OrderEvent orderEvent) {
        //TODO: 这段逻辑到底在哪比较合适？还有没有其他的处理方法？优缺点是什么？
        if(orderEvent.getTicketName().equals("PaymentRecord")){
            orderEvent.setName(OrderEvent.Values.PAID.name());
        }

        if(orderEvent.getTicketName().equals("CancelAcceptRecord")){
            orderEvent.setName(OrderEvent.Values.CANCEL.name());
        }
        //TODO: 什么是domain级别应该关心的业务？提示：事件之间是不是逻辑关系？
        orderCommandService.postEvent(orderId, orderEvent);
        //TODO: 这里还缺一些业务，缺了什么？有几种处理方式？
    }
}
