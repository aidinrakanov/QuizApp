package com.example.quizapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.quizapp.db.convertor.DateConverter;
import com.example.quizapp.db.convertor.QuestionConverter;

import java.util.Date;
import java.util.List;


@Entity(tableName = "quiz_result")
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "difficulty")
    private String difficulty;

    @ColumnInfo(name = "correct_answers_result")
    private int correctAnswerResult;

    @TypeConverters({QuestionConverter.class})
    private List<Questions> questions;

    @TypeConverters({DateConverter.class})
    private Date createdAt;

    public QuizResult(int id, String category,
                      String difficulty, int correctAnswerResult,
                      List<Questions> questions, Date createdAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerResult = correctAnswerResult;
        this.questions = questions;
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

    public int getCorrectAnswerResult() {
        return correctAnswerResult;
    }

    public void setCorrectAnswerResult(int correctAnswerResult) {
        this.correctAnswerResult = correctAnswerResult;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
