package com.example.joxapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class ImageWho {


    public final int imageResId;

    public final String Answer;

    public ImageWho(int imageResId, String Answer) {

        this.imageResId = imageResId;
        this.Answer = Answer;
    }
}
