package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {
   List<Player> players = new ArrayList<>();

   GameBoard gameBoard = new GameBoard();

   LinkedList<Question> popQuestions = new LinkedList<>();
   LinkedList<Question> scienceQuestions = new LinkedList<>();
   LinkedList<Question> sportsQuestions = new LinkedList<>();
   LinkedList<Question> rockQuestions = new LinkedList<>();

   int currentPlayer = 0;

   public GameBetter() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast(createQuestion("Pop", i));
         scienceQuestions.addLast(createQuestion("Science", i));
         sportsQuestions.addLast(createQuestion("Sports", i));
         rockQuestions.addLast(createQuestion("Rock", i));
      }
   }

   public Question createQuestion(String category, int index) {
      return new Question(category, index);
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
            currentPlayer().leavePenaltyBox();

            System.out.println(currentPlayer().getName() + " is getting out of the penalty box");
            makePlayerTurn(roll);
         } else {
            System.out.println(currentPlayer().getName() + " is not getting out of the penalty box");
            currentPlayer().moveToPenaltyBox();
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
      int boardSize = gameBoard.getSize();
      int destination = (currentPlayer().getPlace() + roll) % boardSize;

      currentPlayer().move(destination);

      System.out.println(currentPlayer().getName()
          + "'s new location is "
          + currentPlayer().getPlace());
   }

   private void askQuestion() {
      if (currentCategory() == "Pop")
         System.out.println(popQuestions.removeFirst().askQuestion());
      if (currentCategory() == "Science")
         System.out.println(scienceQuestions.removeFirst().askQuestion());
      if (currentCategory() == "Sports")
         System.out.println(sportsQuestions.removeFirst().askQuestion());
      if (currentCategory() == "Rock")
         System.out.println(rockQuestions.removeFirst().askQuestion());
   }


   private String currentCategory() {
      return gameBoard.getCurrentCategory(currentPlayer().getPlace());
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
      currentPlayer().moveToPenaltyBox();

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
