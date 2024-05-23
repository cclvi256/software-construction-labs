package adt;

import java.util.Set;

public class Single<T> implements IDecorator<T> {
  private IIntervalSet<T> delegate;
  
  public Single(IIntervalSet<T> delegate) {
    this.delegate = delegate;
  }
  
  @Override
  public boolean insert(Interval<T> interval) {
    return delegate.insert(interval);
  }
  
  @Override
  public Set<Interval<T>> getIntervals() {
    return delegate.getIntervals();
  }
  
  @Override
  public Set<T> getLabels() {
    return delegate.getLabels();
  }
  
  @Override
  public boolean remove(T label) {
    return delegate.remove(label);
  }
  
  @Override
  public Set<Interval<T>> findIntervals(T label) {
    return delegate.findIntervals(label);
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }
  
  @Override
  public boolean contains(Interval<T> interval) {
    return delegate.contains(interval);
  }
  
  @Override
  public boolean contains(T label) {
    return delegate.contains(label);
  }
  
  @Override
  public boolean checkValid() {
    return delegate.checkValid();
  }
  
  @Override
  public boolean confirm() {
    return delegate.confirm();
  }
  
  @Override
  public boolean remove(Interval<T> interval) {
    return delegate.remove(interval);
  }
}