package com.tenriver.kpop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
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
import android.widget.TextView;
import android.media.SoundPool;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class  DifficultyActivity extends AppCompatActivity {

    private Button easyButton;
    private Button normalButton;
    private Button hardButton;

    private int highscore;
    private int year_num_Difficulty;
    private TextView textViewHighscore;
    private TextView textViewGuidegame;
    private TextView difficulty_title;
    private TextView textLevel;
    private ConstraintLayout layout_caution;
    private ConstraintLayout layout_tip;

    private TextView easyTip;
    private TextView normalTip;
    private TextView hardTip;

    private TextView kpop1;
    private TextView kpop2;
    private TextView kpop3;

    //광고
    private AdView mAdview;
    private InterstitialAd screenAd;

    private static final String SCREENAD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    private boolean isFinished = false;

    //Sound
    SoundPool soundPool;	//작성
    int soundID;		//작성

    private float soundPoolVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {


            }
        });

        mAdview = findViewById(R.id.difficulty_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("\n" + "ca-app-pub-3940256099942544/6300978111");


        if(!MainActivity.mediaplayer_main.isPlaying())
        {
            MainActivity.mediaplayer_main.start();
        }



        difficulty_title = findViewById(R.id.difficultyTitle);
        easyButton = findViewById(R.id.easy_Button);
        normalButton = findViewById(R.id.normal_Button);
        hardButton = findViewById(R.id.hard_Button);

        textLevel = findViewById(R.id.txt_level);

        easyTip = findViewById(R.id.txt_easytip);
        normalTip = findViewById(R.id.txt_normaltip);
        hardTip = findViewById(R.id.txt_hardtip);

        kpop1 = (TextView) findViewById((R.id.txtTitle1));
        kpop2 = (TextView) findViewById((R.id.txtTitle2));
        kpop3 = (TextView) findViewById((R.id.txtTitle3));

        //페이드인 애니메이션
        Animation textfadein = AnimationUtils.loadAnimation(getApplication(), R.anim.fade_in_text);
        difficulty_title.startAnimation(textfadein);
        easyTip.startAnimation(textfadein);
        normalTip.startAnimation(textfadein);
        hardTip.startAnimation(textfadein);
        kpop1.startAnimation(textfadein);
        kpop2.startAnimation(textfadein);
        kpop3.startAnimation(textfadein);


        ImageView remote_difficulty = (ImageView) findViewById(R.id.Remote_difficulty);
        ConstraintLayout remotebutton_difficulty = (ConstraintLayout) findViewById(R.id.Remote_button_difficulty);

        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote_difficulty.startAnimation(RemoteUp);

        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton_difficulty.startAnimation(RemoteButtonUp);

        RemoteUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                easyButton.setEnabled(false);
                normalButton.setEnabled(false);
                hardButton.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                easyButton.setEnabled(true);
                normalButton.setEnabled(true);
                hardButton.setEnabled(true);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


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

        SharedPreferences music = getSharedPreferences(MainActivity.SHARED_MUSIC,MODE_PRIVATE);

        Boolean bgmCb_difficulty = music.getBoolean("bgmCb",true);
        Boolean effectCb_difficulty = music.getBoolean("effectCb",true);

        ImageView imageview_lp = findViewById(R.id.imageView_lp);
        Animation lpLotate = AnimationUtils.loadAnimation(getApplication(), R.anim.rotate_lp);

        if(bgmCb_difficulty){
            imageview_lp.startAnimation(lpLotate);
        }

        else{
            imageview_lp.clearAnimation();
        }

        //Sound
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.buttonsound1,1);

        Intent intent = getIntent();
        final int year_num_Difficulty = intent.getIntExtra("year_num",2020);

        if(soundPool!=null){
            if(!effectCb_difficulty){
                soundPoolVolume=0.0f;
            }

            else{
                soundPoolVolume=0.5f;
            }
        }

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);

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
                            Intent Quizintent = new Intent(getApplicationContext(),QuizMain.class);
                            Quizintent.putExtra("difficulty_time",10000); // 10초
                            Quizintent.putExtra("year_num",year_num_Difficulty);
                            Quizintent.putExtra("game_mode", 0);
                            Quizintent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                            startActivity(Quizintent);
                            finish();
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
                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);

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
                            Intent Quizintent = new Intent(getApplicationContext(),QuizMain.class);
                            Quizintent.putExtra("difficulty_time",5000); // 5초
                            Quizintent.putExtra("year_num",year_num_Difficulty);
                            Quizintent.putExtra("game_mode", 0);
                            Quizintent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                            startActivity(Quizintent);
                            finish();
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
                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);

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
                            Intent Quizintent = new Intent(getApplicationContext(),QuizMain.class);
                            Quizintent.putExtra("difficulty_time",3000); // 3초
                            Quizintent.putExtra("year_num",year_num_Difficulty);
                            Quizintent.putExtra("game_mode", 0);
                            Quizintent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                            startActivity(Quizintent);
                            finish();
                        }

                        else{
                            handler.removeCallbacks(this);
                        }
                    }
                },600);
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

        Intent yearintent = new Intent(this,YearActivity.class);
        startActivity(yearintent);
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