package com.dmall.order.core;



import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Page<T> {
  private List<T> results;
  private int size;
  private long page;
  private long total;

  public Page(List<T> results, long page, int size, long total) {
    this.results = results;
    this.size = size;
    this.page = page;
    this.total = total;
  }

  public <U> Page<U> map(Function<? super T, ? extends U> converter) {
    return new Page<>(getConvertedContent(converter), page, size, total);
  }

  private <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {

    if (converter == null) throw new IllegalArgumentException("function must not be null");

    return this.results.stream().map(converter).collect(Collectors.toList());
  }

  public List<T> getResults() {
    return results;
  }

  public int getSize() {
    return size;
  }

  public long getPage() {
    return page;
  }

  public long getTotal() {
    return total;
  }

}