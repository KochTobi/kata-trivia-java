package trivia;

public class Player {

  private int purse = 0;
  private final String name;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getPurse() {
    return purse;
  }

  public void addCoin(){
    purse++;
  }

}
