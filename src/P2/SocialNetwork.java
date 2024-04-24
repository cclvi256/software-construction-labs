package P2;

import P1.graph.Graph;

import java.util.Set;

public class SocialNetwork {
  private final Graph<Person> graph = Graph.empty();
  
  public Set<Person> getNeighbors(Person person) {
    throw new RuntimeException("not implemented");
  }
  
  public boolean addVertex(Person person) {
    throw new RuntimeException("not implemented");
  }
  
  public boolean addEdge(Person personFrom, Person personTo) {
    throw new RuntimeException("not implemented");
  }
  
  public int getDistance(Person personFrom, Person personTo) {
    throw new RuntimeException("not implemented");
  }
}
