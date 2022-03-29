package trivia;

public class Player {
  
  private final String name;

  private int position;
  private int money;
  private boolean inPenaltyBox;
  
  private Player(String name, int position, int money, boolean inPenaltyBox) {
    this.name = name;
    this.inPenaltyBox = inPenaltyBox;
  }
  
  public static Player create(String name) {
    return new Player(name, 0, 0, false);
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

  public void moveToPenaltyBox() {
    this.inPenaltyBox = true;
  }

  public void leavePenaltyBox() {
    this.inPenaltyBox = false;
  }

  public boolean isInPenaltyBox() {
    return inPenaltyBox;
  }
}
