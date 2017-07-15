
package com.dmall.order.model;

public class Order {

  private String orderId;

  private String productId;

  private String goodsId;


  private Product product;
  private Shipping shipping;

  public Order() {
    super();

  }

  public Order(String orderId, String productId, String goodsId) {
    this.orderId = orderId;
    this.productId = productId;
    this.goodsId = goodsId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Shipping getShipping() {
    return shipping;
  }

  public void setShipping(Shipping shipping) {
    this.shipping = shipping;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(String goodsId) {
    this.goodsId = goodsId;
  }
}
