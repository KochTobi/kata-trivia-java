package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {
   List<Player> players = new ArrayList<>();
   int[] places = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   LinkedList popQuestions = new LinkedList();
   LinkedList scienceQuestions = new LinkedList();
   LinkedList sportsQuestions = new LinkedList();
   LinkedList rockQuestions = new LinkedList();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

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
      inPenaltyBox[howManyPlayers()] = false;

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + howManyPlayers());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(players.get(currentPlayer).getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer).getName() + " is getting out of the penalty box");
            makePlayerTurn(roll);
         } else {
            System.out.println(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
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

      getCurrentPlayer().setPlace(getCurrentPlayer().getPlace() + roll);
      if (getCurrentPlayer().getPlace() > 11) {
         getCurrentPlayer().setPlace(getCurrentPlayer().getPlace() - 12);
      }
      System.out.println(players.get(currentPlayer).getName()
          + "'s new location is "
          + getCurrentPlayer().getPlace());
   }

   private Player getCurrentPlayer() {
      Player player = players.get(currentPlayer);
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
      if (inPenaltyBox[currentPlayer]) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            players.get(currentPlayer).addCoin();
            System.out.println(players.get(currentPlayer).getName()
                               + " now has "
                               + players.get(currentPlayer).getPurse()
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            selectNextPlayer();

            return winner;
         } else {
            selectNextPlayer();
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         players.get(currentPlayer).addCoin();
         System.out.println(players.get(currentPlayer).getName()
                            + " now has "
                            + players.get(currentPlayer).getPurse()
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         selectNextPlayer();

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      selectNextPlayer();
      return true;
   }

   private void selectNextPlayer() {
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
   }


   private boolean didPlayerWin() {
      return !(players.get(currentPlayer).getPurse() == 6);
   }
}
