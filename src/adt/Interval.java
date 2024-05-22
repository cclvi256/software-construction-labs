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
  
  public boolean isOverlap(Interval<T> that){
    return this.end > that.begin || this.begin < that.end;
  }
  
  @Override public boolean equals(Object that) {
    if (!(that instanceof Interval)) {
      return false;
    }
    
    Interval<T> thatInterval = (Interval<T>) that;
    return this.label.equals(thatInterval.label) &&
           this.begin == thatInterval.begin &&
           this.end == thatInterval.end;
  }
  
  @Override public int hashCode() {
    int rev = 17;
    rev = 47 * rev + label.hashCode();
    rev = 47 * rev + Long.hashCode(begin);
    rev = 47 * rev + Long.hashCode(end);
    return rev;
  }
}
