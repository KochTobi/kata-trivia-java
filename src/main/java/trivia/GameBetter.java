package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {
   List<Player> players = new ArrayList<>();

   LinkedList popQuestions = new LinkedList();
   LinkedList scienceQuestions = new LinkedList();
   LinkedList sportsQuestions = new LinkedList();
   LinkedList rockQuestions = new LinkedList();

   int currentPlayer = 0;
   private final int boardSize = 12;

   public GameBetter() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast(("Science Question " + i));
         sportsQuestions.addLast(("Sports Question " + i));
         rockQuestions.addLast(createRockQuestion(i));
      }
   }

   public String createRockQuestion(int index) {
      return "Rock Question " + index;
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + howManyPlayers());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(currentPlayer().getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer().isInPenaltyBox()) {
         if (roll % 2 != 0) {
            currentPlayer().setInPenaltyBox(false);

            System.out.println(currentPlayer().getName() + " is getting out of the penalty box");
            makePlayerTurn(roll);
         } else {
            System.out.println(currentPlayer().getName() + " is not getting out of the penalty box");
            currentPlayer().setInPenaltyBox(true);
         }

      } else {
         makePlayerTurn(roll);
      }

   }

   private void makePlayerTurn(int roll) {
      movePlayer(roll);
      System.out.println("The category is " + currentCategory());
      askQuestion();
   }

   private void movePlayer(int roll) {
      int destination = (getCurrentPlayer().getPlace() + roll) % boardSize;

      getCurrentPlayer().move(destination);

      System.out.println(currentPlayer().getName()
          + "'s new location is "
          + getCurrentPlayer().getPlace());
   }

   private Player getCurrentPlayer() {
      Player player = currentPlayer();
      return player;
   }

   private void askQuestion() {
      if (currentCategory() == "Pop")
         System.out.println(popQuestions.removeFirst());
      if (currentCategory() == "Science")
         System.out.println(scienceQuestions.removeFirst());
      if (currentCategory() == "Sports")
         System.out.println(sportsQuestions.removeFirst());
      if (currentCategory() == "Rock")
         System.out.println(rockQuestions.removeFirst());
   }


   private String currentCategory() {
      if (getCurrentPlayer().getPlace() == 0) return "Pop";
      if (getCurrentPlayer().getPlace() == 4) return "Pop";
      if (getCurrentPlayer().getPlace() == 8) return "Pop";
      if (getCurrentPlayer().getPlace() == 1) return "Science";
      if (getCurrentPlayer().getPlace() == 5) return "Science";
      if (getCurrentPlayer().getPlace() == 9) return "Science";
      if (getCurrentPlayer().getPlace() == 2) return "Sports";
      if (getCurrentPlayer().getPlace() == 6) return "Sports";
      if (getCurrentPlayer().getPlace() == 10) return "Sports";
      return "Rock";
   }

   public boolean wasCorrectlyAnswered() {
      if (currentPlayer().isInPenaltyBox()) {
            selectNextPlayer();
            return true;
      } else {
         System.out.println("Answer was correct!!!!");
         currentPlayer().addCoin();
         System.out.println(currentPlayer().getName()
                            + " now has "
                            + currentPlayer().getPurse()
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         selectNextPlayer();

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer().getName() + " was sent to the penalty box");
      currentPlayer().setInPenaltyBox(true);

      selectNextPlayer();
      return true;
   }

   private Player currentPlayer() {
      return players.get(currentPlayer);
   }


   private void selectNextPlayer() {
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
   }


   private boolean didPlayerWin() {
      return !(currentPlayer().getPurse() == 6);
   }
}
