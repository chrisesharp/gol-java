package gol;

import org.junit.*;
import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void defaultCellIsDeadWithNoLiveNeighbours() {
        Cell cell = new DeadCell();
        Cell nextGen = cell.evolve();
        assertTrue(nextGen instanceof DeadCell);    
    }
    
    @Test
    public void liveCellWithNoLiveNeighboursDies() {
        Cell cell = new LiveCell();
        cell.setLiveNeighbours(0);
        Cell nextGen = cell.evolve();
        assertTrue(nextGen instanceof DeadCell);
    }
    
    @Test
    public void liveCellWithTwoLiveNeighboursLives(){
        Cell cell = new LiveCell();
        cell.setLiveNeighbours(2);
        Cell nextGen = cell.evolve();
        assertTrue(nextGen instanceof LiveCell);
    }
    
    @Test
    public void liveCellWithFourLiveNeighboursDies(){
        Cell cell = new LiveCell();
        cell.setLiveNeighbours(4);
        Cell nextGen = cell.evolve();
        assertTrue(nextGen instanceof DeadCell);
    }
    
    @Test
    public void deadCellWithThreeLiveNeighboursComesToLife(){
        Cell cell = new DeadCell();
        cell.setLiveNeighbours(3);
        Cell nextGen = cell.evolve();
        assertTrue(nextGen instanceof LiveCell);
    }
    
    @Test
    public void deadCellWithFourLiveNeighboursStaysDead(){
        Cell cell = new DeadCell();
        cell.setLiveNeighbours(4);
        Cell nextGen = cell.evolve();
        assertTrue(nextGen instanceof DeadCell);
    }
}

