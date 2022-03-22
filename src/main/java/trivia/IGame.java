package trivia;

public interface IGame {

	boolean addPlayer(String playerName);

	void roll(int roll);

	boolean wasCorrectlyAnswered();

	boolean wrongAnswer();

}
