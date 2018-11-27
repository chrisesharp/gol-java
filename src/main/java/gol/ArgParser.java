package gol;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.OutputStream;

public class ArgParser {
  private boolean valid;
  private CmdLineParser parser;
  
  @Option(name="-f", aliases="--file", usage="Fully qualified path and name of input txt file.")  
  private String fileName = "start.txt";   
  
  @Option(name="-t", aliases="--turns", usage="Number of turns to run simulation")
  private int turns = 1;
  
  @Option(name="-s", aliases="--speed", usage="Framerate in miliseconds")
  private int speed = 100;

  public ArgParser(String... args) {
    parser = new CmdLineParser(this);
    try {
      parser.parseArgument(args);
      this.valid = true;
    } catch (CmdLineException e) {
      this.valid = false;
    }
  }
  
  public void printUsage(OutputStream out) {
    parser.printUsage(out);
  }
  
  public String getFile() {
    return this.fileName;
  }
  
  public int getTurns() {
    return this.turns;
  }
  
  public int getSpeed() {
    return this.speed;
  }
  
  public boolean isValid() {
    return valid;
  }
}