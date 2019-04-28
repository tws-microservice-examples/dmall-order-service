package com.dmall.order.domain.core;

public class Pageable {

  private final long page;
  private final int size;

  public long getPage() {
    return page;
  }

  public int getSize() {
    return size;
  }

  public Pageable(long page, int size) {
    this.page = page;
    this.size = size;
  }
}
