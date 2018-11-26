package gol;

public class Game {
  private static final String ANSI_CLEARSCREEN = "\u001B[H\u001B[2J\u001B[1m";
  private static final int DEFAULT_SPEED = 100;
  private int speed_ms;
  private int width;
  private int height;
  private World world = new World();
  
  public Game() {
    speed_ms = DEFAULT_SPEED; 
  }
  
  public Game(int speed_ms) {
    this.speed_ms = speed_ms;
  }
  
  public int width(){
      return width;
  }
  
  public int height(){
      return height;
  }
  
  public void parse(String input){
    String[] parts = input.split("\n",2);
    parseDimensions(parts[0]);
    parseCells(parts[1]);
  }    
     
  private void parseDimensions(String dimensions) {
    try {
        width = Integer.parseInt(dimensions.substring(0,1));
        height = Integer.parseInt(dimensions.substring(2,3));
    } catch (NumberFormatException e) {
        System.out.println(e);
    }
  }
  
  private void parseCells(String input) {
    String[] rows = input.split("\n");
    for (int y=0; y < rows.length; y++) {
      String row = rows[y];
      for (int x=0; x < row.length(); x++){ 
        if (row.charAt(x) == '*') {
            world.addLiveCell(new Location(x,y));
        }
      }
    }
  }
  
  public boolean cellAliveAt(Location location) {
    Cell cell = world.getCell(location);
    return cell.isAlive();
  }
  
  public String render() {
    return renderHeader() + renderBoard();
  }
  
  private String renderHeader() {
    return width + " " + height + "\n"; 
  }
  
  private String renderBoard() {
    String output = "";
    for (int y=0; y < height; y++) {
      for (int x=0; x < width; x++){ 
        output += world.getCell(new Location(x,y));
      }
      output += "\n";
    }
    return output;
  }
  
  public void play(int turns) {
    for (int i=0; i < turns; i++) {
      System.out.println(ANSI_CLEARSCREEN);
      System.out.println(this.render());
      evolve();
      try {
        Thread.sleep(speed_ms);
      } catch (Exception e) {}
    }
    System.out.println(ANSI_CLEARSCREEN);
    System.out.println(this.render());
  }
  
  public void evolve() {
    world.evolve();
  }
}