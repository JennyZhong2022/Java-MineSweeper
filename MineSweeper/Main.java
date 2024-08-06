package MineSweeper;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Grid grid = new Grid(10, 10, 10);
    Scanner scanner = new Scanner(System.in);

    while (true) {
      grid.printGrid();
      System.out.println("Enter number(1-10) of row, number(1-10) of column to reveal a cell");
      int row = scanner.nextInt();
      int column = scanner.nextInt();

      grid.revealCell(row - 1, column - 1);

      if (grid.isGameWon()) {
        System.out.println("Congratulations, You have won!");
        break;
      }
    }
    scanner.close();
  }
}
