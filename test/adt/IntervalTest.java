package adt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntervalTest {
  @Test
  public void test1() {
    Interval<String> i = new Interval<>("a", 200410141808L, 200410141809L);
    assertEquals("a", i.getLabel());
    assertEquals(200410141808L, i.getBegin());
    assertEquals(200410141809L, i.getEnd());
    assertEquals(1, i.getLength());
  }
  
  @Test public void test2() {
    Interval<Integer> i = new Interval<>(1, 200410141808L, 200410141809L);
    assertEquals(1, i.getLabel());
    assertEquals(200410141808L, i.getBegin());
    assertEquals(200410141809L, i.getEnd());
    assertEquals(1, i.getLength());
  }
  
  @Test public void test3() {
    Interval<Double> i = new Interval<>(1.0, 200410141808L, 200410141809L);
    assertEquals(1.0, i.getLabel());
    assertEquals(200410141808L, i.getBegin());
    assertEquals(200410141809L, i.getEnd());
    assertEquals(1, i.getLength());
  }
}
