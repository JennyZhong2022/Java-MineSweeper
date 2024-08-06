package MineSweeper;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private int rows;
    private int columns;
    private int totalMines;

    // how to implement 100 cells?
    // 10 rows
    // 10 columns

    public Grid(int rows, int columns, int totalMines) {
        this.rows = rows;
        this.columns = columns;
        this.totalMines = totalMines;
        cells = new Cell[rows][columns];
        initializeCells();
        placeBombs();
        calculateNeighborMines();

    }

    // initialize all cells as non-bomb cells
    private void initializeCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(false);
            }
        }
    }

    // randomly place bombs in the grid
    private void placeBombs() {
        Random randomBomb = new Random();
        int bombsPlaced = 0;

        while (bombsPlaced < totalMines) {
            int row = randomBomb.nextInt(rows);
            int column = randomBomb.nextInt(columns);

            if (!cells[row][column].isBomb()) {
                cells[row][column].setBomb(true);
                bombsPlaced++;
            }

        }

    }

    // Calculate the number of neighbor mines for each cell

    private void calculateNeighborMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!cells[i][j].isBomb()) {
                    int count = countMinesAround(i, j);
                    cells[i][j].setNeighborMines(count);
                }
            }
        }
    }

    // Count mines around a specific cell
    private int countMinesAround(int row, int column) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns && !(i == row && j == column) && cells[i][j].isBomb()) {
                    count++;
                }
            }
        }
        return count;
    }

    // reveal a cell

    public void revealCell(int row, int column) {
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (cells[row][column].isBomb()) {
                System.out.println("Boom! You hit a mine, game over");
                cells[row][column].reveal();
            } else {
                cells[row][column].reveal();
            }

        }
    }

    // print the current state of the≠ grid
    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].isRevealed()) {
                    if (cells[i][j].isBomb()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(cells[i][j].getNeighborMines() + " ");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println(); // Moves to the next line after printing a row
        }
    }

    // check if the game is won
    public boolean isGameWon() {
        int revealedCells = 0;
        int totalCells = rows * columns - totalMines;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].isRevealed() && !cells[i][j].isBomb()) {
                    revealedCells++;
                }
            }
        }
        return revealedCells == totalCells;
    }

}
