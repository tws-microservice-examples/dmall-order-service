package com.dmall.order.service;

import com.dmall.order.model.Shipping;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class ShippingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")})
    public Shipping getShippingDetail(String goodsId) {
        String url = String.format("http://shipping-service/goods/%s", goodsId);
        return restTemplate.getForObject(url, Shipping.class);

    }

    @SuppressWarnings("unused")
    private Shipping fallback(String goodsId) {

        return new Shipping("default", "默认仓库", "默认地址", new Date());
    }
}
