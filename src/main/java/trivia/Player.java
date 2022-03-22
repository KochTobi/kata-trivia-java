package trivia;

public class Player {
  
  private final String name;
  private int place;
  
  private Player(String name) {
    this.name = name;
  }
  
  public static Player create(String name) {
    return new Player(name);
  }

  public String getName() {
    return name;
  }
  
}
