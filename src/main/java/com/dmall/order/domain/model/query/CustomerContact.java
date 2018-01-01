package com.dmall.order.domain.model.query;

import com.dmall.order.domain.common.ValueObject;
import com.dmall.order.domain.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


//TODO: 思考题： query和command两个上下文下的CustomerContact可不可以合成一个？什么情况应该合？什么情况下不应该合？各有什么价值？
@Entity(name = "jx_customer_contact")
public class CustomerContact implements ValueObject<CustomerContact> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;


    private String name;
    private String province;
    private String city;
    private String area;
    private String street;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    public Long getId() {
        return id;
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

    public String getMore_details() {
        return more_details;
    }

    private String more_details;

    public CustomerContact() {
    }

    public String getName() {
        return name;
    }

    public String getAdress(){
        return province+city+area+street+more_details;
    }

    @Override
    public boolean sameValueAs(CustomerContact other) {
        return other.name.equals(name);
    }
}
