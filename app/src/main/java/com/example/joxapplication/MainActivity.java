package com.example.joxapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    protected void onResume() {
        super.onResume();
        EditText Name= findViewById(R.id.NameText);
        Name.setText("");   // Clear String on Edit text


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            Button playbutt = findViewById(R.id.playbutton);
        Button ScoreButt = findViewById(R.id.AllScore);
        ScoreButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// Press CHECH SCORE to go to ScoreActivity
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });
        playbutt.setOnClickListener(new View.OnClickListener() {// Press Play to go to GameActivity

            @Override
            public void onClick(View view) {EditText Name= findViewById(R.id.NameText);
            String a= Name.getText().toString();
            if(a.length()<1){
                Toast.makeText(MainActivity.this,"Please Enter Your Name!!!",Toast.LENGTH_SHORT).show();
            }else{

                Intent intent = new Intent(MainActivity.this, GameActivity.class);
               intent.putExtra("Name",a); // Send Player name from MainActivity to GameActivity
                startActivity(intent);
            }}
        });
    }

}