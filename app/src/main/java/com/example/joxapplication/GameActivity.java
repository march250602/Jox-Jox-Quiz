package com.example.joxapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joxapplication.db.AppDatabase;
import com.example.joxapplication.model.ImageWho;
import com.example.joxapplication.model.ScorePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity  implements View.OnClickListener {
    private ImageView mQuestionImageView;
    private Button[] mButtons = new Button[4];
    static public ImageWho[] items = { // Data for Quiz Game
            new ImageWho(R.drawable.basty,"Basty"),
            new ImageWho(R.drawable.bear,"Beer"),
            new ImageWho(R.drawable.mix,"Mix"),
            new ImageWho(R.drawable.nurat,"Nurat"),
            new ImageWho(R.drawable.nutt,"Nuttnisa"),
            new ImageWho(R.drawable.petch,"Petchy"),
            new ImageWho(R.drawable.sitang,"Sitang"),
            new ImageWho(R.drawable.toon,"Toon"),
            new ImageWho(R.drawable.film,"Film")
    };
    private String mAnswerWord;
    private Random mRandom;
    private List<ImageWho> mItemList;

    private int score = 0;
     TextView scoreTextView;
    private int round = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView PlayerName = findViewById(R.id.Nameplayer);
        String getName=getIntent().getExtras().getString("Name"); // Get player Name from MainActivity
        PlayerName.setText(getName+" "); // Set player name in GameActivity
        mQuestionImageView = findViewById(R.id.question_image_view);
        mButtons[0] = findViewById(R.id.choice1);
        mButtons[1] = findViewById(R.id.choice2);
        mButtons[2] = findViewById(R.id.choice3);
        mButtons[3] = findViewById(R.id.choice4);

        mButtons[0].setOnClickListener(this);
        mButtons[1].setOnClickListener(this);
       mButtons[2].setOnClickListener(this);
        mButtons[3].setOnClickListener(this);

        scoreTextView = findViewById(R.id.Score);
        mItemList = new ArrayList<>(Arrays.asList(items));
        mRandom = new Random();
        newQuiz();
    }

    private void newQuiz() {


        // random word for game
        int answerIndex = mRandom.nextInt(mItemList.size());

        ImageWho item = mItemList.get(answerIndex);
        // set image for test
        mQuestionImageView.setImageResource(item.imageResId);
        mAnswerWord = item.Answer;

        // random choice button answer
        int randomButton = mRandom.nextInt(4);
        mButtons[randomButton].setText(item.Answer);
        // pull answer item out form list
        mItemList.remove(item);
        // shuffle data
        Collections.shuffle(mItemList);

        for (int i = 0; i < 4 ; i++){
            if(i == randomButton){
                continue;
            }
            mButtons[i].setText(mItemList.get(i).Answer);
        }
    }

    private void checkRound(){ // For Check Round. This Game has 5 rounds.
        round++;
        if(round==5){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Result");
            builder.setMessage("You have "+score+" points\n\nDo you want to save score?");
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    TextView PlayerName = findViewById(R.id.Nameplayer);
                    String a= scoreTextView.getText().toString();
                    AppExecutors executors = new AppExecutors();
                    executors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() { // worker thread
                            AppDatabase db = AppDatabase.getInstance(GameActivity.this);// Add Name,Score to Database
                            db.userDao().addUser(
                                    new ScorePlayer(0,PlayerName.getText().toString(),Integer.valueOf(a)
                                    ));
                            finish();
                        }
                    });

                    score=0;
                    String temp= String.valueOf(score);
                    scoreTextView.setText(temp);
                    mItemList = new ArrayList<>(Arrays.asList(items));

                    Toast.makeText(GameActivity.this,"Save Complete!!!",Toast.LENGTH_SHORT).show();
                    finish();
                    newQuiz();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        }else{
            newQuiz();
        }

    }
    @Override
    public void onClick(View view) {
        Button b = findViewById(view.getId());
        String buttonText = b.getText().toString();

        if(mAnswerWord.equals(buttonText)){
            Toast.makeText(GameActivity.this,"✔ CORRECT",Toast.LENGTH_SHORT).show();
            score++;
            String temp= String.valueOf(score);
            scoreTextView.setText(temp);


        }else {
            Toast.makeText(GameActivity.this,"✖ INCORRECT!",Toast.LENGTH_SHORT).show();
        }
        checkRound();


    }
}