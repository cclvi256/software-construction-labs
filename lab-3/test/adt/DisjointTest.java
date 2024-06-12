package adt;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisjointTest {
  //  @Override
  //  public boolean insert(Interval<T> interval) {
  //    return false;
  //  }
  //  Test strategy:
  //  10: Interval is illegal
  //  101: The end is before the begin time
  //  102: The end is equal to the begin time
  //  103: Label is null
  //  104: An identical interval already exists
  //  105: The interval itself is null
  //  106: An interval with the same label already exists, and time overlaps l-partially
  //  107: An interval with the same label already exists, and time overlaps r-partially
  //  11: Interval is legal
  //  111: The interval is the first one
  //  112: The interval is the last one
  //  113: The interval is in the middle
  //  114: The interval shares the same label, but the time does not overlap
  //  115: The interval overlaps l-partially with another with different label
  //  116: The interval overlaps r-partially with another with different label
  //  117: The interval overlaps fully with another with different label
  
  @Test
  public void test101() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    assertThrows(Exception.class, () -> i.insert(new Interval<>(1, 1, 0)));
  }
  
  @Test
  public void test102() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    assertThrows(Exception.class, () -> i.insert(new Interval<>(1, 0, 0)));
  }
  
  @Test
  public void test103() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    assertThrows(Exception.class, () -> i.insert(new Interval<>(null, 0, 1)));
  }
  
  @Test
  public void test104() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 1));
    assertThrows(Exception.class, () -> i.insert(new Interval<>(1, 0, 1)));
  }
  
  @Test
  public void test105() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    assertThrows(Exception.class, () -> i.insert(null));
  }
  
  @Test
  public void test106() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertFalse(i.checkValid());
  }
  
  @Test
  public void test107() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 5, 15));
    i.insert(new Interval<>(1, 0, 10));
    assertFalse(i.checkValid());
  }
  
  //  111: The interval is the first one
  //  112: The interval is the last one
  //  113: The interval is in the middle
  //  114: The interval shares the same label, but the time does not overlap
  //  115: The interval overlaps l-partially with another with different label
  //  116: The interval overlaps r-partially with another with different label
  //  117: The interval overlaps fully with another with different label
  @Test
  public void test111() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(1, 0, 10)));
  }
  
  @Test
  public void test112() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 10, 20));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 10, 20)));
  }
  
  @Test
  public void test113() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    i.insert(new Interval<>(3, 10, 20));
    assertFalse(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 5, 15)));
  }
  
  @Test
  public void test114() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 10, 20));
    i.insert(new Interval<>(1, 20, 30));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(1, 20, 30)));
  }
  
  @Test
  public void test115() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    assertFalse(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 5, 15)));
  }
  
  @Test
  public void test116() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 15, 30));
    i.insert(new Interval<>(2, 10, 20));
    assertFalse(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 10, 20)));
  }
  
  @Test
  public void test117() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 0, 10));
    assertFalse(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 0, 10)));
  }
  
  //  @Override
  //  public Set<Interval<T>> getIntervals() {
  //    return Set.of();
  //  }
  //  Test strategy:
  //  201: The interval set is empty
  //  202: The interval set is not empty
  //  203: The interval set is null
  //  204: Some of the labels is null
  //  205: The interval set is illegal
  
  @Test
  public void test201() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    assertTrue(i.getIntervals().isEmpty());
  }
  
  @Test
  public void test202() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 10, 20));
    assertTrue(i.getIntervals().contains(new Interval<>(1, 0, 10)));
    assertTrue(i.getIntervals().contains(new Interval<>(2, 10, 20)));
  }
  
  @Test
  public void test203() {
    IIntervalSet<Integer> i = null;
    assertThrows(Exception.class, () -> i.getIntervals());
  }
  
  @Test
  public void test204() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertFalse(i.checkValid());
    // assertThrows(Exception.class, () -> i.getIntervals());
  }
  
  //  @Override
  //  public Set<T> getLabels() {
  //    return Set.of();
  //  }
  //  Test strategy:
  //  301: The interval set is empty
  //  302: The interval set have only 1 label
  //  303: The interval set have multiple labels
  //  304: Some of the labels is null
  //  305: The interval set is null
  //  306: The interval set is illegal
  
  
  @Test
  public void test301() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    assertTrue(i.getLabels().isEmpty());
  }
  
  @Test
  public void test302() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertTrue(i.getLabels().contains(1));
  }
  
  @Test
  public void test303() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 10, 20));
    assertTrue(i.getLabels().contains(1));
    assertTrue(i.getLabels().contains(2));
  }
  
  @Test
  public void test304() {
    IIntervalSet<Integer> i = null;
    assertThrows(Exception.class, () -> i.getLabels());
  }
  
  @Test
  public void test305() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertFalse(i.checkValid());
    // assertThrows(Exception.class, () -> i.getLabels());
  }
  
  //  @Override
  //  public boolean remove(T label) {
  //    return false;
  //  }
  //  Test strategy
  //  4A0 The label is illegal
  //  4A01 The label is null
  //  4A02 The label not exists
  //  4A1 The label exists
  //  4A11 The label is not repeated
  //  4A12 The label is not repeated, but overlaps with another interval
  //  4A13 The label is repeated
  //  4A14 The label is repeated, but overlaps with another interval
  //  4A15 The label is repeated, but overlapping itself
  
  @Test
  public void test4A01() {
    IIntervalSet<String> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>("1", 0, 10));
    String a = null;
    assertThrows(Exception.class, () -> i.remove(a));
  }
  
  @Test
  public void test4A02() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    // assertThrows(Exception.class, () -> i.remove(2));
    // assertFalse(i.remove(2));
    // TODO  Use the better one
  }
  
  @Test
  public void test4A11() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertTrue(i.remove(1));
    assertFalse(i.contains(1));
  }
  
  @Test
  public void test4A12() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    assertTrue(i.remove(1));
    assertFalse(i.contains(1));
    assertTrue(i.contains(2));
  }
  
  @Test
  public void test4A13() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertTrue(i.remove(1));
    assertFalse(i.contains(1));
  }
  
  @Test
  public void test4A14() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 14, 35));
    i.insert(new Interval<>(2, 10, 20));
    assertTrue(i.remove(1));
    assertFalse(i.contains(1));
    assertTrue(i.contains(2));
  }
  
  @Test
  public void test4A15() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertTrue(i.remove(1));
    assertFalse(i.contains(1));
  }
  
  //  @Override
  //  public boolean remove(Interval<T> interval) {
  //    return false;
  //  }
  //  Test strategy:
  //  4B0 The interval is illegal
  //  4B01 The interval is null
  //  4B02 The interval label is null
  //  4B03 The interval's end is earlier than the beginning
  //  4B04 The interval's end is equal to the beginning
  //  4B05 The interval label not exists
  //  4B06 The interval label exists, but the time does not match
  //  4B1 The interval is legal
  //  4B11 No intervals share the same label
  //  4B12 Some intervals share the same label
  //  4B13 The interval overlaps with another interval
  
  @Test
  public void test4B01() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    Interval<Integer> interval = null;
    assertThrows(Exception.class, () -> i.remove(interval));
  }
  
  @Test
  public void test4B02() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    Interval<Integer> interval = new Interval<>(null, 0, 10);
    assertThrows(Exception.class, () -> i.remove(interval));
  }
  
  @Test
  public void test4B03() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    Interval<Integer> interval = new Interval<>(1, 10, 0);
    assertThrows(Exception.class, () -> i.remove(interval));
  }
  
  @Test
  public void test4B04() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    Interval<Integer> interval = new Interval<>(1, 10, 10);
    assertThrows(Exception.class, () -> i.remove(interval));
  }
  
  @Test
  public void test4B05() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    Interval<Integer> interval = new Interval<>(2, 0, 10);
    assertThrows(Exception.class, () -> i.remove(interval));
  }
  
  @Test
  public void test4B06() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    Interval<Integer> interval = new Interval<>(1, 5, 15);
    assertThrows(Exception.class, () -> i.remove(interval));
  }
  
  @Test
  public void test4B11() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 15, 20));
    Interval<Integer> interval = new Interval<>(2, 15, 20);
    assertTrue(i.remove(interval));
    assertFalse(i.contains(interval));
  }
  
  @Test
  public void test4B12() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    i.insert(new Interval<>(2, 15, 20));
    Interval<Integer> interval = new Interval<>(2, 15, 20);
    assertTrue(i.remove(interval));
    assertFalse(i.contains(interval));
  }
  
  @Test
  public void test4B13() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    Interval<Integer> interval = new Interval<>(2, 5, 15);
    assertTrue(i.remove(interval));
    assertFalse(i.contains(interval));
  }
  
  //  @Override
  //  public Set<T> findIntervals(T label) {
  //    return Set.of();
  //  }
  //  Test strategy:
  //  501: The label is null
  //  502: The label does not exist
  //  503: The label exists, not repeated
  //  504: The label exists, repeated
  
  @Test
  public void test501() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    Integer a = null;
    assertThrows(Exception.class, () -> i.findIntervals(a));
  }
  
  @Test
  public void test502() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertTrue(i.findIntervals(2).isEmpty());
  }
  
  @Test
  public void test503() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertTrue(i.findIntervals(1).contains(new Interval<>(1, 0, 10)));
  }
  
  @Test
  public void test504() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertTrue(i.findIntervals(1).contains(new Interval<>(1, 0, 10)));
    assertTrue(i.findIntervals(1).contains(new Interval<>(1, 5, 15)));
  }
  
  //  @Override
  //  public boolean checkValid() {
  //    return false;
  //  }
  //  Test strategy:
  //  601: The interval set is legal
  //  602: The intervals sharing the same label overlap
  
  @Test
  public void test601() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 1, 20));
    assertFalse(i.checkValid());
  }
  
  @Test
  public void test602() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertFalse(i.checkValid());
  }
  
  @Test public void test603() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    i.insert(new Interval<>(3, 10, 20));
    assertFalse(i.checkValid());
  }
  
  @Test public void test604() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 16, 20));
    assertTrue(i.checkValid());
  }
  
  //  @Override
  //  public boolean isEmpty() {
  //    return false;
  //  }
  //  Test Strategy
  //  701: The interval set is empty
  //  702: The interval set is not empty
  
  @Test
  public void test701() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    assertTrue(i.isEmpty());
  }
  
  @Test
  public void test702() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertFalse(i.isEmpty());
  }
  
  //  @Override
  //  public boolean contains(T label) {
  //    return false;
  //  }
  //  Test strategy:
  //  8A01 The label is null
  //  8A02 The label is not in the set
  //  8A03 The label is attached to one interval
  //  8A04 The label is attached to multiple intervals
  
  @Test
  public void test8A01() {
    IIntervalSet<String> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>("1", 0, 10));
    
    String a = null;
    assertThrows(Exception.class, () -> i.contains(a));
  }
  
  @Test
  public void test8A02() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertFalse(i.contains(2));
  }
  
  @Test
  public void test8A03() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertTrue(i.contains(1));
  }
  
  @Test
  public void test8A04() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertTrue(i.contains(1));
  }
  
  //  @Override
  //  public boolean contains(Interval<T> interval) {
  //    return false;
  //  Test strategy:
  //  8B01 The interval is null
  //  8B02 The label is null
  //  8B03 The interval have its end earlier than the beginning
  //  8B04 The interval have its end equal to the beginning
  //  8B05 The interval is not in the set
  //  8B06 The interval is in the set
  //  8B07 The interval's label is in the set, but the time does not match
  
  @Test
  public void test8B01() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    Interval<Integer> interval = null;
    assertThrows(Exception.class, () -> i.contains(interval));
  }
  
  @Test
  public void test8B02() {
    IIntervalSet<String> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>("1", 0, 10));
    Interval<String> interval = new Interval<>(null, 0, 10);
    assertThrows(Exception.class, () -> i.contains(interval));
  }
  
  @Test
  public void test8B03() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertThrows(Exception.class, () -> i.contains(new Interval<>(1, 10, 0)));
  }
  
  @Test
  public void test8B04() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertThrows(Exception.class, () -> i.contains(new Interval<>(1, 10, 10)));
  }
  
  @Test
  public void test8B05() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertFalse(i.contains(new Interval<>(2, 10, 15)));
  }
  
  @Test
  public void test8B06() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    assertTrue(i.contains(new Interval<>(1, 0, 10)));
  }
  
  @Test
  public void test8B07() {
    IIntervalSet<Integer> i = new Disjoint<>(new IntervalSet<>());
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    assertFalse(i.contains(new Interval<>(1, 5, 15)));
  }
}
