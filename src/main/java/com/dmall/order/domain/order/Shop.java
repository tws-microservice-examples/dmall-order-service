package com.dmall.order.domain.order;

import com.dmall.order.domain.common.ValueObject;
import com.google.common.base.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Shop implements ValueObject<Shop> {
    private String brand;

    public Shop() {
    }

    public Shop(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public boolean sameValueAs(Shop other) {
        return other.brand.equals(brand);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Shop other = (Shop) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getBrand());
    }
}
