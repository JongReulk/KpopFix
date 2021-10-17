package com.course.kpop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.media.SoundPool;
import android.os.Handler;

public class  DifficultyActivity extends AppCompatActivity {

    private Button easyButton;
    private Button normalButton;
    private Button hardButton;

    private int highscore;
    private TextView textViewHighscore;
    private TextView textViewGuidegame;
    private TextView textLevel;
    private ConstraintLayout layout_caution;
    private ConstraintLayout layout_tip;

    private TextView easyTip;
    private TextView normalTip;
    private TextView hardTip;

    private boolean isFinished = false;

    //Sound
    SoundPool soundPool;	//작성
    int soundID;		//작성

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);


        easyButton = findViewById(R.id.easy_Button);
        normalButton = findViewById(R.id.normal_Button);
        hardButton = findViewById(R.id.hard_Button);

        textLevel = findViewById(R.id.txt_level);

        easyTip = findViewById(R.id.txt_easytip);
        normalTip = findViewById(R.id.txt_normaltip);
        hardTip = findViewById(R.id.txt_hardtip);


        ImageView remote_difficulty = (ImageView) findViewById(R.id.Remote_difficulty);
        ConstraintLayout remotebutton_difficulty = (ConstraintLayout) findViewById(R.id.Remote_button_difficulty);

        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote_difficulty.startAnimation(RemoteUp);

        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton_difficulty.startAnimation(RemoteButtonUp);


        //텍스트뷰 특정 부분 색깔 바꾸기
        Spannable easyspan = (Spannable) easyTip.getText();
        easyspan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.easy_color)), 0, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        Spannable normalspan = (Spannable) normalTip.getText();
        normalspan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.teal_200)), 0, 6, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        Spannable hardspan = (Spannable) hardTip.getText();
        hardspan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.hard_color)), 0, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        layout_caution = findViewById(R.id.layout_caution);
        layout_tip = findViewById(R.id.layout_difficultyTip);

        layout_caution.setVisibility(View.GONE);

        //Sound
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.buttonsound1,1);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID,1f,1f,0,0,1f);

                easyButton.setEnabled(false);
                normalButton.setEnabled(false);
                hardButton.setEnabled(false);
                easyButton.setTextColor(Color.GRAY);
                normalButton.setTextColor(Color.GRAY);
                hardButton.setTextColor(Color.GRAY);

                layout_tip.setVisibility(View.GONE);
                layout_caution.setVisibility(View.VISIBLE);
                textLevel.setText(getString(R.string.Easy));
                textLevel.setTextColor(getResources().getColor(R.color.easy_color));

                //리모컨이미지 내려감
                Animation RemoteDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remote_difficulty.startAnimation(RemoteDown);

                //리모컨버튼 내려감
                Animation RemoteButtonDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remotebutton_difficulty.startAnimation(RemoteButtonDown);

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
                },600);

            }
        });

        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID,1f,1f,0,0,1f);

                easyButton.setEnabled(false);
                normalButton.setEnabled(false);
                hardButton.setEnabled(false);
                easyButton.setTextColor(Color.GRAY);
                normalButton.setTextColor(Color.GRAY);
                hardButton.setTextColor(Color.GRAY);

                layout_tip.setVisibility(View.GONE);
                layout_caution.setVisibility(View.VISIBLE);
                textLevel.setText(getString(R.string.Normal));
                textLevel.setTextColor(getResources().getColor(R.color.teal_200));

                //리모컨이미지 내려감
                Animation RemoteDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remote_difficulty.startAnimation(RemoteDown);

                //리모컨버튼 내려감
                Animation RemoteButtonDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remotebutton_difficulty.startAnimation(RemoteButtonDown);

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
                },600);
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID,1f,1f,0,0,1f);

                easyButton.setEnabled(false);
                normalButton.setEnabled(false);
                hardButton.setEnabled(false);
                easyButton.setTextColor(Color.GRAY);
                normalButton.setTextColor(Color.GRAY);
                hardButton.setTextColor(Color.GRAY);

                layout_tip.setVisibility(View.GONE);
                layout_caution.setVisibility(View.VISIBLE);
                textLevel.setText(getString(R.string.Hard));
                textLevel.setTextColor(getResources().getColor(R.color.hard_color));

                //리모컨이미지 내려감
                Animation RemoteDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remote_difficulty.startAnimation(RemoteDown);

                //리모컨버튼 내려감
                Animation RemoteButtonDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remotebutton_difficulty.startAnimation(RemoteButtonDown);

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
                },600);
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