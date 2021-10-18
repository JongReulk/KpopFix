package com.course.kpop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import android.icu.text.CaseMap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import android.media.SoundPool;
import android.os.Handler;
import android.media.MediaPlayer;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    public static final int REQUEST_CODE_QUIZ = 101;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SHARED_MUSIC = "sharedMusic";
    public static final String KEY_HIGHSCORE = "keyhighscore";

    private int highscore;
    private TextView textViewHighscore;
    private TextView textViewTitle;

    private long backPressedTime;

    private Button settingOpen;
    private Button quitButton;

    SettingDialog settingDialog;
    private float soundPoolVolume;


    AudioManager audioManager;

    //Sound
    SoundPool soundPool;	//작성
    int soundID;		    //작성

    // MediaPlayer 객체생성
    public static MediaPlayer mediaplayer_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리모컨 이미지
        ImageView remote = (ImageView) findViewById(R.id.Remote_main);
        ConstraintLayout remotebutton = (ConstraintLayout) findViewById(R.id.Remote_button_main);

        //리모컨이미지 올라옴
        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote.startAnimation(RemoteUp);

        //리모컨버튼 올라옴
        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton.startAnimation(RemoteButtonUp);

        //텍스트 페이드인
        textViewTitle = (TextView) findViewById((R.id.txtTitle));
        Animation textfadein = AnimationUtils.loadAnimation(getApplication(), R.anim.fade_in_text);
        textViewTitle.startAnimation(textfadein);

        // BGN 실행
        mediaplayer_main = MediaPlayer.create(this, R.raw.selectmusic2);
        mediaplayer_main.setLooping(true);
        mediaplayer_main.start();

        SharedPreferences music = getSharedPreferences(SHARED_MUSIC,MODE_PRIVATE);

        Boolean bgmCb_main = music.getBoolean("bgmCb",true);
        Boolean effectCb_main = music.getBoolean("effectCb",true);


        if(mediaplayer_main!=null){
            if(!bgmCb_main){
                mediaplayer_main.setVolume(0,0);
            }

            else{
                mediaplayer_main.setVolume(1,1);
            }
        }



        startButton = findViewById(R.id.Main_start);
        TypingEffect textViewHighscore = findViewById(R.id.txtbestScore);
        TypingEffect textViewTitle = findViewById(R.id.txtTitle);
        settingOpen = findViewById(R.id.setting_Button);
        quitButton = findViewById(R.id.quit_Button);

        //audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

        Intent intent = getIntent();
        loadHighscore();

        //Sound
        soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.buttonsound1,1);

        if(soundPool!=null){
            if(!effectCb_main){
                soundPoolVolume=0.0f;
            }

            else{
                soundPoolVolume=1.0f;
            }
        }


        Handler handler = new Handler();



        // 타이핑 이펙트

        textViewHighscore.setText("");
        textViewHighscore.setCharacterDelay(150);
        textViewHighscore.animateText(getString(R.string.bestscore));

        /*
        textViewTitle.setText("");
        textViewTitle.setCharacterDelay(150);
        textViewTitle.animateText(getString(R.string.Title));*/


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);

                //리모컨이미지 내려감
                Animation RemoteDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remote.startAnimation(RemoteDown);

                //리모컨버튼 내려감
                Animation RemoteButtonDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remotebutton.startAnimation(RemoteButtonDown);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), DifficultyActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_QUIZ);

                        // 액티비티 이동시 페이드인아웃 연출
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    }
                }, 600); //딜레이 타임 조절
            }
        });


        settingOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);
                dial();

            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);

                // BGM 종료
                if(mediaplayer_main!=null)
                {
                    mediaplayer_main.stop();
                    mediaplayer_main.release();
                    mediaplayer_main = null;
                }
                finish();

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mediaplayer_main!=null)
        {
            mediaplayer_main.start();
        }
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
        //textViewHighscore.setText("Best: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        //textViewHighscore.setText("Best: " + highscore);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            finish();
            if(mediaplayer_main!=null)
            {
                mediaplayer_main.stop();
                mediaplayer_main.release();
                mediaplayer_main = null;
            }
        }else{
            Toast.makeText(this, "한번 더 뒤로버튼을 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    private void dial(){
        settingDialog = new SettingDialog(this);
        settingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button cancelBtn = settingDialog.findViewById(R.id.btn_cancel);
        Button confirmBtn = settingDialog.findViewById(R.id.btn_confirm);
        CheckBox bgmCb = settingDialog.findViewById(R.id.backgroundMusicCb);
        CheckBox effectCb = settingDialog.findViewById(R.id.effectCb);

        SharedPreferences music = getSharedPreferences(SHARED_MUSIC,MODE_PRIVATE);

        Boolean bgm_B = music.getBoolean("bgmCb",true);
        Boolean effect_B = music.getBoolean("effectCb",true);

        bgmCb.setChecked(bgm_B);
        effectCb.setChecked(effect_B);

        settingDialog.setCancelable(false); // 밖에 선택해도 창이 안꺼짐
        settingDialog.show();


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);
                settingDialog.dismiss();

            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor musicEditor = music.edit();
                musicEditor.putBoolean("bgmCb", (bgmCb.isChecked()));
                musicEditor.putBoolean("effectCb", (effectCb.isChecked()));
                musicEditor.apply();

                if(mediaplayer_main!=null){
                    if(!bgmCb.isChecked()){
                        mediaplayer_main.setVolume(0,0);
                    }

                    else{
                        mediaplayer_main.setVolume(1,1);
                    }
                }

                if(mediaplayer_main!=null){
                    if(!effectCb.isChecked()){
                        soundPoolVolume=0.0f;
                    }

                    else{
                        soundPoolVolume=1.0f;
                    }
                }

                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);
                settingDialog.dismiss();

            }
        });

   }

}