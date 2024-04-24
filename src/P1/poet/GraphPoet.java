/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import P1.graph.Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * A graph-based poetry generator.
 *
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacency: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 *
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 *
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 *
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 *
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
  
  private final Graph<String> graph = Graph.empty();
  
  // Abstraction function:
  //   The graph consists of vertices representing words and edges representing
  //   the affinity.
  // Representation invariant:
  //   The graph is non-null.
  //   If possible, check if the graph's total degree is 0
  // Safety from rep exposure:
  //   All the fields are private and final, and despite the mutability of graph,
  //   we will never return it to any other class.
  
  /**
   * Create a new poet with the graph from corpus (as described above).
   *
   * @param corpus text file from which to derive the poet's affinity graph
   * @throws IOException if the corpus file cannot be found or read
   */
  public GraphPoet(File corpus) throws IOException {
    Graph<String> graph = Graph.empty();
    String[] words = new String[]{};
    
    FileInputStream fis = null;
    
    try {
      fis = new FileInputStream(corpus);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    String fileContent = new String(fis.readAllBytes());
    words = fileContent.split("\\s+");
    
    boolean first = true;
    String lastWord = null;
    for (String word : words) {
      // Preprocessing the word: change to lower case.
      word = word.toLowerCase();
      if (first) {
        first = false;
        graph.add(word);
      } else {
        Map<String, Integer> targets = graph.targets(lastWord);
        int formalWeight = targets.getOrDefault(word, 0);
        graph.set(lastWord, word, formalWeight + 1);
      }
      lastWord = word;
    }
  }
  
  // TODO checkRep
  private boolean checkRep() {
    return graph != null;
    // TODO if possible, check if the graph's total degree is 0
  }
  
  private String findBridge(String from, String to) {
    Map<String, Integer> sources = graph.sources(to);
    int maxWeight = 0;
    String bridge = null;
    for (String source : sources.keySet()) {
      if (graph.targets(from).containsKey(source)) {
        int weight = sources.get(source) + graph.targets(from).get(source);
        if (weight > maxWeight) {
          maxWeight = weight;
          bridge = source;
        }
      }
    }
    
    // Return the bridge word if it exists, otherwise return null.
    // If more than one bridge exists and share the same weight, return the first one.
    return bridge;
  }
  
  /**
   * Generate a poem.
   *
   * @param input string from which to create the poem
   * @return poem (as described above)
   */
  public String poem(String input) {
    String[] words = input.split("\\s+");
    // Maybe using StringBuilder is more efficient, and the StringBuilder is not
    // available in any other classes. So it is safe to use it here.
    StringBuilder poem = new StringBuilder();
    boolean first = true;
    String lastWord = null;
    for (String word : words) {
      poem.append(word);
      String reserveCase = word;
      word = word.toLowerCase();
      if (first) {
        first = false;
      } else {
        poem.append(' ');
        String bridge = findBridge(lastWord, word);
        if (bridge != null) {
          poem.append(bridge).append(' ');
        }
      }
      poem.append(reserveCase);
      lastWord = word;
    }
    
    return poem.toString();
  }
  
  // TODO toString()
  @Override public String toString() {
    return super.toString();
  }
}
