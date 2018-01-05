package com.dmall.order.domain.order;


import com.dmall.order.domain.common.ValueObject;
import com.google.common.base.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Pet implements ValueObject<Pet> {
    private String petId;
    private Integer price;
    private Integer amount;
    private String description;

    public Pet() {
    }

    public Pet(Integer price, Integer amount, String description, String petId) {
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.petId = petId;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getPetId() {
        return petId;
    }

    @Override
    public boolean sameValueAs(Pet other) {
        return other.price.equals(price)
                && other.amount.equals(amount)
                && other.description.equalsIgnoreCase(description)
                && other.petId.equals(petId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Pet other = (Pet) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPrice(), getAmount(),
                getPetId(), getDescription());
    }
}
