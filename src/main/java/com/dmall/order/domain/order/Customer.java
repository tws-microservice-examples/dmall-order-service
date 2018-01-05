package com.dmall.order.domain.order;


import com.dmall.order.domain.common.ValueObject;
import com.google.common.base.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Customer implements ValueObject<Customer> {
    private String name;
    private Address address;

    public Customer() {
    }

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean sameValueAs(Customer other) {
        return other.name.equals(name)
                && other.address.sameValueAs(address);
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Customer other = (Customer) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getAddress());
    }
}
