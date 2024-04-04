package P3;

import java.util.*;

/**
 * <p>Represents a graph where vertices are people and edges represent friendships.</p><br>
 * <p>This is an undirected graph, but can be easily modified to be directed.</p><br>
 * <p>This ADT is mutable, and no defensive copy is used due to its scale, so
 * be careful when using it.</p><br>
 */
public class FriendshipGraph {
  private ArrayList<Person> vertices;
  private ArrayList<Edge> edges;
  
  private ArrayList<Person> getNeighbors(Person v) {
    ArrayList<Person> neighbors = new ArrayList<Person>();
    
    for (Edge e : edges) {
      if (e.getHead().equals(v)) {
        neighbors.add(e.getTail());
      } else if (e.getTail().equals(v)) {
        neighbors.add(e.getHead());
      }
    }
    
    return neighbors;
  }
  
  public FriendshipGraph() {
    vertices = new ArrayList<Person>(0);
    edges = new ArrayList<Edge>(0);
  }
  
  public int getDistance (Person v1, Person v2) {
    if (v1 == null || v2 == null) {
      throw new IllegalArgumentException("Vertex cannot be null");
    }
    
    if (!vertices.contains(v1) || !vertices.contains(v2)) {
      throw new IllegalArgumentException("Vertex not in graph");
    }
    
    if (v1.equals(v2)) {
      return 0;
    }
    
    Queue<Person> queue = new LinkedList<Person>();
    HashMap<Person, Integer> distances = new HashMap<Person, Integer>();
    
    for (Person v : vertices) {
      distances.put(v, Integer.MAX_VALUE);
    }
    
    distances.put(v1, 0);
    queue.add(v1);
    
    while (!queue.isEmpty()) {
      Person current = queue.poll();
      
      for (Person neighbor : getNeighbors(current)) {
        if (distances.get(neighbor) == Integer.MAX_VALUE) {
          distances.put(neighbor, distances.get(current) + 1);
          queue.add(neighbor);
        }
      }
    }
    
    return distances.get(v2);
  }
  
  public void addVertex(Person p) {
    if (p == null) {
      throw new IllegalArgumentException("Vertex cannot be null");
    }
    
    if (vertices.contains(p)) {
      throw new IllegalArgumentException("Vertex already in graph");
    }
    
    vertices.add(p);
  }
  
  public void addEdge(Person p1, Person p2) {
    if (p1 == null || p2 == null) {
      throw new IllegalArgumentException("Vertex cannot be null");
    }
    
    if (!vertices.contains(p1) || !vertices.contains(p2)) {
      throw new IllegalArgumentException("Vertex not in graph");
    }
    
    if (p1.equals(p2)) {
      throw new IllegalArgumentException("Cannot add self-loop");
    }
    
    if (edges.contains(new Edge(p1, p2))) {
      throw new IllegalArgumentException("Edge already in graph");
    }
    
    edges.add(new Edge(p1, p2));
  }
  
  public static void main(String[] args) {
    FriendshipGraph graph = new FriendshipGraph();
    Person rachel = new Person("Rachel");
    Person ross = new Person("Ross");
    Person ben = new Person("Ben");
    Person kramer = new Person("Kramer");
    graph.addVertex(rachel);
    graph.addVertex(ross);
    graph.addVertex(ben);
    graph.addVertex(kramer);
    graph.addEdge(rachel, ross);
    graph.addEdge(ross, rachel);
    graph.addEdge(ross, ben);
    graph.addEdge(ben, ross);
    System.out.println(graph.getDistance(rachel, ross)); // 1
    System.out.println(graph.getDistance(rachel, ben)); // 2
    System.out.println(graph.getDistance(rachel, rachel)); // 0
    System.out.println(graph.getDistance(rachel, kramer)); // -1
  }
}