/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
  
  private final Set<String> vertices = new HashSet<>();
  private final List<Edge> edges = new ArrayList<>();
  
  // Abstraction function:
  //   TODO
  //
  // Representation invariant:
  //   TODO
  // Safety from rep exposure:
  //   TODO
  
  // TODO constructor
  
  // TODO checkRep
  
  @Override
  public boolean add(String vertex) {
    throw new RuntimeException("not implemented");
  }
  
  @Override
  public int set(String source, String target, int weight) {
    throw new RuntimeException("not implemented");
  }
  
  @Override
  public boolean remove(String vertex) {
    throw new RuntimeException("not implemented");
  }
  
  @Override
  public Set<String> vertices() {
    throw new RuntimeException("not implemented");
  }
  
  @Override
  public Map<String, Integer> sources(String target) {
    throw new RuntimeException("not implemented");
  }
  
  @Override
  public Map<String, Integer> targets(String source) {
    throw new RuntimeException("not implemented");
  }
  
  // TODO toString()
  @Override
  public String toString() {
    throw new RuntimeException("not implemented");
  }
  
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * An Edge is a weighted directed edge from a source vertex to a target vertex.
 * It can't delete itself, but can change its weight. If deleted, it will be
 * processed by GC.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
  
  // TODO fields
  String source;
  String target;
  int weight;
  
  // Abstraction function:
  //   An edge from source to target with weight Edge.weight.
  // Representation invariant:
  //   weight >= 0
  //   source != null
  //   target != null
  //   source != target
  //   source and target are in the vertices set of the graph
  // Safety from rep exposure:
  //   All fields are private and immutable.
  
  // TODO constructor
  Edge(String source, String target, int weight) {
    this.source = source;
    this.target = target;
    this.weight = weight;
  }
  
  // TODO checkRep
  
  boolean checkRep() {
    return weight >= 0 && source != null && target != null &&
        !source.equals(target);
  }
  
  // TODO methods
  
  Edge setWeight(int weight) {
    return new Edge(source, target, weight);
  }
  
  // TODO toString()
  @Override
  public String toString() {
    return source.toString() + '\t' + target.toString() + '\t' + weight;
  }
}
