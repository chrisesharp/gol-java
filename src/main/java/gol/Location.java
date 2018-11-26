package gol;

import java.util.Set;
import java.util.HashSet;

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
    for (int y = startY; y <= endY; y++) {
      for (int x = startX; x <= endX; x++) {
        if (!(x==this.x && y==this.y)) {
          neighbours.add(new Location(x,y));
        }
      }
    }
    return neighbours;
  }
}