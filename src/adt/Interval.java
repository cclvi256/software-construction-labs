package adt;

public class Interval<T> {
  private T label;
  private long begin;
  private long end;
  
  Interval(T label, long begin, long end) {
    this.label = label;
    this.begin = begin;
    this.end = end;
  }
  
  public T getLabel() {
    return label;
  }
  
  public long getBegin() {
    return begin;
  }
  
  public long getEnd() {
    return end;
  }
  
  public long getLength() {
    return end - begin;
  }
}
