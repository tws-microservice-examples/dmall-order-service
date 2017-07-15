package com.dmall.order.service;

import com.dmall.order.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Component
public class HystrixClientFallback implements ProductService {

    @Override
    public Product getProductDetial(@PathVariable("doctorId") String doctorId) {
        return new Product("default", "default doctor", new Date());
    }
}
