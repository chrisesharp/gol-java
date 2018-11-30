package gol;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.stream.IntStream;

public class Game {
  private static final String ANSI_CLEARSCREEN = "\u001B[H\u001B[2J\u001B[1m";
  private static final int DEFAULT_SPEED = 100;
  private int speed_ms;
  private int width;
  private int height;
  private PrintStream output;
  private World world = new World();
  
  public Game() {
    this(System.out, DEFAULT_SPEED);
  }
  
  public Game(OutputStream out, int speed_ms) {
    this.speed_ms = speed_ms;
    this.output = new PrintStream(out);
  }
  
  public int width(){
      return width;
  }
  
  public int height(){
      return height;
  }
  
  public void parse(String input) {
    String[] parts = input.split("\n",2);
    parseDimensions(parts[0]);
    parseCells(parts[1]);
  }    
     
  private void parseDimensions(String input) {
    String[] dimensions = input.split(" ");
    try {
      width = Integer.parseInt(dimensions[0]);
      height = Integer.parseInt(dimensions[1]);
    } catch (NumberFormatException e) {
      output.println("Invalid dimensions");
    }
  }
  
  private void parseCells(String input) {
    String[] rows = input.split("\n");
    
    IntStream.range(0, rows.length)
      .forEach(y -> 
        IntStream.range(0, rows[y].length())
          .forEach(x -> world.addCell(rows[y].substring(x,x+1),new Location(x,y)))
      );
  }
  
  protected boolean cellAliveAt(Location location) {
    return world.getCell(location).isAlive();
  }
  
  public String render() {
    return renderHeader() + renderBoard();
  }
  
  private String renderHeader() {
    return width + " " + height + "\n"; 
  }
  
  private String renderBoard() {
    StringBuilder output = new StringBuilder();
    IntStream.range(0, height)
      .forEach(y -> {
        IntStream.range(0, width)
          .forEach(x -> output.append(world.getCell(new Location(x,y))));
        output.append("\n");
      }
    );
    return output.toString();
  }
  
  public void play(int turns) {
    for (int i=0; i < turns; i++) {
      output.print(ANSI_CLEARSCREEN);
      output.print(this.render());
      evolve();
      try {
        Thread.sleep(speed_ms);
      } catch (Exception e) {}
    }
    output.print(ANSI_CLEARSCREEN);
    output.print(this.render());
  }
  
  public void evolve() {
    world.evolve();
  }
}