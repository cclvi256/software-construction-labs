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
  Set<Interval<T>> findIntervals(T label);
  boolean checkValid();
  boolean confirm();
  long length(Interval<T> interval);
  boolean isEmpty();
  boolean contains(T label);
  boolean contains(Interval<T> interval);
}