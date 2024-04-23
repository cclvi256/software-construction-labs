package P3;

/**
 * Represents an arc in a graph.
 * The arc is directed from the tail to the head.
 */
public class Edge {
  private final Person head;
  private final Person tail;
  
  public Edge(Person tail, Person head) {
    this.head = head;
    this.tail = tail;
  }
  
  public Person getHead() {
    return head;
  }
  
  public Person getTail() {
    return tail;
  }
}
