package com.example.joxapplication.db;

import android.content.Context;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.joxapplication.AppExecutors;
import com.example.joxapplication.R;

import com.example.joxapplication.model.ScorePlayer;

import java.util.Calendar;
import java.util.concurrent.Executors;


@Database(entities = {ScorePlayer.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  private static final String TAG = "AppDatabase";
  private static final String DB_NAME = "user.db";

  private static AppDatabase sInstance;

  public abstract ImageDao userDao();

  public static synchronized AppDatabase getInstance(final Context context) {
    if (sInstance == null) {
      sInstance = Room.databaseBuilder(
              context.getApplicationContext(),
              AppDatabase.class,
              DB_NAME
      ).addCallback(new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
          super.onCreate(db);
          insertInitialData(context);
        }
      }).build();
    }
    return sInstance;
  }

  private static void insertInitialData(final Context context) {
    AppExecutors executors = new AppExecutors();
    executors.diskIO().execute(new Runnable() {
      @Override
      public void run() { // worker thread
        AppDatabase db = getInstance(context);
        db.userDao().addUser(
                new ScorePlayer(0,"Sam",3),
                new ScorePlayer(0,"Smith",4)
        );
      }
    });
  }
}

