package adts;

public interface IIntervalSet<T> {
  boolean add(T label, int start, int end);
  boolean remove(T label);
  boolean remove(T label, int start);
  boolean remove(T label, int start, int end);
  boolean contains(T label);
  int count();
  int count(T label);
}
