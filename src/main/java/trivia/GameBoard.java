package trivia;

import java.util.List;

public class GameBoard {
    List<String> gameBoard = List.of("Pop","Science","Sports","Rock","Pop","Science","Sports","Rock","Pop","Science","Sports","Rock");

    GameBoard(){

    }

    public String getCurrentCategory(int place) {
        return gameBoard.get(place);
    }

    public int getSize() {
        return gameBoard.size();
    }

}
