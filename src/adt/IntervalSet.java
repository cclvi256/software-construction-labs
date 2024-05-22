package adt;

import java.util.HashSet;
import java.util.Set;

public class IntervalSet<T> implements IIntervalSet<T> {
  private Set<Interval<T>> intervals;
  private boolean valid;
  
  IntervalSet() {
    intervals = new HashSet<>();
    valid = true;
  }
  
  @Override
  public boolean insert(Interval<T> interval) {
    for (Interval<T> i : intervals) {
      if (i.equals(interval)) {
        return false;
      }
      
      if (i.getLabel().equals(interval.getLabel())) {
        if (i.isOverlap(interval)) {
          valid = false;
          break;
        }
      }
    }
    
    intervals.add(interval);
    return true;
  }
  
  @Override
  public Set<Interval<T>> getIntervals() {
    return new HashSet<>(intervals);
  }
  
  @Override
  public Set<T> getLabels() {
    Set<T> labels = new HashSet<>();
    for (Interval<T> i : intervals) {
      labels.add(i.getLabel());
    }
    return labels;
  }
  
  @Override
  public boolean remove(T label) {
    Set<Interval<T>> toRemove = findIntervals(label);
    
    if (toRemove.isEmpty()) {
      return false;
    }
    
    for (Interval<T> i : toRemove) {
      intervals.remove(i);
    }
    
    return true;
  }
  
  @Override
  public boolean remove(Interval<T> interval) {
    for (Interval<T> i : intervals) {
      if (i.equals(interval)) {
        intervals.remove(i);
        return true;
      }
    }
    
    return false;
  }
  
  @Override
  public Set<Interval<T>> findIntervals(T label) {
    return Set.of();
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
  public boolean isEmpty() {
    return false;
  }
  
  @Override
  public boolean contains(T label) {
    return false;
  }
  
  @Override
  public boolean contains(Interval<T> interval) {
    return false;
  }
}