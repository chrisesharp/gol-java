package gol;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void play1frame()  throws InterruptedException {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      Game game = new Game(result,1);
      String input = "1 1\n.\n"; 
      game.parse(input); 
      game.play(1);
      String expected = input + input;
      assertEquals(expected, removeANSIControlChars(result.toString()));
    }
    
    @Test
    public void failOnBadInput() throws InterruptedException {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      Game game = new Game(result,1);
      String input = "1 ?\n.\n"; 
      game.parse(input); 
      game.play(1);
      String expected = "Invalid dimensions\n1 0\n1 0\n";
      assertEquals(expected, removeANSIControlChars(result.toString()));
    }
    
    @Test
    public void testMain()  throws InterruptedException {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      List<String> commandArgs = new ArrayList<>();
      System.setOut(new PrintStream(result));
      GameMain.main(commandArgs.toArray(new String[0]));
      System.setOut(System.out);
      String expected = "1 1\n*\n1 1\n.\n"; 
      assertEquals(expected, removeANSIControlChars(result.toString()));
    }
    
    @Test
    public void testMainForUsage() throws InterruptedException {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      List<String> commandArgs = new ArrayList<>();
      commandArgs.add("-?");
      System.setErr(new PrintStream(result));
      GameMain.main(commandArgs.toArray(new String[0]));
      System.setErr(System.err);
      String usage = " -f (--file) VAL : Fully qualified path and name of input txt file. (default:\n                   start.txt)\n";
      usage += " -s (--speed) N  : Framerate in miliseconds (default: 100)\n";
      usage += " -t (--turns) N  : Number of turns to run simulation (default: 1)\n";
      assertEquals(usage, removeANSIControlChars(result.toString()));
    }
    
    @Test
    public void testFileError() throws InterruptedException {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      String[] args = {"-f","foo.txt"};
      System.setErr(new PrintStream(result));
      GameMain.main(args);
      System.setErr(System.err);
      String error = "File error!\n";
      assertEquals(error, removeANSIControlChars(result.toString()));
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
  