package adts;

import java.util.List;
import java.util.Map;

public class IntervalSet<T> implements IIntervalSet<T> {
  private Map<T, IntervalTime> intervals;
  
  @Override
  public boolean add(T label, int start, int end) {
    return false;
  }
  
  @Override
  public boolean remove(T label) {
    return false;
  }
  
  @Override
  public boolean remove(T label, int start) {
    return false;
  }
  
  @Override
  public boolean remove(T label, int start, int end) {
    return false;
  }
  
  @Override
  public boolean contains(T label) {
    return false;
  }
  
  @Override
  public int count() {
    return 0;
  }
  
  @Override
  public int count(T label) {
    return 0;
  }
}
