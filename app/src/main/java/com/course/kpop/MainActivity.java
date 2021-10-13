package com.course.kpop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    public static final int REQUEST_CODE_QUIZ = 101;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyhighscore";

    private int highscore;
    private TextView textViewHighscore;

    private long backPressedTime;

    private Button settingOpen;
    private Button quitButton;

    SettingDialog settingDialog;

    MediaPlayer mediaPlayer;
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.Main_start);
        textViewHighscore = findViewById(R.id.txtbestScore);
        settingOpen = findViewById(R.id.setting_Button);
        quitButton = findViewById(R.id.quit_Button);
        //mediaPlayer = mediaPlayer.create(this, R.raw.titlesound1);

        //audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

        Intent intent = getIntent();


        loadHighscore();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DifficultyActivity.class);
                startActivityForResult(intent,REQUEST_CODE_QUIZ);
            }
        });


        settingOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dial();

            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("데이터를 받았는가","?"+requestCode +" , " + resultCode + " , " + data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizMain.HIGH_SCORE, 0);
                Log.e("점수를 받았는가","?" + score);
                if (score > highscore){
                    updateHighscore(score);
                }
            }
        }
    }

    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;
        textViewHighscore.setText("Best: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Best: " + highscore);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            finish();
        }else{
            Toast.makeText(this, "한번 더 뒤로버튼을 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    private void dial(){
        settingDialog = new SettingDialog(this);
        settingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        settingDialog.setCancelable(false);
        settingDialog.show();


        Button cancelBtn = settingDialog.findViewById(R.id.btn_cancel);
        Button confirmBtn = settingDialog.findViewById(R.id.btn_confirm);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingDialog.dismiss();

            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingDialog.dismiss();

            }
        });

   }

}