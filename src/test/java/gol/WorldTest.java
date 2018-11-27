package gol;

import org.junit.*;
import static org.junit.Assert.*;

public class WorldTest{
    @Test
    public void newWorldIsEmpty() {
        World world = new World();
        Location location = new Location(0,0);
        Cell cell = world.getCell(location);
        assertTrue(cell instanceof DeadCell);
    }
    
    @Test
    public void addSingleCellInWorld() {
        World world = new World();
        Location location = new Location(0,0);
        world.addLiveCell(location);
        Cell cell = world.getCell(location);
        assertTrue(cell instanceof LiveCell);
    }
    
    @Test
    public void singleCellInWorldDies() {
        World world = new World();
        Location location = new Location(0,0);
        world.addLiveCell(location);
        world.evolve();
        Cell cell = world.getCell(location);
        assertTrue(cell instanceof DeadCell);
    }

    @Test
    public void twoCellsInWorldDie() {
        World world = new World();
        Location location1 = new Location(0,0);
        Location location2 = new Location(0,1);
        Location[] locationArray;        
        locationArray = new Location[] {location1, location2};
        world.addLiveCells(locationArray);
        world.evolve();
        Cell cell1 = world.getCell(location1);
        Cell cell2 = world.getCell(location2);
        assertTrue(cell1 instanceof DeadCell);
        assertTrue(cell2 instanceof DeadCell);
    }
    
    @Test
    public void threeCellsInWorldOneSurvives() {
        World world = new World();
        Location location1 = new Location(0,0);
        Location location2 = new Location(0,1);
        Location location3 = new Location(0,2);
        Location[] locationArray;        
        locationArray = new Location[] {location1, location2, location3};
        world.addLiveCells(locationArray);
        world.evolve();
        Cell cell1 = world.getCell(location1);
        Cell cell2 = world.getCell(location2);
        Cell cell3 = world.getCell(location3);
        assertTrue(cell1 instanceof DeadCell);
        assertTrue(cell2 instanceof LiveCell);
        assertTrue(cell3 instanceof DeadCell);
    }

    @Test
    public void stableFour() {
        World world = new World();
        Location location1 = new Location(0,0);
        Location location2 = new Location(0,1);
        Location location3 = new Location(1,0);
        Location location4 = new Location(1,1);
        Location[] locationArray;        
        locationArray = new Location[] {location1, location2, location3, location4};
        world.addLiveCells(locationArray);
        world.evolve();
        Cell cell1 = world.getCell(location1);
        Cell cell2 = world.getCell(location2);
        Cell cell3 = world.getCell(location3);
        Cell cell4 = world.getCell(location4);
        assertTrue(cell1 instanceof LiveCell);
        assertTrue(cell2 instanceof LiveCell);
        assertTrue(cell3 instanceof LiveCell);
        assertTrue(cell4 instanceof LiveCell);
    }
    
    @Test
    public void deathByOvercrowding() {
        World world = new World();
        Location location1 = new Location(1,0);
        Location location2 = new Location(1,1);
        Location location3 = new Location(1,2);
        Location location4 = new Location(0,1);
        Location location5 = new Location(2,1);
        Location[] locationArray = new Location[] {location1, location2, location3, location4, location5 };
        world.addLiveCells(locationArray);
        world.evolve();
        Cell cell = world.getCell(location2);
        assertTrue(cell instanceof DeadCell);
    }
    
    @Test
    public void birthOfTwoCells() {
        World world = new World();
        Location location1 = new Location(1,0);
        Location location2 = new Location(1,1);
        Location location3 = new Location(1,2);
        Location location4 = new Location(0,1);
        Location location5 = new Location(2,1);
        Location[] locationArray = new Location[] {location1, location2, location3 };
        world.addLiveCells(locationArray);
        world.evolve();
        Cell cell4 = world.getCell(location4);
        Cell cell5 = world.getCell(location5);
        assertTrue(cell4 instanceof LiveCell);
        assertTrue(cell5 instanceof LiveCell);
    }    
}
