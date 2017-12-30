package com.dmall.order.service;

import com.dmall.order.service.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Component
public class ProductServiceHystrixClientFallback implements ProductService {
    @Override
    public List<Product> findProdctsBySkuIn(String[] skus) {
        return null;
    }

//    @Override
//    public Product getProductDetial(@PathVariable("productId") String productId) {
//        return new Product("default", "default", new Date());
//    }

}
