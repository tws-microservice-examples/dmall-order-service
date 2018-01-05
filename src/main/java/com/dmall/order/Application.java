package com.dmall.order;

import com.dmall.order.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
