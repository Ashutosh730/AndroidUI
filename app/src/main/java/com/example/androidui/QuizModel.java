package com.example.androidui;

public class QuizModel {

    int question;
    boolean ans;

    public QuizModel(int question, boolean ans) {
        this.question = question;
        this.ans = ans;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }
}
