package adt;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntervalSetTest {
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
  //  108: An interval with the same label already exists, and time overlaps fully
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
    IIntervalSet<Integer> i = new IntervalSet<>();
    assertThrows(Exception.class, () -> i.insert(new Interval<>(1, 1, 0)));
  }
  @Test public void test102() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    assertThrows(Exception.class, () -> i.insert(new Interval<>(1, 0, 0)));
  }
  @Test public void test103() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    assertThrows(Exception.class, () -> i.insert(new Interval<>(null, 0, 1)));
  }
  @Test public void test104() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 1));
    assertThrows(Exception.class, () -> i.insert(new Interval<>(1, 0, 1)));
  }
  @Test public void test105() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    assertThrows(Exception.class, () -> i.insert(null));
  }
  @Test public void test106() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(1, 5, 15));
    assertFalse(i.checkValid());
  }
  @Test public void test107() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 5, 15));
    i.insert(new Interval<>(1, 0, 10));
    assertFalse(i.checkValid());
  }
  @Test public void test108() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 10));
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
  @Test public void test111() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 10));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(1, 0, 10)));
  }
  @Test public void test112() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 10, 20));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 10, 20)));
  }
  @Test public void test113() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    i.insert(new Interval<>(3, 10, 20));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 5, 15)));
  }
  @Test public void test114() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 10, 20));
    i.insert(new Interval<>(1, 20, 30));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(1, 20, 30)));
  }
  @Test public void test115() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 5, 15));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 5, 15)));
  }
  @Test public void test116() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 15, 30));
    i.insert(new Interval<>(2, 10, 20));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 10, 20)));
  }
  @Test public void test117() {
    IIntervalSet<Integer> i = new IntervalSet<>();
    i.insert(new Interval<>(1, 0, 10));
    i.insert(new Interval<>(2, 0, 10));
    assertTrue(i.checkValid());
    assertTrue(i.getIntervals().contains(new Interval<>(2, 0, 10)));
  }
  
  //  @Override
  //  public Set<Interval<T>> getIntervals() {
  //    return Set.of();
  //  }
  
  @Test
  public void test201() {
  
  }
  
  //  @Override
  //  public Set<T> getLabels() {
  //    return Set.of();
  //  }
  
  @Test
  public void test301() {
  
  }
  
  //  @Override
  //  public boolean remove(T label) {
  //    return false;
  //  }
  
  @Test
  public void test4A01() {
  
  }
  
  //  @Override
  //  public boolean remove(Interval<T> interval) {
  //    return false;
  //  }
  
  @Test
  public void test4B01() {
  
  }
  
  //  @Override
  //  public Set<T> findIntervals(T label) {
  //    return Set.of();
  //  }
  
  @Test
  public void test501() {
  
  }
  
  //  @Override
  //  public long begin(T label) {
  //    return 0;
  //  }
  
  @Test
  public void test6A01() {
  
  }
  
  //  @Override
  //  public long begin(Interval<T> interval) {
  //    return 0;
  //  }
  
  @Test
  public void test6B01() {
  
  }
  
  //  @Override
  //  public long end(T label) {
  //    return 0;
  //  }
  
  @Test
  public void test7A01() {
  
  }
  
  //  @Override
  //  public long end(Interval<T> interval) {
  //    return 0;
  //  }
  
  @Test
  public void test7B01() {
  
  }
  
  //  @Override
  //  public boolean checkValid() {
  //    return false;
  //  }
  
  @Test
  public void test801() {
  
  }
  
  //  @Override
  //  public long length(T label) {
  //    return 0;
  //  }
  
  @Test
  public void test9A01() {
  
  }
  
  //  @Override
  //  public long length(Interval<T> interval) {
  //    return 0;
  //  }
  
  @Test
  public void test9B01() {
  
  }
  
  //  @Override
  //  public boolean isEmpty() {
  //    return false;
  //  }
  
  @Test
  public void test1001() {
  
  }
  
  //  @Override
  //  public boolean contains(T label) {
  //    return false;
  //  }
  
  @Test
  public void test11A01() {
  
  }
  
  //  @Override
  //  public boolean contains(Interval<T> interval) {
  //    return false;
  
  @Test
  public void test11B01() {
  
  }
}