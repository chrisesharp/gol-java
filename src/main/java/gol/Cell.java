package gol;

public abstract class Cell {
  private int numberOfLiveNeighbours = 0;

  public Cell evolve() {
    return survive() ? Cell.makeLiveCell() : Cell.makeDeadCell() ;
  }
  
  public abstract boolean survive();
  
  public void setLiveNeighbours(int numberOfLiveNeighbours) {
    this.numberOfLiveNeighbours = numberOfLiveNeighbours;
  }
  
  public int getLiveNeighbours() {
    return numberOfLiveNeighbours;
  }
  
  public boolean isAlive() {
    return false;
  }
  
  public static Cell makeCell(Cell cell) {
    return (cell != null) ? cell : new DeadCell();
  }
  
  public static Cell makeCell(String icon) {
    return (LiveCell.isIcon(icon)) ? new LiveCell() : new DeadCell();
  }
  
  public static Cell makeLiveCell() {
    return new LiveCell();
  }
  
  public static Cell makeDeadCell() {
    return new DeadCell();
  }

}