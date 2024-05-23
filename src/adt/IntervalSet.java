package adt;

import java.util.*;

public class IntervalSet<T> implements IIntervalSet<T> {
  private Set<Interval<T>> intervals;
  private boolean confirmed;
  
  public IntervalSet() {
    intervals = new HashSet<>();
    confirmed = false;
  }
  
  @Override
  public boolean insert(Interval<T> interval) {
    if (!interval.legal()) {
      throw new RuntimeException();
    }
    
    for (Interval<T> i : intervals) {
      if (i.equals(interval)) {
        throw new RuntimeException();
      }
    }
    
    confirmed = false;
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
      confirmed = false;
      intervals.remove(i);
    }
    
    return true;
  }
  
  @Override
  public boolean remove(Interval<T> interval) {
    if (interval == null || !interval.legal()) {
      throw new RuntimeException();
    }
    
    for (Interval<T> i : intervals) {
      if (i.equals(interval)) {
        confirmed = false;
        intervals.remove(i);
        return true;
      }
    }
    
    throw new RuntimeException();
  }
  
  @Override
  public Set<Interval<T>> findIntervals(T label) {
    if (label == null) {
      throw new RuntimeException();
    }
    
    Set<Interval<T>> rev = new HashSet<>();
    
    for (Interval<T> i : intervals) {
      if (i.getLabel().equals(label)) {
        rev.add(i);
      }
    }
    
    return rev;
  }
  
  @Override
  public boolean checkValid() {
    Map<T, Integer> count = new HashMap<>();
    
    for (Interval<T> i : intervals) {
      T label = i.getLabel();
      if (count.containsKey(label)) {
        count.put(label, count.get(label) + 1);
      } else {
        count.put(label, 1);
      }
    }
    
    for (T i : count.keySet()) {
      if (count.get(i) > 1) {
        Set<Interval<T>> duplicated = findIntervals(i);
        for (Interval<T> j : duplicated) {
          for (Interval<T> k : duplicated) {
            if (!j.equals(k) && j.overlaps(k)) {
              return false;
            }
          }
        }
      }
    }
    
    return true;
  }
  
  @Override
  public boolean confirm() {
    if (!confirmed) {
      if (checkValid()) {
        confirmed = true;
        return true;
      } else {
        return false;
      }
    }
    
    return true;
  }
  
  @Override
  public boolean isEmpty() {
    return intervals.isEmpty();
  }
  
  @Override
  public boolean contains(T label) {
    if (label == null) {
      throw new RuntimeException();
    }
    
    for (Interval<T> i : intervals) {
      if (i.getLabel().equals(label)) {
        return true;
      }
    }
    
    return false;
  }
  
  @Override
  public boolean contains(Interval<T> interval) {
    if (interval == null || !interval.legal()) {
      throw new RuntimeException();
    }
    return intervals.contains(interval);
  }
}