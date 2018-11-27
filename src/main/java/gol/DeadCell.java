package gol;

public class DeadCell extends Cell {
  public static final String icon = "."; 

  @Override
  public boolean survive() {
    return (this.getLiveNeighbours() == 3);
  }
  
  @Override
  public String toString() {
    return icon;
  }
}