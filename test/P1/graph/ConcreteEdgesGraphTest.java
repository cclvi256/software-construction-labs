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
   *  first, the number of vertices, then 1 tab
   *  second, the number of edges, then 1 LF
   *  third, the vertices, each vertex is separated by 1 tab, then 1 LF
   *  fourth, the edges, each edge is separated by 2 tab, then \0 as the end
   *   an edge is represented by two vertices and weight, separated by 1 tab
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
  public void testCEG1() {
    Graph<String> graph = emptyInstance();
    assertEquals("0\t0", graph.toString().substring(0, 3));
  }
  
  @Test
  public void testCEG2() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    String graphString = graph.toString();
    String[] parts = graphString.split("\n");
    assertEquals(2, parts.length);
    int verticesNum = Integer.parseInt(parts[0].split("\t")[0]);
    int edgesNum = Integer.parseInt(parts[0].split("\t")[1]);
    String[] vertices = parts[1].split("\t");
    assertEquals(1, verticesNum);
    assertEquals(0, edgesNum);
    assertEquals("a", vertices[0]);
  }
  
  @Test public void testCEG3() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.add("c");
    String graphString = graph.toString();
    String[] parts = graphString.split("\n");
    assertEquals(2, parts.length);
    int verticesNum = Integer.parseInt(parts[0].split("\t")[0]);
    int edgesNum = Integer.parseInt(parts[0].split("\t")[1]);
    String[] vertices = parts[1].split("\t");
    assertEquals(3, verticesNum);
    assertEquals(0, edgesNum);
    assertEquals("a", vertices[0]);
    assertEquals("b", vertices[1]);
    assertEquals("c", vertices[2]);
  }
  
  @Test public void testCEG4() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.set("a", "b", 1);
    String graphString = graph.toString();
    String[] parts = graphString.split("\n");
    int verticesNum = Integer.parseInt(parts[0].split("\t")[0]);
    int edgesNum = Integer.parseInt(parts[0].split("\t")[1]);
    String[] vertices = parts[1].split("\t");
    String[] edges = parts[2].split("\t\t");
    assertEquals(2, verticesNum);
    assertEquals(1, edgesNum);
    assertEquals("a", vertices[0]);
    assertEquals("b", vertices[1]);
    assertEquals("a\tb\t1", edges[0]);
  }
  
  @Test public void testCEG5() {
    Graph<String> graph = emptyInstance();
    graph.add("a");
    graph.add("b");
    graph.add("c");
    graph.set("a", "b", 1);
    graph.set("b", "c", 2);
    String graphString = graph.toString();
    String[] parts = graphString.split("\n");
    int verticesNum = Integer.parseInt(parts[0].split("\t")[0]);
    int edgesNum = Integer.parseInt(parts[0].split("\t")[1]);
    String[] vertices = parts[1].split("\t");
    String[] edges = parts[2].split("\t\t");
    assertEquals(3, verticesNum);
    assertEquals(2, edgesNum);
    assertEquals("a", vertices[0]);
    assertEquals("b", vertices[1]);
    assertEquals("c", vertices[2]);
    assertEquals("a\tb\t1", edges[0]);
    assertEquals("b\tc\t2", edges[1]);
  }
  /*
   * Testing Edge...
   */
  
  // Testing strategy for Edge
  //   TODO
  
  // TODO tests for operations of Edge
  
}
