/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;

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
   *  2 Vertex exists but has no eddge -> Remove it and return true
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
}
