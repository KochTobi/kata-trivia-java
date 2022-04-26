package trivia;

public class Player {

  private int purse = 0;
  private final String name;
  private int place = 0;

  private boolean inPenaltyBox = false;

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

  public int getPlace() {
    return place;
  }

  public void move(int place) {
    this.place = place;
  }

  public void setInPenaltyBox(boolean inPenaltyBox) {
    this.inPenaltyBox = inPenaltyBox;
  }

  public boolean isInPenaltyBox() {
    return inPenaltyBox;
  }
}
