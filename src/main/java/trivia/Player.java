package trivia;

public class Player {

  private int purse = 0;
  private final String name;
  private int place = 0;

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

  public void setPlace(int place) {
    this.place = place;
  }
}
