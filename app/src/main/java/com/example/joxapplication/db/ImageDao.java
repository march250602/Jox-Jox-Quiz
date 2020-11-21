package com.example.joxapplication.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.joxapplication.model.ScorePlayer;

import java.util.List;

@Dao
public interface ImageDao {
    @Query("SELECT * FROM score")
    ScorePlayer[] getAllUsers();

    @Query("SELECT * FROM score WHERE player_name = :id")
    ScorePlayer getUserByName(String id);

    @Insert
    void addUser(ScorePlayer... users);

    @Delete
    void deleteUser(ScorePlayer user);
}
