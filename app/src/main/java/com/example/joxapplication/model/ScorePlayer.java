package com.example.joxapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "score")
public class ScorePlayer {
    @PrimaryKey(autoGenerate = true)
    public final int id;
    @ColumnInfo(name = "player_name")
    public final String Name;
    @ColumnInfo(name = "player_score")
    public final int Score;
    public ScorePlayer(int id,String Name, int Score){
        this.id=id;
        this.Name=Name;
        this.Score=Score;
    }
}
