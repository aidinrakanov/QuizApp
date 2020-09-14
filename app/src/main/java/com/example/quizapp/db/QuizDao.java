package com.example.quizapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizapp.models.QuizResult;

import java.util.List;

@Dao
public interface QuizDao  {

    @Insert
    long insert(QuizResult quizResult);

    @Query("SELECT * FROM quizResult WHERE id =:id")
    QuizResult getById(int id);

    @Delete
    void delete(QuizResult quizResult);

    @Query("SELECT * FROM quizResult")
    LiveData<List<QuizResult>> getAll();
}
