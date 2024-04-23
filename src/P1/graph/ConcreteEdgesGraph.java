/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
  
  private final Set<String> vertices = new HashSet<>();
  private final List<Edge> edges = new ArrayList<>();
  
  // Abstraction function:
  //
  //   A graph with vertices and edges, Vertices are stored in vertices set, and
  //   the edges are stores in a list.
  // Representation invariant:
  //
  //   Vertices are not null, and edges are not null.
  // Safety from rep exposure:
  //
  //   All fields are private, String and Edge are immutable, though the Set and
  //   List is mutable, the graph is less used.
  
  // TODO constructor
  // It seems that it unnecessary to have a constructor with any operation.
  // Maybe using the default is not bad.
  ConcreteEdgesGraph() {
  }
  
  // TODO checkRep
  private boolean checkRep() {
    for (Edge edge : edges) {
      if (edge.checkRep()) {
        return false;
      }
    }
    return true;
  }
  
  @Override
  public boolean add(String vertex) {
    if (vertex == null) {
      throw new RuntimeException("Illegal parameters");
    }
    
    for (String i : vertices) {
      if (i.equals(vertex)) {
        return false;
      }
    }
    
    vertices.add(vertex);
    return true;
  }
  
  @Override
  public int set(String source, String target, int weight) {
    // Block the illegal parameters
    if (weight < 0 || source == null || target == null ||
        source.equals(target)) {
      throw new RuntimeException("Illegal parameters");
    }
    
    // Find the source and target
    String localSource = null;
    String localTarget = null;
    for (String i : vertices) {
      if (i.equals(source)) {
        localSource = i;
      }
      if (i.equals(target)) {
        localTarget = i;
      }
      if (localSource != null && localTarget != null) {
        break;
      }
    }
    
    // If the weight is 0, Removing
    
    if (weight == 0) {
      if (localSource == null || localTarget == null) {
        return 0;
      }
      for (Edge i : edges) {
        if (i.source.equals(localSource) && i.target.equals(localTarget)) {
          edges.remove(i);
          return i.weight;
        }
      }
      return 0;
    }
    
    // If the weight is positive, Adding or Modifying
    
    if (localSource == null) {
      vertices.add(source);
      localSource = source;
    }
    
    if (localTarget == null) {
      vertices.add(target);
      localTarget = target;
    }
    
    int oldWeight = 0;
    
    for (Edge i : edges) {
      if (i.source.equals(localSource) && i.target.equals(localTarget)) {
        edges.remove(i);
        oldWeight = i.weight;
        break;
      }
    }
    
    edges.add(new Edge(localSource, localTarget, weight));
    return oldWeight;
  }
  
  @Override
  public boolean remove(String vertex) {
    if (vertex == null) {
      throw new RuntimeException("Illegal parameters");
    }
    
    edges.removeIf(i -> i.source.equals(vertex) || i.target.equals(vertex));
    
    return vertices.remove(vertex);
  }
  
  @Override
  public Set<String> vertices() {
    
    return vertices;
  }
  
  @Override
  public Map<String, Integer> sources(String target) {
    if (target == null || !vertices.contains(target)) {
      throw new RuntimeException("Illegal parameters");
    }
    
    Map<String, Integer> res = new HashMap<>();
    
    for (Edge i : edges) {
      if (i.target.equals(target)) {
        res.put(i.source, i.weight);
      }
    }
    
    return res;
  }
  
  @Override
  public Map<String, Integer> targets(String source) {
    if (source == null || !vertices.contains(source)) {
      throw new RuntimeException("Illegal parameters");
    }
    
    Map<String, Integer> res = new HashMap<>();
    
    for (Edge i : edges) {
      if (i.source.equals(source)) {
        res.put(i.target, i.weight);
      }
    }
    
    return res;
  }
  
  // TODO toString()
  @Override
  public String toString() {
    String res = "";
    res += vertices.size();
    res += '\t';
    res += edges.size();
    res += '\n';
    
    boolean first = true;
    for (String i : vertices) {
      if (first) {
        first = false;
      } else {
        res += '\t';
      }
      res += i;
    }
    
    res += '\n';
    
    first = true;
    for (Edge i : edges) {
      if (first) {
        first = false;
      } else {
        res += "\t\t";
      }
      res += i.toString();
    }
    
    return res;
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
