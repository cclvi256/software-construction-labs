package P1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MagicSquare {
  public static void main(String[] args) {
    System.out.println("Part 1, Testing Magic Square: ");
    System.out.println("Processing 1.txt");
    boolean bool1 = MagicSquare.isLegalMagicSquare("src/P1/txt/1.txt");
    System.out.println("1.txt: " + bool1);
    
    System.out.println("\nProcessing 2.txt");
    boolean bool2 = MagicSquare.isLegalMagicSquare("src/P1/txt/2.txt");
    System.out.println("2.txt: " + bool2);
    
    System.out.println("\nProcessing 3.txt");
    boolean bool3 = MagicSquare.isLegalMagicSquare("src/P1/txt/3.txt");
    System.out.println("3.txt: " + bool3);
    
    System.out.println("\nProcessing 4.txt");
    boolean bool4 = MagicSquare.isLegalMagicSquare("src/P1/txt/4.txt");
    System.out.println("4.txt: " + bool4);
    
    System.out.println("\nProcessing 5.txt");
    boolean bool5 = MagicSquare.isLegalMagicSquare("src/P1/txt/5.txt");
    System.out.println("5.txt: " + bool5);
    
    System.out.println("\n\nPart 2, Generating Magic Squares: ");
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the size of the magic square: ");
    int n = scanner.nextInt();
    System.out.println("The magic square is: ");
    MagicSquare.generateMagicSquare(n);
  }
  
  private static ArrayList<ArrayList<Integer>> getMatrix(String path)
      throws InvalidPathException, IOException, InputMismatchException {
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    
    int firstLineLength = 0;
    for (String line : Files.readAllLines(Paths.get(path))) {
      ArrayList<Integer> row = new ArrayList<Integer>();
      
      for (String num : line.split("\t")) {
        row.add(Integer.parseInt(num));
      }
      
      if (firstLineLength == 0) {
        firstLineLength = row.size();
      } else if (firstLineLength != row.size()) {
        throw new InputMismatchException("Matrix is not square");
      }
      
      matrix.add(row);
    }
    
    if (matrix.size() != firstLineLength) {
      throw new InputMismatchException("Matrix is not square");
    }
    
    return matrix;
  }
  
  private static boolean check(ArrayList<ArrayList<Integer>> matrix) {
    int sum = 0;
    for (int i = 0; i < matrix.size(); i++) {
      sum += matrix.get(0).get(i);
    }
    
    int sumDiag1 = 0;
    int sumDiag2 = 0;
    for (int i = 0; i < matrix.size(); i++) {
      int sumRow = 0;
      int sumCol = 0;
      for (int j = 0; j < matrix.size(); j++) {
        sumRow += matrix.get(i).get(j);
        sumCol += matrix.get(j).get(i);
      }
      
      if (sumRow != sum || sumCol != sum) {
        return false;
      }
      
      sumDiag1 += matrix.get(i).get(i);
      sumDiag2 += matrix.get(i).get(matrix.size() - i - 1);
    }
    
    return sumDiag1 == sum && sumDiag2 == sum;
  }
  
  public static boolean isLegalMagicSquare(String path) {
    try {
      ArrayList<ArrayList<Integer>> matrix = getMatrix(path);
      return check(matrix);
    } catch (InvalidPathException | NumberFormatException |
             InputMismatchException | IOException e) {
      e.printStackTrace();
    }
    
    return false;
  }
  
  public static boolean generateMagicSquare(int n) {
    if (n % 2 == 0 || n < 0) {
      System.out.println("The size of the magic square must be a positive odd" +
          " number");
      return false;
    }
    
    int[][] magic = new int[n][n];
    int row = 0, col = n / 2, i, j, square = n * n;
    for (i = 1; i <= square; i++) {
      magic[row][col] = i;
      if (i % n == 0) {
        row++;
      } else {
        if (row == 0) {
          row = n - 1;
        } else {
          row--;
        }
        if (col == (n - 1)) {
          col = 0;
        } else {
          col++;
        }
      }
    }
    try {
      for (i = 0; i < n; i++) {
        Path filePath = Paths.get("src/P1/txt/6.txt");
        for (j = 0; j < n; j++) {
          System.out.print(magic[i][j] + "\t");
          Files.write(filePath,
              (magic[i][j] + "\t").getBytes());
        }
        System.out.println();
        Files.write(filePath, "\n".getBytes());
      }
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
