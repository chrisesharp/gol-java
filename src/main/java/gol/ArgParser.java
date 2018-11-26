package gol;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class ArgParser {
  @Option(name="-f", aliases="--file", usage="Fully qualified path and name of input txt file.")  
  private String fileName;   
  
  @Option(name="-t", aliases="--turns", usage="Number of turns to run simulation")
  private int turns = 1;
  
  @Option(name="-s", aliases="--speed", usage="Framerate in miliseconds")
  private int speed = 100;

  public ArgParser(String... args) {
    CmdLineParser parser = new CmdLineParser(this);
    try {
      parser.parseArgument(args);
    } catch (CmdLineException e) {
      parser.printUsage(System.err);
      System.err.println(e.getMessage());
      System.exit(-1);
    }
  }
  
  public String getFile() {
    return this.fileName;
  }
  
  public boolean hasFile() {
    return (this.fileName != null);
  }
  
  public int getTurns() {
    return this.turns;
  }
  
  public int getSpeed() {
    return this.speed;
  }
}