package adt;

import java.util.Set;

public class Disjoint<T> implements IDecorator<T> {
  @Override
  public boolean insert(Interval<T> interval) {
    return false;
  }
  
  @Override
  public Set<Interval<T>> getIntervals() {
    return Set.of();
  }
  
  @Override
  public Set<T> getLabels() {
    return Set.of();
  }
  
  @Override
  public boolean remove(T label) {
    return false;
  }
  
  @Override
  public Set<T> findIntervals(T label) {
    return Set.of();
  }
  
  @Override
  public long begin(T label) {
    return 0;
  }
  
  @Override
  public long end(T label) {
    return 0;
  }
  
  @Override
  public long length(T label) {
    return 0;
  }
  
  @Override
  public boolean isEmpty() {
    return false;
  }
  
  @Override
  public boolean contains(Interval<T> interval) {
    return false;
  }
  
  @Override
  public boolean contains(T label) {
    return false;
  }
  
  @Override
  public long length(Interval<T> interval) {
    return 0;
  }
  
  @Override
  public boolean checkValid() {
    return false;
  }
  
  @Override
  public long end(Interval<T> interval) {
    return 0;
  }
  
  @Override
  public long begin(Interval<T> interval) {
    return 0;
  }
  
  @Override
  public boolean remove(Interval<T> interval) {
    return false;
  }
}