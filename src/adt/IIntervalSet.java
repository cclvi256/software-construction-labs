package adt;

import java.util.Set;

public interface IIntervalSet<T> {
  // TODO  Add a static factory method if necessary.
  // TODO  Delete the overloaded methods if unnecessary.
  boolean insert(Interval<T> interval);
  Set<Interval<T>> getIntervals();
  Set<T> getLabels();
  boolean remove(T label);
  boolean remove(Interval<T> interval);
  Set<T> findIntervals(T label);
  long begin(T label);
  long begin(Interval<T> interval);
  long end(T label);
  long end(Interval<T> interval);
  boolean checkValid();
  boolean confirm();
  long length(T label);
  long length(Interval<T> interval);
  boolean isEmpty();
  boolean contains(T label);
  boolean contains(Interval<T> interval);
}