package com.dmall.order.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    public static final String JP_SKU = "sku";

    private String sku;

    @JsonProperty(JP_SKU)
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
