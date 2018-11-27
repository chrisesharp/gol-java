package gol;

import org.junit.*;
import static org.junit.Assert.*;

public class GridTest {
    private Game game = new Game();

    @Test
    public void singleCellInputGridIs1x1() {
        String input = "1 1\n.\n"; 
        game.parse(input); 
        assertEquals(1, game.width());
        assertEquals(1, game.height());
    }
    
    @Test
    public void cellInputGridIs2x1() {
        String input = "2 1\n..\n"; 
        game.parse(input); 
        assertEquals(2, game.width());
        assertEquals(1, game.height());
    }
    
    @Test
    public void cellInputGridIs2x2() {
        String input = "2 2\n..\n..\n"; 
        game.parse(input); 
        assertEquals(2, game.width());
        assertEquals(2, game.height());
    }
    
    @Test
    public void cellInputGridIs1x1WithDeadCell() {
        String input = "1 1\n.\n"; 
        game.parse(input); 
        assertFalse(game.cellAliveAt(new Location(0,0)));
    }
    
    @Test
    public void cellInputGridIs1x1WithLiveCell() {
        String input = "1 1\n*\n"; 
        game.parse(input); 
        assertTrue(game.cellAliveAt(new Location(0,0)));
    }
    
    @Test
    public void cellInputGridIs2x1WithLiveCell() {
        String input = "2 1\n*.\n"; 
        game.parse(input); 
        assertTrue(game.cellAliveAt(new Location(0,0)));
        assertFalse(game.cellAliveAt(new Location(1,0)));
    }
    
    @Test
    public void cellInputGridIs2x2With2LiveCells() {
        String input = "2 2\n.*\n.*\n"; 
        game.parse(input); 
        assertFalse(game.cellAliveAt(new Location(0,1)));
        assertTrue(game.cellAliveAt(new Location(1,1)));
    }
    
    @Test
    public void cellOutputGridIs1x1() {
        String input = "1 1\n.\n"; 
        game.parse(input); 
        String output = game.render();
        assertEquals(input, output);
    }
    
    @Test
    public void cellOutputGridIs2x1() {
        String input = "2 1\n..\n"; 
        game.parse(input); 
        String output = game.render();
        assertEquals(input, output);
    }
    
    @Test
    public void cellOutputGridIs2x2() {
        String input = "2 2\n..\n..\n"; 
        game.parse(input); 
        String output = game.render();
        assertEquals(input, output);
    }
    
    @Test
    public void cellOutputGridIs1x1WithLiveCell() {
        String input = "1 1\n*\n"; 
        game.parse(input); 
        String output = game.render();
        assertEquals(input, output);
    }
    
    @Test
    public void cellOutputGridIs2x2With2LiveCells() {
        String input = "2 2\n.*\n.*\n"; 
        game.parse(input); 
        String output = game.render();
        assertEquals(input, output);
    }
    
    @Test
    public void propeller() {
      String input = "3 3\n.*.\n.*.\n.*.\n";
      String expected = "3 3\n...\n***\n...\n";
      game.parse(input);
      game.evolve();
      String output = game.render();
      assertEquals(expected, output);
    }
}
  