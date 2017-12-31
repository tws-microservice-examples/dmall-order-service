package com.dmall.order.apis.services;

import com.dmall.order.domain.factory.OrderCommandDTO;
import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.service.OrderCommandService;
import com.dmall.order.service.ProductService;
import com.dmall.order.service.model.Product;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderApplicationService {
    private OrderCommandService orderCommandService;
    private ProductService productService;


    @Autowired
    public OrderApplicationService(OrderCommandService orderCommandService,
                                   ProductService productService) {
        this.orderCommandService = orderCommandService;
        this.productService = productService;
    }


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

        return orderCommandService.submitOrder(orderCommandDTO);

    }

}
