package gol;
import java.util.Map;
import java.util.HashMap;

public class Cell {
  private boolean liveness = true;
  private int numberOfLiveNeighbours = 0;
  private static final Map<Boolean,String> icons = new HashMap<Boolean, String>() 
  {
    private static final long serialVersionUID = 42L;
    {
      put(Boolean.TRUE, "*");
      put(Boolean.FALSE, "."); 
    }
  };
  
  public Cell() {  
  }
  
  public Cell(boolean liveness) {
      this.liveness = liveness;
  }

  public void evolve() {
    liveness = applyRules();
  }
  
  public boolean isAlive() {
    return liveness;
  }
  
  public void setLiveNeighbours(int numberOfLiveNeighbours){
    this.numberOfLiveNeighbours = numberOfLiveNeighbours;
  }
  
  private boolean growthCase() {
    return numberOfLiveNeighbours == 3;
  }
  
  private boolean stable() {
    return numberOfLiveNeighbours == 2 && liveness;
  }
  
  private boolean applyRules() {
    return (stable()|| growthCase());
  }
  
  @Override
  public String toString() {
    return icons.get(liveness);
  }
}