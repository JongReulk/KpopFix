package com.tenriver.kpop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ModeActivity extends AppCompatActivity {

    private boolean isFinished = false;

    //Sound
    SoundPool soundPool;	//작성
    int soundID;		//작성

    private float soundPoolVolume;

    Button basicButton;
    Button challengeButton;
    TextView basicTxt;
    TextView challengeTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode);


        basicButton = findViewById(R.id.basic_Button);
        challengeButton = findViewById(R.id.challenge_Button);
        basicTxt = findViewById(R.id.txt_basicmode);
        challengeTxt = findViewById(R.id.txt_challenge);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {


            }
        });

        AdView mAdview = findViewById(R.id.mode_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("\n" + "ca-app-pub-3940256099942544/6300978111");


        if (!MainActivity.mediaplayer_main.isPlaying()) {
            MainActivity.mediaplayer_main.start();
        }


        ImageView remote_mode = (ImageView) findViewById(R.id.Remote_mode);
        ConstraintLayout remotebutton_mode = (ConstraintLayout) findViewById(R.id.Remote_button_mode);

        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote_mode.startAnimation(RemoteUp);

        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton_mode.startAnimation(RemoteButtonUp);

        RemoteUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                basicButton.setEnabled(false);
                challengeButton.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                basicButton.setEnabled(true);
                challengeButton.setEnabled(true);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        SharedPreferences music = getSharedPreferences(MainActivity.SHARED_MUSIC, MODE_PRIVATE);

        Boolean bgmCb_difficulty = music.getBoolean("bgmCb", true);
        Boolean effectCb_difficulty = music.getBoolean("effectCb", true);

        ImageView imageview_lp = findViewById(R.id.imageView_lp);
        Animation lpLotate = AnimationUtils.loadAnimation(getApplication(), R.anim.rotate_lp);

        if (bgmCb_difficulty) {
            imageview_lp.startAnimation(lpLotate);
        } else {
            imageview_lp.clearAnimation();
        }

        //Sound
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);    //작성
        soundID = soundPool.load(this, R.raw.buttonsound1, 1);


        if (soundPool != null) {
            if (!effectCb_difficulty) {
                soundPoolVolume = 0.0f;
            } else {
                soundPoolVolume = 0.5f;
            }
        }

        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID, soundPoolVolume, soundPoolVolume, 0, 0, 1f);

                basicButton.setEnabled(false);
                challengeButton.setEnabled(false);

                basicButton.setTextColor(Color.GRAY);
                challengeButton.setTextColor(Color.GRAY);


                challengeTxt.setVisibility(View.GONE);

                //리모컨이미지 내려감
                Animation RemoteDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remote_mode.startAnimation(RemoteDown);

                //리모컨버튼 내려감
                Animation RemoteButtonDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remotebutton_mode.startAnimation(RemoteButtonDown);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        if (!isFinished) {
                            Intent Modeintent = new Intent(getApplicationContext(), YearActivity.class);

                            Modeintent.putExtra("game_mode", 100); // challenge 선택
                            Modeintent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                            startActivity(Modeintent);
                            finish();
                        } else {
                            handler.removeCallbacks(this);
                        }

                    }
                }, 600);

            }
        });

        challengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID, soundPoolVolume, soundPoolVolume, 0, 0, 1f);

                basicButton.setEnabled(false);
                challengeButton.setEnabled(false);

                basicButton.setTextColor(Color.GRAY);
                challengeButton.setTextColor(Color.GRAY);

                basicTxt.setVisibility(View.GONE);


                //리모컨이미지 내려감
                Animation RemoteDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remote_mode.startAnimation(RemoteDown);

                //리모컨버튼 내려감
                Animation RemoteButtonDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remotebutton_mode.startAnimation(RemoteButtonDown);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinished) {
                            Intent Modeintent = new Intent(getApplicationContext(), QuizMain.class);
                            Modeintent.putExtra("game_mode", 100); // challenge 선택

                            Modeintent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                            startActivity(Modeintent);
                            finish();
                        } else {
                            handler.removeCallbacks(this);
                        }
                    }
                }, 600);
            }
        });
    }


    /*
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
    }*/

    @Override
    protected void onStart() {
        super.onStart();

        if(!MainActivity.mediaplayer_main.isPlaying())
        {
            MainActivity.mediaplayer_main.start();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isFinished = true;

        Intent mainintent = new Intent(this,MainActivity.class);
        startActivity(mainintent);
        finish();

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        MainActivity.mediaplayer_main.pause();
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

