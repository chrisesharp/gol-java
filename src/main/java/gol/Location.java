package gol;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.IntStream;

public class Location{
  public int x,y;
  
  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof Location) {
      Location that = (Location) other;
      return (this.x==that.x && this.y==that.y);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return (13 * this.x) + 19 * this.y;
  }
  
  public Set<Location> getNeighbourhood() {
    Set<Location> neighbours = new HashSet<>();
    int startX = Math.max(0, this.x - 1);
    int startY = Math.max(0, this.y - 1);
    int endX = this.x + 1;
    int endY = this.y + 1;
    
    IntStream.rangeClosed(startY, endY)
      .forEach(y -> IntStream.rangeClosed(startX, endX)
      .filter(x -> !(x==this.x && y==this.y))
      .forEach(x -> neighbours.add(new Location(x,y)))
    );

    return neighbours;
  }
}