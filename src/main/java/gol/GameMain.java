package gol;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class GameMain {  
  private static String readFile(String filePath) {
    StringBuilder contentBuilder = new StringBuilder();
    try {
      Stream<String> stream = Files.lines(
        Paths.get(filePath), 
        StandardCharsets.UTF_8);
      stream.forEach(line -> contentBuilder.append(line).append("\n"));
      stream.close();
    }
    catch (IOException e)
    {
      System.err.println("File error!");
    }
    return contentBuilder.toString();
  }
  
  public static void main(String[] args) {
    ArgParser parser = new ArgParser(args);
    if (parser.isValid()) {
      String file = parser.getFile();
      int turns = parser.getTurns();
      int speed = parser.getSpeed();
      Game game = new Game(System.out, speed);
      String input = readFile(file);
      if (input.length()>0) {
        game.parse(input);
        game.play(turns);  
      }    
    } else {
      parser.printUsage(System.err);
    }
  }  
}