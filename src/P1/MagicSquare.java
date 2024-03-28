package P1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.InputMismatchException;

public class MagicSquare {
  public static void main(String[] args) {
  
  }
  
  private static String getContent(String path) throws IOException {
    return Files.readString(Paths.get(path));
  }
  
  /**
   * @param fileContent All the content from the file.
   * @return True if the fileContent is legal.
   * @throws InputMismatchException When calling the function with an illegal
   *                                matrix, the exception will be thrown.
   * @throws NumberFormatException  When the input contains non-integers, this
   *                                exception will be thrown.
   */
  private static int[][] parse(String fileContent)
      throws InputMismatchException {
    int[][] matrix = new int[3][3];
    
    String[] lines = fileContent.split("\n");
    
    if (lines.length != 3) {
      throw new InputMismatchException("Illegal matrix.");
    }
    
    for (int i = 0; i < 3; i++) {
      String[] line = lines[i].split("\t");
      if (line.length != 3) {
        throw new InputMismatchException("Illegal matrix.");
      }
      
      for (int j = 0; j < 3; j++) {
        try {
          matrix[i][j] = Integer.parseInt(line[j]);
        } catch (NumberFormatException e) {
          throw new InputMismatchException("Illegal matrix.");
        }
      }
    }
    
    return matrix;
  }
  
  /**
   * spec: To check if a square provided in the file a legal one.
   *
   * @param fileName the file path and name, relative path is recommended.
   * @return If the input file is a legal Magic Square
   */
  private static boolean isLegalMagicSquare(String fileName) {
    try {
      String cont = MagicSquare.getContent(fileName);
      int[][] matrix = parse(cont);
      return matrix[0][0]+matrix[0][1]+matrix[0][2]==15 &&
          matrix[1][0]+matrix[1][1]+matrix[1][2]==15 &&
          matrix[2][0]+matrix[2][1]+matrix[2][2]==15 &&
          matrix[0][0]+matrix[1][0]+matrix[2][0]==15 &&
          matrix[0][1]+matrix[1][1]+matrix[2][1]==15 &&
          matrix[0][2]+matrix[1][2]+matrix[2][2]==15 &&
          matrix[0][0]+matrix[1][1]+matrix[2][2]==15 &&
          matrix[0][2]+matrix[1][1]+matrix[2][0]==15;
    } catch (IOException e) {
      System.out.println("File not found.");
      System.exit(1);
    } catch (InputMismatchException e) {
      System.out.println("Illegal matrix.");
      System.exit(1);
    }
    
    return false;
  }
}
