package gol;

public class DeadCell extends Cell {
  public static final String icon = "."; 
  
  @Override
  public Cell evolve() {
    return survive() ? Cell.makeLiveCell() : this ;
  }

  private boolean survive() {
    return (this.getLiveNeighbours() == 3);
  }
  
  @Override
  public String toString() {
    return icon;
  }
}