package MineSweeper;

public class Cell {
  private boolean bomb;
  private boolean revealed;
  private int neighborMines;

  public Cell(boolean bomb) {
    this.bomb = bomb;
    this.revealed = false; // Cells start as unrevealed
    this.neighborMines = 0;
  }

  // getter and setter for bomb
  public boolean isBomb() {
    return bomb;
  }

  public void setBomb(boolean bomb) {
    this.bomb = bomb;
  }

  // getter and setter for reveal
  public boolean isRevealed() {
    return revealed;
  }

  public void reveal() {
    this.revealed = true;
  }

  // getter and setter for mines count
  public int getNeighborMines() {
    return neighborMines;
  }

  public void setNeighborMines(int count) {
    this.neighborMines = count;
  }
}
