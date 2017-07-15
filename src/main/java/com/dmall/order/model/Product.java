package com.dmall.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.dmall.order.common.CustomDateToStringSerializer;

import java.util.Date;

public class Product {

    public static final String JP_PRODUCTID = "productId";
    public static final String JP_NAME = "name";
    public static final String JP_POSTED = "posted";

    private String productId;

    private String name;

    private Date posted;

    public Product() {
        super();

    }

    public Product(String productId, String comment, Date posted) {
        super();
        this.productId = productId;
        this.name = comment;
        this.posted = posted;
    }

    @JsonProperty(JP_PRODUCTID)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty(JP_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @JsonProperty(JP_POSTED)
    @JsonSerialize(using = CustomDateToStringSerializer.class)
    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((posted == null) ? 0 : posted.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (posted == null) {
            if (other.posted != null)
                return false;
        } else if (!posted.equals(other.posted))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", posted=" + posted + "]";
    }

}

