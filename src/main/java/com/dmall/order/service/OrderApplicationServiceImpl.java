package com.dmall.order.service;

import com.dmall.order.factory.OrderCommandDTO;
import com.dmall.order.factory.OrderFactory;
import com.dmall.order.model.Order;
import com.dmall.order.model.OrderEvent;
import com.dmall.order.model.OrderRepository;
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

    private OrderCommandDTO orderCommandDTO;


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

    //提交数据
    @Override
    public Order submitOrder(OrderCommandDTO orderCommandDTO){
        //校验数据合法性
        validSku(orderCommandDTO);
        this.orderCommandDTO = orderCommandDTO;
        dealWithInventory();


        //创建基础对象，初始化数据
        Order result = new Order();
        result.setContactId(orderCommandDTO.getCustomerContactId());
        result.setOrderItems(orderCommandDTO.getOrderItems());
        /* 添加更多细节 */
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setName("CREATED");
        addEvent(result, orderEvent);

    /*    List<OrderEvent> orderEvents = result.getOrderEvents();
        orderEvents.add(orderEvent);
*/
        //校验业务合法性
        Order order = result;
        boolean flag = order.getOrderItems().stream()
                .anyMatch(i -> order
                        .getOrderItems().stream()
                        .filter(j -> j.getSkuSnapShot()
                        .getSkuId().equals(i))
                        .collect(Collectors.toList()).size()
                        > 1);
        if(flag){
            return null;
        }

        //存储
        Order createdOrder = orderRepository.save(order);
        return createdOrder;

    }

    private void addEvent(Order result, OrderEvent orderEvent) {
        List<OrderEvent> orderEvents = result.getOrderEvents();
        orderEvents.add(orderEvent);
    }

    private void dealWithInventory() {
        orderCommandDTO.getOrderItems().stream().forEach(orderItem -> {


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

    private void validSku(OrderCommandDTO orderCommandDTO) {
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
        if(orderEvent.getTicketName().equals("PaymentRecord")){
            orderEvent.setName(OrderEvent.Values.PAID.name());
        }

        if(orderEvent.getTicketName().equals("CancelAcceptRecord")){
            orderEvent.setName(OrderEvent.Values.CANCEL.name());
        }
        orderCommandService.postEvent(orderId, orderEvent);
    }
}
