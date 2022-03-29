package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {
   List<Player> players = new ArrayList<>();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   private LinkedList<String> popQuestions = new LinkedList<>();
   private LinkedList<String> scienceQuestions = new LinkedList<>();
   private LinkedList<String> sportsQuestions = new LinkedList<>();
   private LinkedList<String> rockQuestions = new LinkedList<>();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public GameBetter() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast(createQuestion(QuestionCategory.POP , i));
         scienceQuestions.addLast(createQuestion(QuestionCategory.SCIENCE , i));
         sportsQuestions.addLast(createQuestion(QuestionCategory.SPORTS , i));
         rockQuestions.addLast(createQuestion( QuestionCategory.ROCK, i));
      }
   }

   public String createQuestion(QuestionCategory questionCategory, int index) {
      return questionCategory.label + " Question " + index;
   }

   public boolean isPlayable() {
      return (getNumberOfPlayers() >= 2);
   }

   @Override
   public boolean addPlayer(String playerName) {
      players.add(Player.create(playerName));
      purses[getNumberOfPlayers()] = 0;

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int getNumberOfPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(players.get(currentPlayer).getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer().isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer).getName() + " is getting out of the penalty box");
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer).getName()
                               + "'s new location is "
                               + places[currentPlayer]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
         } else {
            System.out.println(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

         System.out.println(players.get(currentPlayer).getName()
                            + "'s new location is "
                            + places[currentPlayer]);
         System.out.println("The category is " + currentCategory());
         askQuestion();
      }

   }

   private Player currentPlayer() {
      return players.get(currentPlayer);
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
     int position = players.get(currentPlayer).getPosition();
      if (position == 0) return "Pop";
      if (position == 4) return "Pop";
      if (position == 8) return "Pop";
      if (position == 1) return "Science";
      if (position == 5) return "Science";
      if (position == 9) return "Science";
      if (position == 2) return "Sports";
      if (position == 6) return "Sports";
      if (position == 10) return "Sports";
      return "Rock";
   }

   public boolean wasCorrectlyAnswered() {
      if (currentPlayer().isInPenaltyBox()) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer).getName()
                               + " now has "
                               + purses[currentPlayer]
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
         } else {
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         purses[currentPlayer]++;
         System.out.println(players.get(currentPlayer).getName()
                            + " now has "
                            + purses[currentPlayer]
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
      currentPlayer().moveToPenaltyBox();

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }

   static enum QuestionCategory {
      POP("Pop"),
      SCIENCE("Science"),
      SPORTS("Sports"),
      ROCK("Rock");

      QuestionCategory(String label) {
         this.label = label;
      }
      final String label;
   }

}
