/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * <p>
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * <p>
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
  
  /*
   * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
   */
  @Override
  public Graph<String> emptyInstance() {
    return new ConcreteEdgesGraph();
  }
  
  /*
   * Testing ConcreteEdgesGraph...
   */
  
  // Testing strategy for ConcreteEdgesGraph.toString()
  
  /*
   * 1 What should this string contains
   *  1 The list of the vertices, format V = {v1, v2, v3, ...}
   *  2 The list of the edges, format E = {{v1, v2, weight1}, {v2, v3, weight2}, ...}
   *
   * 2 Which cases
   *  1 The graph is empty
   *  2 The graph has one vertex
   *  3 The graph has multiple vertices, no edge
   *  4 The graph has multiple vertices, one edge
   *  5 The graph has multiple vertices, multiple edges
   */
  
  // TODO tests for ConcreteEdgesGraph.toString()
  
  @Test
  public void test1() {
    Graph<String> graph = emptyInstance();
    assertEquals("V = {}\nE = {}", graph.toString());
  }
  
  @Test
  public void test2() {
    Graph<String> graph = emptyInstance();
    graph.add("v1");
    assertEquals("V = {v1}\nE = {}", graph.toString());
  }
  
  @Test
  public void test3() {
    Graph<String> graph = emptyInstance();
    graph.add("v1");
    graph.add("v2");
    graph.add("v3");
    String vertices =
        graph.toString().substring(4, graph.toString().indexOf("\nE ="));
    vertices = vertices.substring(1, vertices.length() - 1);
    String[] vertexArray = vertices.split(", ");
    assertEquals(3, vertexArray.length);
    assertTrue(vertices.contains("v1"));
    assertTrue(vertices.contains("v2"));
    assertTrue(vertices.contains("v3"));
  }
  
  @Test
  public void test4() {
    Graph<String> graph = emptyInstance();
    graph.add("v1");
    graph.add("v2");
    graph.set("v1", "v2", 1);
    String edges =
        graph.toString().substring(graph.toString().indexOf("\nE =") + 5);
    edges = edges.substring(2, edges.length() - 2);
    String[] edgeArray = edges.split("}, ?");
    assertEquals(1, edgeArray.length);
    assertTrue(edges.contains("v1, v2, 1"));
  }
  
  @Test
  public void test5() {
    Graph<String> graph = emptyInstance();
    graph.add("v1");
    graph.add("v2");
    graph.add("v3");
    graph.set("v1", "v2", 1);
    graph.set("v2", "v3", 2);
    String edges =
        graph.toString().substring(graph.toString().indexOf("\nE =") + 5);
    edges = edges.substring(2, edges.length() - 2);
    String[] edgeArray = edges.split("}, ?");
    assertEquals(2, edgeArray.length);
    assertTrue(edges.contains("v1, v2, 1"));
    assertTrue(edges.contains("v2, v3, 2"));
  }
  
  /*
   * Testing Edge...
   */
  
  // Testing strategy for Edge
  //   TODO
  
  // TODO tests for operations of Edge
  
}
