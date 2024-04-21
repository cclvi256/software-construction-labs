/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * <p>
 * Tests for instance methods of Graph.
 * </p><p>
 * PS2's instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 * </p>
 */
public abstract class GraphInstanceTest {
  
  // Testing strategy
  //   TODO
  
  /**
   * Overridden by implementation-specific test classes.
   *
   * @return a new empty graph of the particular implementation being tested
   */
  public abstract Graph<String> emptyInstance();
  
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false; // make sure assertions are enabled with VM argument: -ea
  }
  
  @Test
  public void testInitialVerticesEmpty() {
    // TODO you may use, change, or remove this test
    assertEquals("expected new graph to have no vertices",
        Collections.emptySet(), emptyInstance().vertices());
  }
  
  // TODO other tests for instance methods of Graph
  
  /*
   * 1 add() - adding a vertex to the graph
   *  1 Vertex not exists -> Add it and return true
   *  2 Vertex exists -> Return false and do nothing
   *  3 Vertex is null -> Exception
   *
   * 2 set() - adding, modifying, or removing an edge in the graph
   *  1 Weight is negative -> Exception
   *  2 Source vertex is null -> Exception
   *  3 Target vertex is null -> Exception
   *  *** Removing ***
   *  4 Edge not exists -> Return 0
   *  5 Edge exists -> Remove it and return the previous weight
   *  6 Source vertex not exists -> Return 0 and do nothing
   *  7 Target vertex not exists -> Return 0 and do nothing
   *  8 Source vertex is null -> Exception
   *  9 Target vertex is null -> Exception
   *  *** Adding ***
   *  10 Source and target exists, edge not exists -> Add it and return 0
   *  11 Source exists, target not exists -> Add both and return 0
   *  12 Source not exists, target exists -> Add both and return 0
   *  13 Source and target not exists -> Add the three and return 0
   *  14 Source is null -> Exception
   *  15 Target is null -> Exception
   *  *** Modifying ***
   *  16 Edge exists -> Modify it and return the previous weight
   *  17 Source is null -> Exception
   *  18 Target is null -> Exception
   *
   * 3 remove() - removing a vertex from the graph
   *  1 Vertex not exists -> Return false
   *  2 Vertex exists but has no edge -> Remove it and return true
   *  3 Vertex exists and has edge -> Remove it and return true
   *  4 Vertex is null -> Exception
   *
   * 4 vertices() - get all the vertices in the graph
   *  1 Graph has no vertex -> Return an empty set
   *  2 Graph has one vertex -> Return a set with one element
   *  3 Graph has few vertices -> Return a set with all the elements
   *  4 Graph has many vertices -> Return a set with all the elements
   *  5 Graph has null vertex or vertices -> Exception
   *
   * 5 sources() - get all the sources of the target vertex
   *  1 Target vertex not exists -> Return an empty map  (or throw an exception, or return null?)
   *  2 Target vertex exists but has no source -> Return an empty map
   *  3 Target vertex exists and has few sources -> Return a map with all the elements
   *  4 Target vertex exists and has many sources -> Return a map with all the elements
   *  5 Target vertex is null -> Exception
   *
   * 6 targets() - get all the targets of the source vertex
   * - Like the sources() method
   */
  
  // First, observer methods should be tested first, because they will be used
  // to test other methods.
  
  //   * 4 vertices() - get all the vertices in the graph
  
  //   *  1 Graph has no vertex -> Return an empty set
  @Test
  public void test41() {
    Graph<String> graph = emptyInstance();
    assert (graph.vertices().isEmpty());
  }
  
  //   *  2 Graph has one vertex -> Return a set with one element
  @Test
  public void test42() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    assertEquals(1, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
  }
  
  //   *  3 Graph has few vertices -> Return a set with all the elements
  @Test
  public void test43() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.add("c");
    assertEquals(3, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
    assertTrue(graph.vertices().contains("b"));
    assertTrue(graph.vertices().contains("c"));
  }
  
  //   *  4 Graph has many vertices -> Return a set with all the elements
  @Test
  public void test44() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.add("c");
    graph.add("d");
    graph.add("e");
    graph.add("f");
    graph.add("g");
    graph.add("h");
    graph.add("i");
    graph.add("j");
    assertEquals(10, graph.vertices().size());
    String str = "";
    for (int i = 0; i < 10; i++) {
      str = "" + (char) ('a' + i);
      assertTrue(graph.vertices().contains(str));
    }
  }
  
  //   *  5 Graph has null vertex or vertices -> Exception
  @Test(expected = Exception.class)
  public void test45() {
    Graph<String> graph = emptyInstance();
    graph.add(null);
  }
  
  //   * 5 sources() - get all the sources of the target vertex
  
  //   *  1 Target vertex not exists -> Return an empty map  (or throw an exception, or return null?)
  @Test(expected = Exception.class)
  public void test51() {
    Graph<String> graph = emptyInstance();
    graph.sources("a");
  }
  
  //   *  2 Target vertex exists but has no source -> Return an empty map
  @Test
  public void test52() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    assertTrue(graph.sources("a").isEmpty());
  }
  
  //   *  3 Target vertex exists and has few sources -> Return a map with all the elements
  @Test
  public void test53() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.add("c");
    graph.set("a", "b", 1);
    graph.set("c", "b", 2);
    assertEquals(2, graph.sources("b").size());
    assertTrue(graph.sources("b").containsKey("a"));
    assertTrue(graph.sources("b").containsKey("c"));
    assertEquals(1, (int) graph.sources("b").get("a"));
    assertEquals(2, (int) graph.sources("b").get("c"));
  }
  
  //   *  4 Target vertex exists and has many sources -> Return a map with all the elements
  @Test
  public void test54() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.add("c");
    graph.add("d");
    graph.add("e");
    graph.add("f");
    graph.add("g");
    graph.add("h");
    graph.add("i");
    graph.add("j");
    graph.set("b", "a", 1);
    graph.set("c", "a", 2);
    graph.set("d", "a", 3);
    graph.set("e", "a", 4);
    graph.set("f", "a", 5);
    graph.set("g", "a", 6);
    graph.set("h", "a", 7);
    graph.set("i", "a", 8);
    graph.set("j", "a", 9);
    assertEquals(9, graph.sources("a").size());
    for (int i = 0; i < 9; i++) {
      assertTrue(graph.sources("a").containsKey("" + (char) ('b' + i)));
      assertEquals(i + 1, (int) graph.sources("a").get("" + (char) ('b' + i)));
    }
  }
  
  //   *  5 Target vertex is null -> Exception
  @Test(expected = Exception.class)
  public void test55() {
    Graph<String> graph = emptyInstance();
    graph.sources(null);
  }
  
  //   * 6 targets() - get all the targets of the source vertex
  
  //   *  1 Source vertex not exists -> Return an empty map  (or throw an exception, or return null?)
  @Test(expected = Exception.class)
  public void test61() {
    Graph<String> graph = emptyInstance();
    graph.targets("a");
  }
  
  //   *  2 Source vertex exists but has no target -> Return an empty map
  @Test
  public void test62() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    assertTrue(graph.targets("a").isEmpty());
  }
  
  //   *  3 Source vertex exists and has few targets -> Return a map with all the elements
  @Test
  public void test63() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.add("c");
    graph.set("a", "b", 1);
    graph.set("a", "c", 2);
    assertEquals(2, graph.targets("a").size());
    assertTrue(graph.targets("a").containsKey("b"));
    assertTrue(graph.targets("a").containsKey("c"));
    assertEquals(1, (int) graph.targets("a").get("b"));
    assertEquals(2, (int) graph.targets("a").get("c"));
  }
  
  //   *  4 Source vertex exists and has many targets -> Return a map with all the elements
  @Test
  public void test64() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.add("c");
    graph.add("d");
    graph.add("e");
    graph.add("f");
    graph.add("g");
    graph.add("h");
    graph.add("i");
    graph.add("j");
    graph.set("a", "b", 1);
    graph.set("a", "c", 2);
    graph.set("a", "d", 3);
    graph.set("a", "e", 4);
    graph.set("a", "f", 5);
    graph.set("a", "g", 6);
    graph.set("a", "h", 7);
    graph.set("a", "i", 8);
    graph.set("a", "j", 9);
    assertEquals(9, graph.targets("a").size());
    for (int i = 0; i < 9; i++) {
      assertTrue(graph.targets("a").containsKey("" + (char) ('b' + i)));
      assertEquals(i + 1, (int) graph.targets("a").get("" + (char) ('b' + i)));
    }
  }
  
  //   *  5 Source vertex is null -> Exception
  @Test(expected = Exception.class)
  public void test65() {
    Graph<String> graph = emptyInstance();
    graph.targets(null);
  }
  
  // Then, test the mutator methods. They should be tested in the following order:
  
  //   * 1 add() - adding a vertex to the graph
  
  //   *  1 Vertex not exists -> Add it and return true
  @Test
  public void test11() {
    Graph<String> graph = emptyInstance();
    assertTrue(graph.add("a"));
    assertTrue(graph.vertices().contains("a"));
  }
  
  //   *  2 Vertex exists -> Return false and do nothing
  @Test
  public void test12() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    assertEquals(1, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
    assertFalse(graph.add("a"));
    assertEquals(1, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
  }
  
  //   *  3 Vertex is null -> Exception
  @Test(expected = Exception.class)
  public void test13() {
    Graph<String> graph = emptyInstance();
    graph.add(null);
  }
  
  //   * 2 set() - adding, modifying, or removing an edge in the graph
  
  //   *  *** Illegal ***
  //   *  1 Weight is negative -> Exception
  @Test(expected = Exception.class)
  public void test201() {
    Graph<String> graph = emptyInstance();
    graph.set("a", "b", -1);
  }
  
  //   *  2 Source vertex is null -> Exception
  @Test(expected = Exception.class)
  public void test202() {
    Graph<String> graph = emptyInstance();
    graph.set(null, "b", 1);
  }
  
  //   *  3 Target vertex is null -> Exception
  @Test(expected = Exception.class)
  public void test203() {
    Graph<String> graph = emptyInstance();
    graph.set("a", null, 1);
  }
  
  //   *  *** Removing ***
  //   *  4 Edge not exists -> Return 0
  @Test
  public void test204() {
    Graph<String> graph = emptyInstance();
    assertEquals(0, graph.set("a", "b", 0));
  }
  
  //   *  5 Edge exists -> Remove it and return the previous weight
  @Test
  public void test205() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    assertEquals(1, graph.set("a", "b", 0));
    assertEquals(0, graph.set("a", "b", 0));
  }
  
  //   *  6 Source vertex not exists -> Return 0 and do nothing
  @Test
  public void test206() {
    Graph<String> graph = emptyInstance();
    graph.add("b");
    assertEquals(0, graph.set("a", "b", 0));
    assertEquals(1, graph.vertices().size());
    assertTrue(graph.vertices().contains("b"));
    assertTrue(graph.sources("b").isEmpty());
    assertEquals(0, graph.set("a", "b", 1));
    assertEquals(2, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
    assertTrue(graph.vertices().contains("b"));
    assertFalse(graph.sources("b").isEmpty());
    assertEquals(1, graph.set("a", "b", 0));
    assertTrue(graph.sources("b").isEmpty());
    assertTrue(graph.vertices().contains("b"));
    assertTrue(graph.vertices().contains("a"));
  }
  
  //   *  7 Target vertex not exists -> Return 0 and do nothing
  @Test
  public void test207() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    assertEquals(0, graph.set("a", "b", 0));
    assertEquals(1, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
    assertTrue(graph.targets("a").isEmpty());
    assertEquals(0, graph.set("a", "b", 1));
    assertEquals(2, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
    assertTrue(graph.vertices().contains("b"));
    assertFalse(graph.targets("a").isEmpty());
    assertEquals(1, graph.set("a", "b", 0));
    assertTrue(graph.targets("a").isEmpty());
    assertTrue(graph.vertices().contains("b"));
    assertTrue(graph.vertices().contains("a"));
  }
  
  //   *  8 Source vertex is null -> Exception
  @Test
  public void test208() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    try {
      graph.set(null, "b", 0);
      fail();
    } catch (Exception e) {
      // expected
    }
  }
  
  //   *  9 Target vertex is null -> Exception
  @Test(expected = Exception.class)
  public void test209() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    graph.set("a", null, 0);
  }
  
  //   *  *** Adding ***
  //   *  10 Source and target exists, edge not exists -> Add it and return 0
  @Test
  public void test210() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    assertEquals(0, graph.set("a", "b", 1));
  }
  
  //   *  11 Source exists, target not exists -> Add both and return 0
  @Test
  public void test211() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    assertEquals(0, graph.set("a", "b", 1));
    assertEquals(2, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
    assertEquals(1, graph.targets("a").size());
    assertTrue(graph.targets("a").containsKey("b"));
    assertEquals(1, (int) graph.targets("a").get("b"));
  }
  
  //   *  12 Source not exists, target exists -> Add both and return 0
  @Test
  public void test212() {
    Graph<String> graph = emptyInstance();
    graph.add("b");
    assertEquals(0, graph.set("a", "b", 1));
    assertEquals(2, graph.vertices().size());
    assertTrue(graph.vertices().contains("b"));
    assertEquals(1, graph.sources("b").size());
    assertTrue(graph.sources("b").containsKey("a"));
    assertEquals(1, (int) graph.sources("b").get("a"));
  }
  
  //   *  13 Source and target not exists -> Add the three and return 0
  @Test
  public void test213() {
    Graph<String> graph = emptyInstance();
    assertEquals(0, graph.set("a", "b", 1));
    assertEquals(2, graph.vertices().size());
    assertTrue(graph.vertices().contains("a"));
    assertTrue(graph.vertices().contains("b"));
    assertEquals(1, graph.targets("a").size());
    assertTrue(graph.targets("a").containsKey("b"));
    assertEquals(1, (int) graph.targets("a").get("b"));
    assertEquals(1, graph.sources("b").size());
    assertTrue(graph.sources("b").containsKey("a"));
    assertEquals(1, (int) graph.sources("b").get("a"));
  }
  
  //   *  14 Source is null -> Exception
  @Test(expected = Exception.class)
  public void test214() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    graph.set(null, "b", 0);
  }
  
  //   *  15 Target is null -> Exception
  @Test(expected = Exception.class)
  public void test215() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    graph.set("a", null, 0);
  }
  
  //   *  *** Modifying ***
  //   *  16 Edge exists -> Modify it and return the previous weight
  @Test
  public void test216() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    assertEquals(1, graph.set("a", "b", 2));
    assertEquals(2, graph.set("a", "b", 3));
  }
  
  //   *  17 Source is null -> Exception
  @Test(expected = Exception.class)
  public void test217() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    graph.set(null, "b", 0);
  }
  
  //   *  18 Target is null -> Exception
  @Test(expected = Exception.class)
  public void test218() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    graph.set("a", null, 0);
  }
  
  //   * 3 remove() - removing a vertex from the graph
  //   *  1 Vertex not exists -> Return false
  @Test
  public void test31() {
    Graph<String> graph = emptyInstance();
    assertFalse(graph.remove("a"));
  }
  
  //   *  2 Vertex exists but has no edge -> Remove it and return true
  @Test
  public void test32() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    assertTrue(graph.remove("a"));
    assertFalse(graph.vertices().contains("a"));
  }
  
  //   *  3 Vertex exists and has edge -> Remove it and return true
  @Test
  public void test33() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    assertTrue(graph.remove("a"));
    assertFalse(graph.vertices().contains("a"));
    assertTrue(graph.vertices().contains("b"));
    assertTrue(graph.sources("b").isEmpty());
  }
  
  //   *  4 Vertex is null -> Exception
  @Test(expected = Exception.class)
  public void test34() {
    Graph<String> graph = emptyInstance();
    graph.remove(null);
  }
}
