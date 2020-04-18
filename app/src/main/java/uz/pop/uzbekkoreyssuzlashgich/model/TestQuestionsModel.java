package uz.pop.uzbekkoreyssuzlashgich.model;

public class TestQuestionsModel {
    int id, categoryId, image;
    String answerA, answerB, answerC, answerD, answer, translatedAnswer;

    public TestQuestionsModel(){};

    public TestQuestionsModel(int id, int categoryId, int image, String answerA, String answerB, String answerC, String answerD, String answer, String translatedAnswer) {
        this.id = id;
        this.categoryId = categoryId;
        this.image = image;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answer = answer;
        this.translatedAnswer = translatedAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTranslatedAnswer() {
        return translatedAnswer;
    }

    public void setTranslatedAnswer(String translatedAnswer) {
        this.translatedAnswer = translatedAnswer;
    }
}
