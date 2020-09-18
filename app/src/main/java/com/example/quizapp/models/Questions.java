package com.example.quizapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Questions {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    @SerializedName("correct_answer")
    private String correctAnswer;
    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;

    private List<String> answers;
    private Integer selectAnswerPosition;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getSelectAnswerPosition() {
        return selectAnswerPosition;
    }

    public void setSelectAnswerPosition(int selectAnswerPosition) {
        this.selectAnswerPosition = selectAnswerPosition;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getDifficulty() { return difficulty; }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public String getCorrectAnswers() { return correctAnswer; }

    public void setCorrectAnswers(String correctAnswers) { this.correctAnswer = correctAnswers; }

    public List<String> getIncorrectAnswers() { return incorrectAnswers; }

    public void setIncorrectAnswers(List<String> incorrectAnswers) { this.incorrectAnswers = incorrectAnswers; }

    public Questions(String category, String type, String difficulty,
                     String question, String correctAnswers, List<String> incorrectAnswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
    }
}
