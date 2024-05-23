package adt;

import java.util.Set;

public class Disjoint<T> implements IDecorator<T> {
  private IIntervalSet<T> delegate;
  private boolean confirmed;
  
  public Disjoint(IIntervalSet<T> delegate) {
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
    if (delegate.checkValid()) {
      Set<Interval<T>> intervals = getIntervals();
      for (Interval<T> i : intervals) {
        for (Interval<T> j : intervals) {
          if (i != j && i.overlaps(j)) {
            return false;
          }
        }
      }
      
      return true;
    }
    
    return false;
  }
  
  @Override
  public boolean confirm() {
    if (delegate.confirm()) {
      if (checkValid()) {
        confirmed = true;
        return true;
      } else {
        confirmed = false;
        return false;
      }
    }
    
    confirmed = false;
    return false;
  }
  
  @Override
  public boolean remove(Interval<T> interval) {
    return delegate.remove(interval);
  }
}