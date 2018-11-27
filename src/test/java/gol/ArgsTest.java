package gol;

import java.io.ByteArrayOutputStream;
import org.junit.*;
import static org.junit.Assert.*;

public class ArgsTest {

    @Test
    public void argsParsedCorrectly() {
      String[] args = {"-f","input.txt","-t","10","-s","200"};
      ArgParser parser = new ArgParser(args);
      String file = (parser.hasFile()) ? parser.getFile() : "start.txt";
      int turns = parser.getTurns();
      int speed = parser.getSpeed();
      assertEquals("input.txt",file);
      assertEquals(10,turns);
      assertEquals(200,speed);
    }
    
    @Test
    public void defaultArgsComputedCorrectly() {
      String[] args = {};
      ArgParser parser = new ArgParser(args);
      String file = (parser.hasFile()) ? parser.getFile() : "start.txt";
      int turns = parser.getTurns();
      int speed = parser.getSpeed();
      assertTrue(parser.isValid());
      assertEquals("start.txt",file);
      assertEquals(1,turns);
      assertEquals(100,speed);
    }
    
    @Test
    public void showUsage() {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      String[] args = {"-?"};
      ArgParser parser = new ArgParser(args);
      assertFalse(parser.isValid());
      parser.printUsage(result);
      String usage = " -f (--file) VAL : Fully qualified path and name of input txt file.\n";
      usage += " -s (--speed) N  : Framerate in miliseconds (default: 100)\n";
      usage += " -t (--turns) N  : Number of turns to run simulation (default: 1)\n";
      assertEquals(usage, result.toString() );
    }
}

