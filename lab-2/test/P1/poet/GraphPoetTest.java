/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest
{
  
  // Testing strategy
  /*
    1. test poem() with empty input
    2. test poem() with input that has no bridge words
    3. test poem() with input that has bridge words
    4. test poem() with input that has multiple bridge words
   */
  
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled()
  {
    assert false; // make sure assertions are enabled with VM argument: -ea
  }
  
  // TODO tests
  
  @Test public void testPW1() throws IOException
  {
    GraphPoet gp = new GraphPoet(new File("src/P1/poet/mugar-omni-theater.txt"));
    String input = "";
    assertEquals("", gp.poem(input));
  }
  
  @Test public void testPW2() throws IOException
  {
    GraphPoet gp = new GraphPoet(new File("src/P1/poet/mugar-omni-theater.txt"));
    String input = "Test the system.";
    assertEquals("Test of the system.", gp.poem(input));
  }
  
  @Test public void testPW3() throws IOException
  {
    GraphPoet gp = new GraphPoet(new File("src/P1/poet/mugar-omni-theater.txt"));
    String input = "This is a test of the Mugar Omni Theater sound system.";
    assertEquals("This is a test of the Mugar Omni Theater sound system.", gp.poem(input));
  }
  
  @Test public void testPW4() throws IOException
  {
    GraphPoet gp = new GraphPoet(new File("src/P1/poet/mugar-omni-theater.txt"));
    String input = "Test the Mugar system.";
    assertEquals("Test of the Mugar system.", gp.poem(input));
  }
}
