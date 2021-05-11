package app;

public class SugestionData {
    String text, answer_a, answer_b, answer_c, answer_d, good_answer;
    int category_id;

    public SugestionData(String text, String answerA, String answerB, String answerC, String answerD, String good_answer, int category_id){
        this.text = text;
        this.answer_a = answerA;
        this.answer_b = answerB;
        this.answer_c = answerC;
        this.answer_d = answerD;
        this.good_answer = good_answer;
        this.category_id = category_id;
    }
}