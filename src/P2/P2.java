package P2;

/**
 * A class where the main method is located.
 */
public class P2 {
  public static void main(String[] args) {
    SocialNetwork graph = new SocialNetwork();
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
