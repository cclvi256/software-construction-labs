/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

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
  //   The vertices is all the added vertices in the graph
  //   Each of the vertex has it field:
  //     Label: the label of the vertex
  //     Targets: the every targets of each vertex, which is a map from the
  //     target vertex to the weight
  // Representation invariant:
  //   The label of each vertex should not be null
  //   The labels should not be the same
  //   The targets should not be null
  //   The weight should be positive
  //   The targets should not contain the same target vertex
  //
  // Safety from rep exposure:
  //   TODO
  //   All of the fields are private, the label is a String, which is immutable,
  //   and the targets is a Map, which is mutable, but it should be got and
  //   returned with defensive copy
  // TODO constructor
  public ConcreteVerticesGraph() {
    // It seems that I should do nothing in the constructor.
  }
  
  // TODO checkRep
  
  private void checkRep() {
    // Not null collection
    assertNotNull(vertices);
    
    // Not null label
    for (Vertex vertex : vertices) {
      assertNotNull(vertex);
      assertTrue(vertex.checkRep());
    }
    
    // Not duplicate label
    for (int i = 0; i < vertices.size(); i++) {
      for (int j = i + 1; j < vertices.size(); j++) {
        assertNotEquals(vertices.get(i).getLabel(), vertices.get(j).getLabel());
        assertNotNull(vertices.get(i).getTargets());
      }
    }
    
    // Considering when adding a new key-value pair in a map will replace the
    // old value instead of adding a new one. So I have no way to check if
    //there's duplicated targets.
  }
  
  @Override
  public boolean add(String vertex) {
    if (vertex == null) {
      throw new RuntimeException("Vertex is null");
    }
    
    for (Vertex v : vertices) {
      if (v.getLabel().equals(vertex)) {
        return false;
      }
    }
    
    vertices.add(new Vertex(vertex));
    checkRep();
    return true;
  }
  
  @Override
  public int set(String source, String target, int weight) {
    
    // If source or target is null, throw exception.
    if (source == null || target == null) {
      throw new RuntimeException("Source or target is null");
    }
    
    // If source or target not exists, add them.
    Vertex sourceCopy = null;
    Vertex targetCopy = null;
    
    for(Vertex vertex : vertices) {
      if (vertex.getLabel().equals(source)) {   // Remember vertex is mutable
        sourceCopy = vertex;
      }
      if (vertex.getLabel().equals(target)) {
        targetCopy = vertex;
      }
      if (sourceCopy != null && targetCopy != null) {
        break;
      }
    }
    
    if(sourceCopy == null) {
      sourceCopy = new Vertex(source);
      vertices.add(sourceCopy);
    }
    
    if(targetCopy == null) {
      targetCopy = new Vertex(target);
      vertices.add(targetCopy);
    }
    
    // If weight is negative, throw exception.
    if (weight < 0) {
      throw new RuntimeException("Weight is negative");
    }
    
    // If weight is not negative, add, modify, or remove the edge.
    for (Vertex vertex : vertices) {
      
      // Wait until the source is found.
      if (vertex.getLabel().equals(source)) {
        
        // If weight is 0, remove the edge.
        if (weight == 0) {
          if (vertex.getTargets().containsKey(targetCopy)) {
            int res = vertex.getTargets().get(targetCopy);
            vertex.getTargets().remove(targetCopy);
            return res;
          } else {
            return 0;
          }
        } else {
          if (vertex.getTargets().containsKey(targetCopy)) {
            int res = vertex.getTargets().get(targetCopy);
            vertex.getTargets().put(targetCopy, weight);
            return res;
          } else {
            vertex.getTargets().put(targetCopy, weight);
            return 0;
          }
        }
      }
      break;
    }
    
    throw new RuntimeException("Source vertex not found");
  }
  
  @Override
  public boolean remove(String vertex) {
    if (vertex == null) {
      throw new RuntimeException("Vertex is null");
    }
    
    Vertex vertexCopy = new Vertex(vertex);
    if (vertices.contains(vertexCopy)) {
      vertices.remove(vertexCopy);
      checkRep();
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public Set<String> vertices() {
    throw new RuntimeException("not implemented");
  }
  
  @Override
  public Map<String, Integer> sources(String target) {
    Map<String, Integer> res = new HashMap<>();
    
    boolean found = false;
    for (Vertex vertex : vertices) {
      if (vertex.getLabel().equals(target)) {
        found = true;
        break;
      }
    }
    
    if (!found) {
      throw new RuntimeException("Target vertex not found");
    }
    
    for (Vertex vertex : vertices) {
      if (vertex.getTargets().containsKey(new Vertex(target))) {
        res.put(vertex.getLabel(), vertex.getTargets().get(new Vertex(target)));
      }
    }
    return res;
  }
  
  @Override
  public Map<String, Integer> targets(String source) {
    for (Vertex vertex : vertices) {
      if (vertex.getLabel().equals(source)) {
        Map<String, Integer> res = new HashMap<>();
        for (Map.Entry<Vertex, Integer> entry : vertex.getTargets()
            .entrySet()) {
          res.put(entry.getKey().getLabel(), entry.getValue());
        }
        return res;
      }
    }
    throw new RuntimeException("Source vertex not found");
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
  
  @Override
  public boolean equals(Object that) {
    if (that instanceof Vertex) {
      return this.label.equals(((Vertex) that).label);
    }
    return false;
  }
  
  Vertex(String label, Map<Vertex, Integer> targets) {
    this.label = label;
    this.targets = new HashMap<>(targets);
  }
  
  // TODO checkRep
  
  protected boolean checkRep() {
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
    
    if (res.length() > 2) {
      res = res.substring(0, res.length() - 2);
    } else {
      res = "";
    }
    return res;
  }
}
