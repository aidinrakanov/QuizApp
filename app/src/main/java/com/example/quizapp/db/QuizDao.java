package com.example.quizapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.quizapp.models.QuizResult;

import java.util.List;

@Dao
public interface QuizDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(QuizResult quizResult);

    @Query("SELECT * FROM quiz_result WHERE id=:id")
    QuizResult get(int id);

    @Delete
    void delete(QuizResult quizResult);

    @Query("DELETE FROM  quiz_result WHERE id=:id")
    void deleteById(int id);

    @Query("SELECT * FROM quiz_result")
    LiveData<List<QuizResult>> getAll();

    @Query("DELETE FROM quiz_result")
    void deleteAll();
}
