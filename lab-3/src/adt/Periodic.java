package adt;

import java.util.Set;

public class Periodic<T> implements IDecorator<T> {
  private IIntervalSet<T> delegate;
  private boolean confirmed;
  private long period;
  
  Periodic(IIntervalSet<T> delegate, long period) {
    this.delegate = delegate;
    this.period = period;
    this.confirmed = false;
  }
  
  Periodic(IIntervalSet<T> delegate) {
    this.delegate = delegate;
    this.period = 0;
    this.confirmed = false;
  }
  
  @Override
  public boolean insert(Interval<T> interval) {
    return delegate.insert(normalize(interval));
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
    return delegate.contains(normalize(interval));
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
    this.confirmed = delegate.confirm();
    return this.confirmed;
  }
  
  @Override
  public boolean remove(Interval<T> interval) {
    return delegate.remove(normalize(interval));
  }
  
  private Interval<T> normalize(Interval<T> interval) {
    if (period == 0) {
      return interval;
    }
    
    if (interval.getLength() > period) {
      throw new RuntimeException();
    }
    
    if (interval.getLength() == period) {
      return new Interval<>(interval.getLabel(), 0, period);
    }
    
    return new Interval<>(interval.getLabel(),
        interval.getBegin() % period,
        interval.getBegin() % period + interval.getLength());
  }
}
