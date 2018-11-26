package gol;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class World{
  Map<Location, Cell> liveCells = new HashMap<>();
  Map<Location, Cell> birthingCells = new HashMap<>();
  
  public Cell getCell(Location location) {
    Cell cell = liveCells.get(location);
    return (cell != null) ? cell : new Cell(false);
  }
  
  public void addLiveCell(Location location) {
    liveCells.put(location, new Cell());
  }
  
  private void addBirthingCell(Location location, int neighbours) {
    Cell cell = new Cell(false);
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
    for (Location neighbour: location.getNeighbourhood()) {
      if (!liveCells.containsKey(neighbour)) {
        addBirthingCell(neighbour, countLiveNeighbours(neighbour));
      }
    }
  }
  
  private Map<Location, Cell> determineNewPopulation() {
    liveCells.putAll(birthingCells);
    return liveCells.entrySet()
            .stream()
            .map(entry-> {
              entry.getValue().evolve();
              return entry;
            })
            .filter(entry -> entry.getValue().isAlive())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}