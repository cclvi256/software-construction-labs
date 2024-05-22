package adt;

import java.util.Set;

public interface IDecorator<T> extends IIntervalSet<T> {
  @Override
  boolean insert(Interval<T> interval);
  
  @Override
  Set<Interval<T>> getIntervals();
  
  @Override
  Set<T> getLabels();
  
  @Override
  boolean remove(T label);
  
  @Override
  Set<Interval<T>> findIntervals(T label);
  
  @Override
  boolean isEmpty();
  
  @Override
  boolean contains(Interval<T> interval);
  
  @Override
  boolean contains(T label);
  
  @Override
  long length(Interval<T> interval);
  
  @Override
  boolean checkValid();
  
  @Override
  boolean confirm();
  
  @Override
  boolean remove(Interval<T> interval);
}