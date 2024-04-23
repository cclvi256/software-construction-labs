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
public class ConcreteVerticesGraph<L> implements Graph<L>
{
  
  private final List<Vertex<L>> vertices = new ArrayList<>();
  
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
  public ConcreteVerticesGraph()
  {
    // It seems that I should do nothing in the constructor.
  }
  
  // TODO checkRep
  
  private void checkRep()
  {
    // Not null collection
    assertNotNull(vertices);
    
    // Not null label
    for (Vertex<L> vertex : vertices)
    {
      assertNotNull(vertex);
      assertTrue(vertex.checkRep());
    }
    
    // Not duplicate label
    for (int i = 0; i < vertices.size(); i++)
    {
      for (int j = i + 1; j < vertices.size(); j++)
      {
        assertNotEquals(vertices.get(i).getLabel(), vertices.get(j).getLabel());
        assertNotNull(vertices.get(i).getTargets());
      }
    }
    
    // Considering when adding a new key-value pair in a map will replace the
    // old value instead of adding a new one. So I have no way to check if
    //there's duplicated targets.
  }
  
  @Override
  public boolean add(L vertex)
  {
    if (vertex == null)
    {
      throw new RuntimeException("Vertex is null");
    }
    
    for (Vertex<L> v : vertices)
    {
      if (v.getLabel().equals(vertex))
      {
        return false;
      }
    }
    
    vertices.add(new Vertex<>(vertex));
    checkRep();
    return true;
  }
  
  @Override
  public int set(L source, L target, int weight)
  {
    // Block the illegal inputs
    if (weight < 0 || source == null || target == null)
    {
      throw new RuntimeException("Invalid input");
    }
    
    // Find the source and target vertex
    Vertex<L> localSource = null;
    Vertex<L> localTarget = null;
    
    for (Vertex<L> vertex : vertices)
    {
      if (vertex.getLabel().equals(source))
      {
        localSource = vertex;
      }
      if (vertex.getLabel().equals(target))
      {
        localTarget = vertex;
      }
      if (localSource != null && localTarget != null)
      {
        break;
      }
    }
    
    // When the weight is 0, it means to remove the edge.
    if (weight == 0)
    {
      // But it's necessary to check if the vertices exist.
      if (localSource == null || localTarget == null)
      {
        return 0;
      }
      
      // And if the edge exists.
      if (localSource.getTargets().containsKey(localTarget))
      {
        int res = localSource.getTargets().get(localTarget);
        localSource.removeTarget(localTarget);
        checkRep();
        return res;
      }
      else
      {
        return 0;
      }
    }
    else
    {
      // Now it comes to adding or modifying.
      // First add the vertices if they don't exist.
      if (localSource == null)
      {
        localSource = new Vertex<>(source);
        vertices.add(localSource);
      }
      
      if (localTarget == null)
      {
        localTarget = new Vertex<>(target);
        vertices.add(localTarget);
      }
      
      // Then add the edge or modify.
      if (localSource.getTargets().containsKey(localTarget))
      {
        int res = localSource.getTargets().get(localTarget);
        localSource.modifyTargetWeight(localTarget, weight);
        checkRep();
        return res;
      }
      else
      {
        localSource.addTarget(localTarget, weight);
        checkRep();
        return 0;
      }
    }
    
  }
  
  @Override
  public boolean remove(L vertex)
  {
    if (vertex == null)
    {
      throw new RuntimeException("Vertex is null");
    }
    
    Vertex<L> vertexCopy = new Vertex<>(vertex);
    if (vertices.contains(vertexCopy))
    {
      for (Vertex<L> v : vertices)
      {
        v.removeTarget(vertexCopy);
      }
      vertices.remove(vertexCopy);
      
      checkRep();
      return true;
    }
    else
    {
      return false;
    }
  }
  
  @Override
  public Set<L> vertices()
  {
    Set<L> res = new HashSet<>();
    for (Vertex<L> vertex : vertices)
    {
      res.add(vertex.getLabel());
    }
    return res;
  }
  
  @Override
  public Map<L, Integer> sources(L target)
  {
    // TODO This method fails some tests
    Map<L, Integer> res = new HashMap<>();
    
    boolean found = false;
    for (Vertex<L> vertex : vertices)
    {
      if (vertex.getLabel().equals(target))
      {
        found = true;
        break;
      }
    }
    
    if (!found)
    {
      throw new RuntimeException("Target vertex not found");
    }
    
    for (Vertex<L> vertex : vertices)
    {
      for (Map.Entry<Vertex<L>, Integer> entry : vertex.getTargets().entrySet())
      {
        if (entry.getKey().getLabel().equals(target))
        {
          res.put(vertex.getLabel(), entry.getValue());
        }
      }
    }
    return res;
  }
  
