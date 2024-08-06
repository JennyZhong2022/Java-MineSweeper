package MineSweeper;

import java.util.Random;

public class Grid {
    private Cell[][] cells; // cells is 2d array of Cell class, 2d like a table with rows and columns.
    private int rows;
    private int columns;
    private int totalMines;

    // constructor to store variables and methods
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
    // iterate every single cell to be non-bomb
    private void initializeCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(false);
            }
        }
    }

    // randomly place bombs in the grid
    // under the condition totalMines bigger than bombsPlaced, loop every single
    // cell, place bombs randomly.
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

    // iterate all cells, update number of neighbor mines for each cell
    // setNeighborMines from Cell class, set with number of count
    // use method countMinesAround to calculate the count

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

        // two for loops iterate the above row, underneath row, left column and right
        // column which are 8 cells around the specific cell
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                // ensure i and j > 0, no negative index and don't beyond the rows and columns,
                // doesn't include the cell
                // itself, only check around 8 cells and if there is a bomb, count++
                if (i >= 0 && i < rows && j >= 0 && j < columns && !(i == row && j == column) && cells[i][j].isBomb()) {
                    count++;
                }
            }
        }
        return count;
    }

    // reveal a cell

    public void revealCell(int row, int column) {
        // ensure rwo and column > 0, no negative index and don't beyond the rows and
        // columns,
        if (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (cells[row][column].isBomb()) {
                System.out.println("Boom! You hit a mine, game over");
                cells[row][column].reveal();
            } else {
                cells[row][column].reveal();
            }

        }
    }

    // print the current state of the grid
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
                    // no revealed, show .
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
