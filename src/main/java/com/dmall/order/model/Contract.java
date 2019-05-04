package com.dmall.order.model;


public class Contract {
  private Long id;


  public void setName(String name) {
    this.name = name;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setMore_details(String more_details) {
    this.more_details = more_details;
  }

  private String name;
  private String province;
  private String city;
  private String area;
  private String street;
  private String more_details;



  public String getName() {
    return name;
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
}
