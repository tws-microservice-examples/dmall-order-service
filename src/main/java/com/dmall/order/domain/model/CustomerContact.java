package com.dmall.order.domain.model;

import com.dmall.order.domain.common.ValueObject;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerContact implements ValueObject<CustomerContact> {
    private String name;
    private Address address;

    public CustomerContact() {
    }

    public CustomerContact(String name, Address address) {
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
    public boolean sameValueAs(CustomerContact other) {
        return other.name.equals(name)
                && other.address.sameValueAs(address);
    }
}