  @Override
  public Map<L, Integer> targets(L source)
  {
    for (Vertex<L> vertex : vertices)
    {
      if (vertex.getLabel().equals(source))
      {
        Map<L, Integer> res = new HashMap<>();
        for (Map.Entry<Vertex<L>, Integer> entry : vertex.getTargets()
            .entrySet())
        {
          res.put(entry.getKey().getLabel(), entry.getValue());
        }
        return res;
      }
    }
    throw new RuntimeException("Source vertex not found");
  }
  
  // TODO toString()
  @Override
  public String toString()
  {
    String res = vertices.size() + "\t";
    int edgeNum = 0;
    for (Vertex<L> vertex : vertices)
    {
      edgeNum += vertex.getTargets().size();
    }
    res += edgeNum + "\n";
    
    boolean first = true;
    for (Vertex<L> vertex : vertices)
    {
      if (first)
      {
        first = false;
      }
      else
      {
        res += "\t";
      }
      res += vertex.getLabel().toString();
    }
    
    res += "\n";
    
    first = true;
    for (Vertex<L> vertex : vertices)
    {
      for (Map.Entry<Vertex<L>, Integer> entry : vertex.getTargets().entrySet())
      {
        if (first)
        {
          first = false;
        }
        else
        {
          res += "\t\t";
        }
        res += vertex.getLabel() + "\t" + entry.getKey().getLabel() + "\t"
            + entry.getValue();
      }
    }
    
    return res;
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
class Vertex<L>
{
  
  // TODO fields
  private L label;
  private Map<Vertex<L>, Integer> targets = new HashMap<>();
  
  // Abstraction function:
  //   Each vertex which is pointed by an arc whose source is this vertex will
  //   be stored as a key in the map targets, and the weight as the key-value
  // Representation invariant:
  //   Label should not be null, and targets should not be null, weight should be positive
  // Safety from rep exposure:
  //   All fields are private, and label is a String, which is immutable.
  //   Targets is mutable, but it is returned with defensive copy, so it's safe.
  
  // TODO constructor
  
  Vertex (L label)
  {
    this.label = label;
  }
  
  @Override
  public boolean equals(Object that)
  {
    if (that instanceof Vertex<?>)
    {
      return this.label.equals(((Vertex<?>) that).label);
    }
    return false;
  }
  
  Vertex(L label, Map<Vertex<L>, Integer> targets)
  {
    this.label = label;
    this.targets = new HashMap<>(targets);
  }
  
  // TODO checkRep
  
  protected boolean checkRep()
  {
    if (label == null)
    {
      return false;
    }
    if (targets == null)
    {
      return false;
    }
    for (Map.Entry<Vertex<L>, Integer> entry : targets.entrySet())
    {
      if (entry.getKey() == null || entry.getValue() <= 0)
      {
        return false;
      }
    }
    return true;
  }
  
  // TODO methods
  
  public L getLabel()
  {
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
  public boolean setLabel(L label)
  {
    if (label == null)
    {
      return false;
    }
    this.label = label;
    return true;
  }
  
  public Map<Vertex<L>, Integer> getTargets()
  {
    return new HashMap<>(targets);
  }
  
  public boolean addTarget(Vertex<L> target, int weight)
  {
    if (target == null || weight <= 0 || targets.containsKey(target))
    {
      return false;
    }
    
    targets.put(target, weight);
    return true;
  }
  
  public boolean removeTarget(Vertex<L> target)
  {
    if (target == null || !targets.containsKey(target))
    {
      return false;
    }
    
    targets.remove(target);
    return true;
  }
  
  public boolean modifyTargetWeight(Vertex<L> target, int weight)
  {
    if (target == null || weight <= 0 || !targets.containsKey(target))
    {
      return false;
    }
    
    targets.replace(target, weight);
    return true;
  }
  
  // TODO toString()
  
  @Override
  public String toString()
  {
    String res = this.label + ": ";
    for (Map.Entry<Vertex<L>, Integer> entry : targets.entrySet())
    {
      res += entry.getKey().getLabel() + "\t" + entry.getValue() + "\t\t";
    }
    
    res = res.substring(0, res.length() - 2);
    return res;
  }
}
