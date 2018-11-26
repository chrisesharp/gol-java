package gol;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class GameMain {  
  private static String readFile(String filePath) {
    StringBuilder contentBuilder = new StringBuilder();
    if (filePath.length()>0) {
      try {
        Stream<String> stream = Files.lines(
          Paths.get(filePath), 
          StandardCharsets.UTF_8);
        stream.forEach(line -> contentBuilder.append(line).append("\n"));
        stream.close();
      }
      catch (IOException e)
      {
        System.err.println(e);
      }
    }
    return contentBuilder.toString();
  }
  
  public static void main(String[] args) {
    ArgParser parser = new ArgParser(args);
    String file = (parser.hasFile()) ? parser.getFile() : "start.txt";
    int turns = parser.getTurns();
    int speed = parser.getSpeed();
    Game game = new Game(speed);
    game.parse(readFile(file));
    game.play(turns);
  }
    
}