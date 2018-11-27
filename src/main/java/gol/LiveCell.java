package gol;

public class LiveCell extends Cell {
  private static final String icon = "*"; 

  @Override
  public Cell evolve() {
    return survive() ? this : new DeadCell() ;
  }

  private boolean survive() {
    return (this.getLiveNeighbours() == 2 || this.getLiveNeighbours() == 3);
  }
  
  @Override
  public String toString() {
    return icon;
  }
  
  public static boolean isIcon(String thisIcon) {
    return icon.equals(thisIcon);
  }
}