package P2;

import P1.graph.Graph;

import java.util.*;

public class SocialNetwork {
  private final Graph<Person> graph = Graph.empty();
  
  public Set<Person> getNeighbors(Person person) {
    return graph.targets(person).keySet();
  }
  
  public boolean addVertex(Person person) {
    if (person == null) {
      throw new RuntimeException("Illegal parameters");
    }
    
    if (graph.vertices().contains(person)) {
      return false;
    }
    
    graph.add(person);
    return true;
  }
  
  public boolean addEdge(Person personFrom, Person personTo) {
    if (personFrom == null || personTo == null) {
      throw new RuntimeException("Illegal parameters");
    }
    
    if (graph.targets(personFrom).containsKey(personTo)) {
      return false;
    }
    
    graph.set(personFrom, personTo, 1);
    return true;
  }
  
  public int getDistance(Person personFrom, Person personTo) {
    if (personFrom == null || personTo == null) {
      throw new RuntimeException("Illegal parameters");
    }
    
    if (!graph.vertices().contains(personFrom) ||
        !graph.vertices().contains(personTo)) {
      throw new RuntimeException("Illegal parameters");
    }
    
    Queue<Person> queue = new LinkedList<>();
    
    Map<Person, Integer> distance = new HashMap<>();
    
    for (Person p : graph.vertices()) {
      distance.put(p, -1);
    }
    
    distance.put(personFrom, 0);
    queue.add(personFrom);
    
    while(!queue.isEmpty()) {
      Person current = queue.poll();
      
      for (Person neighbor : graph.targets(current).keySet()) {
        if (distance.get(neighbor) == -1) {
          distance.put(neighbor, distance.get(current) + 1);
          queue.add(neighbor);
        }
      }
    }
    
    return distance.get(personTo);
  }
}
