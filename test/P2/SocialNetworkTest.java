package P2;

import org.junit.Test;
import static org.junit.Assert.*;

public class SocialNetworkTest {
  @Test(expected = AssertionError.class)
  public void testAssertionEnabled() {
    assert false;
  }
  
  // Testing strategy
  /*
  All is the same as the previous one in Lab 1.
   */
  
  @Test
  public void testAssertionsEnabled() {
    assert true;
  }
  
  @Test
  public void sameVertex() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    Person spike = new Person("Spike");
    
    graph.addVertex(tom);
    graph.addVertex(spike);
    
    graph.addEdge(tom, spike);
    graph.addEdge(spike, tom);
    
    assertEquals(0, graph.getDistance(tom, tom));
    assertEquals(0, graph.getDistance(spike, spike));
  }
  
  @Test
  public void adjacentVertices() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    Person spike = new Person("Spike");
    Person butch = new Person("Butch");
    
    graph.addVertex(tom);
    graph.addVertex(spike);
    graph.addVertex(butch);
    
    graph.addEdge(tom, spike);
    graph.addEdge(spike, tom);
    graph.addEdge(spike, butch);
    graph.addEdge(butch, spike);
    
    assertEquals(1, graph.getDistance(tom, spike));
    assertEquals(1, graph.getDistance(spike, tom));
    assertEquals(1, graph.getDistance(spike, butch));
    assertEquals(1, graph.getDistance(butch, spike));
  }
  
  @Test
  public void farVertices() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    Person spike = new Person("Spike");
    Person butch = new Person("Butch");
    Person lily = new Person("Lily");
    Person jerry = new Person("Jerry");
    Person tyke = new Person("Tyke");
    Person george = new Person("George");
    Person nibbles = new Person("Nibbles");
    
    graph.addVertex(tom);
    graph.addVertex(spike);
    graph.addVertex(butch);
    graph.addVertex(lily);
    graph.addVertex(jerry);
    graph.addVertex(tyke);
    graph.addVertex(george);
    graph.addVertex(nibbles);
    
    graph.addEdge(tom, spike);
    graph.addEdge(spike, tom);
    graph.addEdge(spike, butch);
    graph.addEdge(butch, spike);
    graph.addEdge(butch, jerry);
    graph.addEdge(jerry, butch);
    graph.addEdge(jerry, tyke);
    graph.addEdge(tyke, jerry);
    graph.addEdge(tyke, george);
    graph.addEdge(george, tyke);
    graph.addEdge(george, nibbles);
    graph.addEdge(nibbles, george);
    
    // tom -> spike -> butch -> jerry -> tyke -> george -> nibbles
    assertEquals(1, graph.getDistance(tom, spike));
    assertEquals(2, graph.getDistance(tom, butch));
    assertEquals(3, graph.getDistance(tom, jerry));
    assertEquals(4, graph.getDistance(tom, tyke));
    assertEquals(5, graph.getDistance(tom, george));
    assertEquals(6, graph.getDistance(tom, nibbles));
    assertEquals(1, graph.getDistance(spike, butch));
    assertEquals(2, graph.getDistance(spike, jerry));
    assertEquals(3, graph.getDistance(spike, tyke));
    assertEquals(4, graph.getDistance(spike, george));
    assertEquals(5, graph.getDistance(spike, nibbles));
    assertEquals(1, graph.getDistance(butch, jerry));
    assertEquals(2, graph.getDistance(butch, tyke));
    assertEquals(3, graph.getDistance(butch, george));
    assertEquals(4, graph.getDistance(butch, nibbles));
    assertEquals(1, graph.getDistance(jerry, tyke));
    assertEquals(2, graph.getDistance(jerry, george));
    assertEquals(3, graph.getDistance(jerry, nibbles));
    assertEquals(1, graph.getDistance(tyke, george));
    assertEquals(2, graph.getDistance(tyke, nibbles));
    assertEquals(1, graph.getDistance(george, nibbles));
    
    // Reverse edges
    assertEquals(1, graph.getDistance(spike, tom));
    assertEquals(2, graph.getDistance(butch, tom));
    assertEquals(3, graph.getDistance(jerry, tom));
    assertEquals(4, graph.getDistance(tyke, tom));
    assertEquals(5, graph.getDistance(george, tom));
    assertEquals(6, graph.getDistance(nibbles, tom));
    assertEquals(1, graph.getDistance(butch, spike));
    assertEquals(2, graph.getDistance(jerry, spike));
    assertEquals(3, graph.getDistance(tyke, spike));
    assertEquals(4, graph.getDistance(george, spike));
    assertEquals(5, graph.getDistance(nibbles, spike));
    assertEquals(1, graph.getDistance(jerry, butch));
    assertEquals(2, graph.getDistance(tyke, butch));
    assertEquals(3, graph.getDistance(george, butch));
    assertEquals(4, graph.getDistance(nibbles, butch));
    assertEquals(1, graph.getDistance(tyke, jerry));
    assertEquals(2, graph.getDistance(george, jerry));
    assertEquals(3, graph.getDistance(nibbles, jerry));
    assertEquals(1, graph.getDistance(george, tyke));
    assertEquals(2, graph.getDistance(nibbles, tyke));
    assertEquals(1, graph.getDistance(nibbles, george));
  }
  
  @Test
  public void unreachable() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    Person spike = new Person("Spike");
    Person butch = new Person("Butch");
    Person lily = new Person("Lily");
    
    graph.addVertex(tom);
    graph.addVertex(spike);
    graph.addVertex(butch);
    graph.addVertex(lily);
    
    graph.addEdge(tom, spike);
    graph.addEdge(spike, tom);
    graph.addEdge(spike, butch);
    graph.addEdge(butch, spike);
    
    assertEquals(-1, graph.getDistance(tom, lily));
    assertEquals(-1, graph.getDistance(spike, lily));
    assertEquals(-1, graph.getDistance(butch, lily));
  }
  
  @Test
  public void usingNotAddedPersonWhenGettingDistance() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    Person spike = new Person("Spike");
    
    graph.addVertex(tom);
    
    assertThrows(Exception.class,
        () -> graph.getDistance(tom, spike));
    assertThrows(Exception.class,
        () -> graph.getDistance(spike, tom));
  }
  
  @Test
  public void usingNullVertex() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    Person spike = new Person("Spike");
    
    graph.addVertex(tom);
    graph.addVertex(spike);
    
    assertThrows(Exception.class,
        () -> graph.addEdge(tom, null));
    assertThrows(Exception.class,
        () -> graph.addEdge(null, tom));
    assertThrows(Exception.class,
        () -> graph.addEdge(null, null));
    assertThrows(Exception.class,
        () -> graph.getDistance(tom, null));
    assertThrows(Exception.class,
        () -> graph.getDistance(null, tom));
    assertThrows(Exception.class,
        () -> graph.getDistance(null, null));
  }
  
  @Test
  public void addingDuplicatedVertex() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    
    graph.addVertex(tom);
    
    assertFalse(graph.addVertex(tom));
  }
  
  @Test
  public void addingEdgeToOneself() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    
    graph.addVertex(tom);
    
    assertThrows(Exception.class,
        () -> graph.addEdge(tom, tom));
  }
  
  @Test
  public void sameLengthPathFromOneToAnother() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    Person spike = new Person("Spike");
    Person butch = new Person("Butch");
    Person lily = new Person("Lily");
    
    graph.addVertex(tom);
    graph.addVertex(spike);
    graph.addVertex(butch);
    graph.addVertex(lily);
    
    graph.addEdge(tom, spike);
    graph.addEdge(spike, butch);
    graph.addEdge(tom, lily);
    graph.addEdge(lily, butch);
    graph.addEdge(spike, tom);
    graph.addEdge(butch, spike);
    graph.addEdge(lily, tom);
    graph.addEdge(butch, lily);
    
    assertEquals(2, graph.getDistance(tom, butch));
  }
  
  @Test
  public void differentLengthPathFromOneToAnother() {
    SocialNetwork graph = new SocialNetwork();
    Person tom = new Person("Tom");
    Person spike = new Person("Spike");
    Person butch = new Person("Butch");
    Person lily = new Person("Lily");
    Person jerry = new Person("Jerry");
    
    graph.addVertex(tom);
    graph.addVertex(spike);
    graph.addVertex(butch);
    graph.addVertex(lily);
    graph.addVertex(jerry);
    
    graph.addEdge(spike, tom);
    graph.addEdge(tom, spike);
    graph.addEdge(butch, spike);
    graph.addEdge(spike, butch);
    graph.addEdge(tom, lily);
    graph.addEdge(lily, tom);
    graph.addEdge(lily, jerry);
    graph.addEdge(jerry, lily);
    graph.addEdge(jerry, butch);
    graph.addEdge(butch, jerry);
    
    assertEquals(2, graph.getDistance(tom, butch));
  }
}

