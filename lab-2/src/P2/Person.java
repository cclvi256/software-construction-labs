package P2;

/**
 * A class that represents a person.
 * As the generic parameter of the class Graph.
 */
public class Person {
  private final String name;
  
  public Person(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
}
