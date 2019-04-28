package com.dmall.order.domain.model;

public final class SkuSnapShotBuilder {
  private Long skuId;
  private double price;

  private SkuSnapShotBuilder() {
  }

  public static SkuSnapShotBuilder aSkuSnapShot() {
    return new SkuSnapShotBuilder();
  }

  public SkuSnapShotBuilder withSkuId(Long skuId) {
    this.skuId = skuId;
    return this;
  }

  public SkuSnapShotBuilder withPrice(double price) {
    this.price = price;
    return this;
  }



  public SkuSnapShot build() {
    SkuSnapShot skuSnapShot = new SkuSnapShot();
    skuSnapShot.setSkuId(skuId);
    skuSnapShot.setPrice(price);
    return skuSnapShot;
  }
}
