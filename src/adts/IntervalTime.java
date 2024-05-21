package adts;

public class IntervalTime {
  private int start;
  private int end;
  
  public IntervalTime(int start, int end) {
    this.start = start;
    this.end = end;
  }
  
  public int getStart() {
    return start;
  }
  
  public int getEnd() {
    return end;
  }
}
