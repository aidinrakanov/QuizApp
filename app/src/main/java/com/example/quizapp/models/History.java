package com.example.quizapp.models;

import java.util.Date;

public class History {
    private int id;
    private String category;
    private String difficulty;
    private int amounts;
    private int correctAnswer;
    private Date createdAt;

    public History(int id, String category, String difficulty,
                   int correctAnswer, int amounts, Date createdAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.amounts = amounts;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getAmounts() {
        return amounts;
    }

    public void setAmounts(int amounts) {
        this.amounts = amounts;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
