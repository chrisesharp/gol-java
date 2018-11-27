package gol;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class World{
  private Map<Location, Cell> liveCells = new HashMap<>();
  private Map<Location, Cell> birthingCells = new HashMap<>();
  
  public Cell getCell(Location location) {
    return Cell.makeCell(liveCells.get(location));
  }
  
  public void addCell(String icon, Location location) {
    if (Cell.makeCell(icon).isAlive()) {
      addLiveCell(location);
    }
  }
  
  public void addLiveCell(Location location) {
    liveCells.put(location, Cell.makeLiveCell());
  }
  
  private void addBirthingCell(Location location, int neighbours) {
    Cell cell = new DeadCell();
    cell.setLiveNeighbours(neighbours);
    birthingCells.put(location, cell);
  }
  
  public void addLiveCells(Location[] locationArray) {
    for(Location loc: locationArray) {
      addLiveCell(loc);    
    }
  }
  
  public void evolve() {
    birthingCells.clear();
    evaluateNeighbourhoods();
    liveCells = determineNewPopulation();
  }
  
  private void evaluateNeighbourhoods() {
    for (Location location: liveCells.keySet()) {
      liveCells.get(location).setLiveNeighbours(countLiveNeighbours(location));
      identifyBirthingNeighbours(location);
    }
  }
  
  private int countLiveNeighbours(Location location) {
    Set<Location> intersection = new HashSet<>(liveCells.keySet());
    intersection.retainAll(location.getNeighbourhood());
    return intersection.size();
  }
  
  private void identifyBirthingNeighbours(Location location) {
    Set<Location> intersection = new HashSet<>(location.getNeighbourhood());
    intersection.removeAll(liveCells.keySet());
    intersection.stream()
      .forEach(neighbour -> addBirthingCell(neighbour, countLiveNeighbours(neighbour)));
  }
  
  private Map<Location, Cell> determineNewPopulation() {
    liveCells.putAll(birthingCells);
    return liveCells.entrySet()
            .stream()
            .map(entry-> {
              entry.setValue(entry.getValue().evolve());
              return entry;
            })
            .filter(entry -> entry.getValue().isAlive())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}