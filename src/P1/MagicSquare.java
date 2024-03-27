package P1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class MagicSquare {
  public static void main(String[] args) {
  
  }
  
  private static String getContent(String path) {
    try {
      return Files.readString(Paths.get(path));
    } catch (IOException e1) {
      System.out.println("File not found or corrupted.");
      System.exit(1);
    } catch (InvalidPathException e2) {
      System.out.println("Invalid path.");
      System.exit(1);
    }
    return null;
  }
  
  private static boolean check(String fileContent) {
    
    
    return false;
  }
  
  /**
   * spec: To check if a square provided in the file a legal one.
   *
   * @param fileName the file path and name, relative path is recommended.
   * @return If the input file is a legal Magic Square
   */
  private static boolean isLegalMagicSquare(String fileName) {
    
    
    // TODO: Modify the return value.
    return false;
  }
}
