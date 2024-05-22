package adt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Full<T> implements IDecorator<T> {
  private IIntervalSet<T> delegate;
  private int begin;
  private int end;
  private boolean confirmed;
  
  Full(IIntervalSet<T> delegate, int begin, int end) {
    this.delegate = delegate;
    this.begin = begin;
    this.end = end;
    this.confirmed = false;
    
    if (begin >= end) {
      throw new RuntimeException();
    }
  }
  
  Full(IIntervalSet<T> delegate) {
    this.delegate = delegate;
    this.begin = 0;
    this.end = -1;
    this.confirmed = false;
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
    boolean baseCheck = delegate.checkValid();
    boolean thisCheck = false;
    
    List<Interval<T>> intervals = new ArrayList<>(delegate.getIntervals());
    Collections.sort(intervals);
    
    ArrayList<Long[]> intervalList = new ArrayList<>();
    for (Interval<T> interval : intervals) {
      intervalList.add(new Long[] {interval.getBegin(), interval.getEnd()});
    }
    
    ArrayList<Long[]> merged = new ArrayList<>();
    
    for (Long[] i : intervalList) {
      if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < i[0]) {
        merged.add(i);
      } else {
        merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], i[1]);
      }
    }
    
    if (merged.size() == 1) {
      if (merged.get(0)[0] == begin && merged.get(0)[1] == end) {
        thisCheck = true;
      } else if (begin == 0 && end == -1) {
        thisCheck = true;
      }
    }
    
    return baseCheck && thisCheck;
  }
  
  @Override
  public boolean confirm() {
    delegate.confirm();
    
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
  public boolean remove(Interval<T> interval) {
    return delegate.remove(interval);
  }
}
