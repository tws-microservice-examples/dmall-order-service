package com.dmall.order.domain.order;


import com.dmall.order.domain.common.ValueObject;
import com.google.common.base.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements ValueObject<Address> {
    private String province;
    private String city;
    private String area;
    private String street;
    private String moreDetails;

    public Address() {
    }

    public Address(String province, String city, String area, String street, String moreDetails) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.street = street;
        this.moreDetails = moreDetails;
    }

    @Override
    public boolean sameValueAs(Address other) {
        return other.province.equals(province)
                && other.city.equals(city)
                && other.area.equals(area)
                && other.street.equals(street)
                && other.moreDetails.equalsIgnoreCase(moreDetails);
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getStreet() {
        return street;
    }

    public String getMoreDetails() {
        return moreDetails;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Address other = (Address) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProvince(), getCity(),
                getArea(), getStreet(), getMoreDetails());
    }

}
