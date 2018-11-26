package gol;

import org.junit.*;
import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void defaultCellStateIsAlive() {
        Cell cell = new Cell();
        assertTrue(cell.isAlive());
    }

    @Test
    public void defaultCellIsDeadWithNoLiveNeighbours() {
        Cell cell = new Cell(false);
        cell.evolve();
        assertFalse(cell.isAlive());    
    }
    
    @Test
    public void liveCellWithNoLiveNeighboursDies() {
        Cell cell = new Cell();
        cell.setLiveNeighbours(0);
        cell.evolve();
        assertFalse(cell.isAlive());
    }
    
    @Test
    public void liveCellWithTwoLiveNeighboursLives(){
        Cell cell = new Cell();
        cell.setLiveNeighbours(2);
        cell.evolve();
        assertTrue(cell.isAlive());
    }
    
    @Test
    public void liveCellWithFourLiveNeighboursDies(){
        Cell cell = new Cell();
        cell.setLiveNeighbours(4);
        cell.evolve();
        assertFalse(cell.isAlive());
    }
    
    @Test
    public void deadCellWithThreeLiveNeighboursComesToLife(){
        Cell cell = new Cell(false);
        cell.setLiveNeighbours(3);
        cell.evolve();
        assertTrue(cell.isAlive());
    }
    
        @Test
    public void deadCellWithFourLiveNeighboursStaysDead(){
        Cell cell = new Cell();
        cell.setLiveNeighbours(4);
        cell.evolve();
        assertFalse(cell.isAlive());
    }
}

