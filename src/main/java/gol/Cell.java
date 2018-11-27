package gol;

public abstract class Cell {
  private int numberOfLiveNeighbours = 0;

  public Cell evolve() {
    return null;
  }
  
  public void setLiveNeighbours(int numberOfLiveNeighbours) {
    this.numberOfLiveNeighbours = numberOfLiveNeighbours;
  }
  
  public int getLiveNeighbours() {
    return numberOfLiveNeighbours;
  }
}