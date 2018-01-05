package com.dmall.order.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;

/**
 * Created by jxzhong on 2018/1/5.
 */
public class RibbonConfiguration {

    @Bean
    public IPing ribbonPing(final IClientConfig config) {
        // BOOT2: changed from /health to /application/health
        return new PingUrl(false,"/application/health");
    }

    @Bean
    public IRule ribbonRule(final IClientConfig config) {
        return new AvailabilityFilteringRule();
    }
}
