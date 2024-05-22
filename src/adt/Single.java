package adt;

import java.util.Set;

public class Single<T> implements IDecorator<T> {
  private IIntervalSet<T> delegate;
  
  public Single(IIntervalSet<T> delegate) {
    this.delegate = delegate;
  }
  
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
  public Set<Interval<T>> findIntervals(T label) {
    return Set.of();
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
  public boolean checkValid() {
    return false;
  }
  
  @Override
  public boolean confirm() {
    return false;
  }
  
  @Override
  public boolean remove(Interval<T> interval) {
    return false;
  }
}