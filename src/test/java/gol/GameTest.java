package gol;

import java.io.ByteArrayOutputStream;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void play1frame() {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      Game game = new Game(result,1);
      String input = "1 1\n.\n"; 
      game.parse(input); 
      game.play(1);
      String expected = input + input;
      assertEquals(expected, removeANSIControlChars(result.toString()));
    }
    
    @Test
    public void failOnBadInput() {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      Game game = new Game(result,1);
      String input = "1 ?\n.\n"; 
      game.parse(input); 
      game.play(1);
      String expected = "Invalid dimensions\n1 0\n1 0\n";
      assertEquals(expected, removeANSIControlChars(result.toString()));
    }
    
    private static String removeANSIControlChars(String input) {
    String output;
    output = input.replaceAll("\u001B\\[[\\d]*m", "");
    output = output.replaceAll("\u001B\\[H", "");
    output = output.replaceAll("\u001B\\[2J", "");
    output = output.replaceAll("\u001B\\[[.?]5[h|l]", "");
    return output;
  }

}
  