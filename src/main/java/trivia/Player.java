package trivia;

public class Player {
  
  private final String name;

  private int position;
  private int money;
  
  private Player(String name, int position, int money) {
    this.name = name;
  }
  
  public static Player create(String name) {
    return new Player(name, 0, 0);
  }

  public String getName() {
    return name;
  }

  public int getMoney() {
    return money;
  }

  public void addMoney(int money) {
    this.money += money;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }
  
}
