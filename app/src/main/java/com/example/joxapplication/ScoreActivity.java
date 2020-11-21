package com.example.joxapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.joxapplication.db.AppDatabase;
import com.example.joxapplication.model.ScorePlayer;



import java.util.Arrays;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {



    protected void onResume() {
        super.onResume();
        reload();




    }
    private void reload() {
        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(ScoreActivity.this);
                final ScorePlayer[] users = db.userDao().getAllUsers();// Select every data from database to show on recyclerview
                List<ScorePlayer> wordList = Arrays.asList(users);
                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager lm = new LinearLayoutManager(ScoreActivity.this);
                        RecyclerView rv = findViewById(R.id.recycler);
                        MyAdapter adapter = new MyAdapter(ScoreActivity.this, wordList);
                        rv.setLayoutManager(lm);
                        rv.setAdapter(adapter); }});


            }
        });





    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        onResume();
    }

}