package trivia;

public class Question {

    public Question(String category, int index) {
        this.category = category;
        this.index = index;
    }

    private String category;
    private int index;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String askQuestion() {
        return category+" Question "+index;
    }
}
