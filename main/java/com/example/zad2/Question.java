package com.example.zad2;

public class Question {
    private int questionId;
    private boolean trueAnswer;

    public Question(int questionId, boolean trueAnswer){
        this.questionId = questionId;
        this.trueAnswer = trueAnswer;
    }
    public int getQuestionId() {
        return questionId; // questionResourceId to identyfikator zasobu tekstowego
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }
}