package com.course.kpop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class  DifficultyActivity extends AppCompatActivity {


    private Button easyButton;
    private Button normalButton;
    private Button hardButton;

    private int highscore;
    private TextView textViewHighscore;
    private TextView textViewGuidegame;
    private TextView textLevel;
    private LinearLayout difficultyLinear;

    private boolean isFinished = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);


        easyButton = findViewById(R.id.easy_Button);
        normalButton = findViewById(R.id.normal_Button);
        hardButton = findViewById(R.id.hard_Button);

        textViewHighscore = findViewById(R.id.question_num);
        textViewGuidegame = findViewById(R.id.guide_level);
        textLevel = findViewById(R.id.txt_level);
        difficultyLinear = findViewById(R.id.difficulty_layout);

        textViewGuidegame.setVisibility(View.GONE);
        textLevel.setVisibility(View.GONE);


        //Intent intent = getIntent();







        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultyLinear.setVisibility(View.GONE);
                textViewGuidegame.setVisibility(View.VISIBLE);
                textLevel.setVisibility(View.VISIBLE);
                textLevel.setText(getString(R.string.Easy));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(!isFinished){
                            Intent intent = new Intent(getApplicationContext(),QuizMain.class);
                            intent.putExtra("difficulty_time",10000); // 10초
                            startActivityForResult(intent,MainActivity.REQUEST_CODE_QUIZ);

                        }

                        else{
                            handler.removeCallbacks(this);
                        }

                    }
                },2000);

            }
        });

        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultyLinear.setVisibility(View.GONE);
                textViewGuidegame.setVisibility(View.VISIBLE);
                textLevel.setVisibility(View.VISIBLE);
                textLevel.setText(getString(R.string.Normal));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(!isFinished){
                            Intent intent = new Intent(getApplicationContext(),QuizMain.class);
                            intent.putExtra("difficulty_time",5000); // 5초
                            startActivityForResult(intent,MainActivity.REQUEST_CODE_QUIZ);
                        }

                        else{
                            handler.removeCallbacks(this);
                        }
                    }
                },2000);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultyLinear.setVisibility(View.GONE);
                textViewGuidegame.setVisibility(View.VISIBLE);
                textLevel.setVisibility(View.VISIBLE);
                textLevel.setText(getString(R.string.Hard));
                textLevel.setTextColor(Color.RED);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(!isFinished){
                            Intent intent = new Intent(getApplicationContext(),QuizMain.class);
                            intent.putExtra("difficulty_time",3000); // 3초
                            startActivityForResult(intent,MainActivity.REQUEST_CODE_QUIZ);
                        }

                        else{
                            handler.removeCallbacks(this);
                        }
                    }
                },2000);
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("데이터를 받았는가","?");

        if (requestCode == MainActivity.REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizMain.HIGH_SCORE, 0);
                Log.e("점수를 받았는가22","?" + score);
                Intent resultIntent = new Intent();
                resultIntent.putExtra(QuizMain.HIGH_SCORE, score);
                Log.e("최고 점수",":" + score);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isFinished = true;
    }

    /*
    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;
        textViewHighscore.setText("최고 점수: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("최고 점수: " + highscore);
    }*/


}