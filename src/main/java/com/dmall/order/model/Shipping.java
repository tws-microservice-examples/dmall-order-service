package com.dmall.order.model;

import com.dmall.order.common.CustomDateToStringSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.Objects;

public class Shipping {

  public static final String JP_DOCTORID = "goodsId";
  public static final String JP_WAREHOUSE = "warehouse";
  public static final String JP_ADDRESS = "address";
  public static final String JP_POSTED = "posted";

  private String goodsId;

  private String warehouse;

  private String address;


  private Date posted;

  public Shipping() {
    super();

  }

  public Shipping(String goodsId, String comment, String warehouse, String address, Date posted) {
    super();
    this.goodsId = goodsId;
    this.warehouse = warehouse;
    this.address = address;
    this.posted = posted;
  }

  @JsonProperty(JP_DOCTORID)
  public String getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(String goodsId) {
    this.goodsId = goodsId;
  }

  @JsonProperty(JP_WAREHOUSE)
  public String getWarehouse() {
    return warehouse;
  }

  public void setWarehouse(String warehouse) {
    this.warehouse = warehouse;
  }

  @JsonProperty(JP_ADDRESS)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Shipping shipping = (Shipping) o;
    return Objects.equals(goodsId, shipping.goodsId) &&
        Objects.equals(warehouse, shipping.warehouse) &&
        Objects.equals(address, shipping.address) &&
        Objects.equals(posted, shipping.posted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(goodsId, warehouse, address, posted);
  }

  @Override
  public String toString() {
    return "Shipping{" +
        "goodsId='" + goodsId + '\'' +
        ", warehouse='" + warehouse + '\'' +
        ", address='" + address + '\'' +
        ", posted=" + posted +
        '}';
  }
}

