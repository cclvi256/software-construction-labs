/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;

import java.util.*;

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
  
  private final List<Vertex> vertices = new ArrayList<>();
  
  // Abstraction function:
  //   TODO
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
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
  
  // TODO fields
  private String label;
  private Map<Vertex, Integer> targets = new HashMap<>();
  
  // Abstraction function:
  //   Each vertex which is pointed by an arc whose source is this vertex will
  //   be stored as a key in the map targets, and the weight as the key-value
  // Representation invariant:
  //   Label should not be null, and targets should not be null, weight should be positive
  // Safety from rep exposure:
  //   All fields are private, and label is a String, which is immutable.
  //   Targets is mutable, but it is returned with defensive copy, so it's safe.
  
  // TODO constructor
  
  Vertex() {
    label = "";
  }
  
  Vertex(String label) {
    this.label = label;
  }
  
  Vertex(String label, Map<Vertex, Integer> targets) {
    this.label = label;
    this.targets = new HashMap<>(targets);
  }
  
  // TODO checkRep
  
  private boolean checkRep() {
    if (label == null) {
      return false;
    }
    if (targets == null) {
      return false;
    }
    for (Map.Entry<Vertex, Integer> entry : targets.entrySet()) {
      if (entry.getKey() == null || entry.getValue() <= 0) {
        return false;
      }
    }
    return true;
  }
  
  // TODO methods
  
  public String getLabel() {
    return label;
  }
  
  /**
   * Set the label of the vertex.
   * <p>
   * Warning: You may change a vertex's label, but you should keep the labels distinct.
   *
   * @param label the new label
   * @return true if the label is set successfully, false if the label is null
   */
  public boolean setLabel(String label) {
    if (label == null) {
      return false;
    }
    this.label = label;
    return true;
  }
  
  public Map<Vertex, Integer> getTargets() {
    return new HashMap<>(targets);
  }
  
  public boolean addTarget(Vertex target, int weight) {
    if (target == null || weight <= 0 || targets.containsKey(target)) {
      return false;
    }
    
    targets.put(target, weight);
    return true;
  }
  
  // TODO toString()
  
  @Override
  public String toString() {
    String res = this.label + ": ";
    for (Map.Entry<Vertex, Integer> entry : targets.entrySet()) {
      res += entry.getKey().getLabel() + "\t" + entry.getValue() + "\t\t";
    }
    return res;
  }
}
