package com.dmall.order.service;

import com.dmall.order.service.model.Product;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {

//    @RequestMapping("/products/{productId}")
//    Product getProductDetial(@PathVariable("productId") String productId);


    @RequestMapping(value="/products", method = RequestMethod.GET, headers = "Accept=application/json")
    List<Product> findProdctsBySkuIn(@RequestParam("skus") String[] skus);
}

