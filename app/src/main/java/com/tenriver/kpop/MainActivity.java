package com.tenriver.kpop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_QUIZ = 101;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SHARED_MUSIC = "sharedMusic";
    public static final String KEY_HIGHSCORE = "keyhighscore";

    private int highscore;
    private TextView textViewHighscore;
    private TextView kpop1;
    private TextView kpop2;
    private TextView kpop3;
    private TextView mvquiz;

    private ImageView imageview_lp;

    private long backPressedTime;

    private Button tipsButton;
    private Button startButton;
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

        //텍스트 페이드인
        kpop1 = (TextView) findViewById((R.id.txtTitle1));
        kpop2 = (TextView) findViewById((R.id.txtTitle2));
        kpop3 = (TextView) findViewById((R.id.txtTitle3));
        mvquiz = (TextView) findViewById((R.id.txtMvQuiz));
        Animation textfadein = AnimationUtils.loadAnimation(getApplication(), R.anim.fade_in_text);
        kpop1.startAnimation(textfadein);
        kpop2.startAnimation(textfadein);
        kpop3.startAnimation(textfadein);
        mvquiz.startAnimation(textfadein);

        // BGN 실행
        if(mediaplayer_main ==null){
            mediaplayer_main = MediaPlayer.create(this, R.raw.selectmusic_new);
            mediaplayer_main.setLooping(true);
            mediaplayer_main.start();
        }

        SharedPreferences music = getSharedPreferences(SHARED_MUSIC,MODE_PRIVATE);

        Boolean bgmCb_main = music.getBoolean("bgmCb",true);
        Boolean effectCb_main = music.getBoolean("effectCb",true);

        imageview_lp = findViewById(R.id.imageView_lp);
        Animation lpLotate = AnimationUtils.loadAnimation(getApplication(), R.anim.rotate_lp);
        imageview_lp.startAnimation(textfadein);


        if(mediaplayer_main!=null){
            if(!bgmCb_main){
                imageview_lp.clearAnimation();
                mediaplayer_main.setVolume(0,0);
            }

            else{
                mediaplayer_main.setVolume(1,1);
                imageview_lp.startAnimation(lpLotate);
            }
        }


        tipsButton = findViewById(R.id.Main_tips);
        startButton = findViewById(R.id.Main_start);
        textViewHighscore = findViewById(R.id.txtbestScore);
        TextView textViewTitle = findViewById(R.id.txtTitle1);
        settingOpen = findViewById(R.id.setting_Button);
        quitButton = findViewById(R.id.quit_Button);



        textViewHighscore.startAnimation(textfadein);

        //audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);



        //Sound
        soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.buttonsound1,1);

        if(soundPool!=null){
            if(!effectCb_main){
                soundPoolVolume=0.0f;
            }

            else{
                soundPoolVolume=0.4f;
            }
        }


        Handler handler = new Handler();
        // 타이핑 이펙트

        /*
        textViewHighscore.setText("");
        textViewHighscore.setCharacterDelay(150);
        textViewHighscore.animateText(getString(R.string.bestscore));*/

        /*
        textViewTitle.setText("");
        textViewTitle.setCharacterDelay(150);
        textViewTitle.animateText(getString(R.string.Title));*/

        tipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tipsButton.setEnabled(false);
                startButton.setEnabled(false);
                settingOpen.setEnabled(false);
                quitButton.setEnabled(false);
                startButton.setTextColor(Color.GRAY);
                settingOpen.setTextColor(Color.GRAY);
                quitButton.setTextColor(Color.GRAY);

                // 다시보지않기
                SharedPreferences closef = getSharedPreferences(TitleActivity.SHARED_CLOSE,MODE_PRIVATE);
                SharedPreferences.Editor closeEditor = closef.edit();
                closeEditor.putBoolean("closeforever", true);
                closeEditor.apply();

                if(mediaplayer_main!=null)
                {
                    mediaplayer_main.stop();
                    mediaplayer_main.release();
                    mediaplayer_main = null;
                }

                Intent intent = new Intent(getApplicationContext(), TipsActivity.class);
                startActivity(intent);

                finish();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                soundPool.play(soundID,soundPoolVolume,soundPoolVolume,0,0,1f);

                startButton.setEnabled(false);
                settingOpen.setEnabled(false);
                quitButton.setEnabled(false);
                startButton.setTextColor(Color.GRAY);
                settingOpen.setTextColor(Color.GRAY);
                quitButton.setTextColor(Color.GRAY);

                //리모컨이미지 내려감
                Animation RemoteDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remote.startAnimation(RemoteDown);

                //리모컨버튼 내려감
                Animation RemoteButtonDown = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_down);
                remotebutton.startAnimation(RemoteButtonDown);



                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), YearActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                        startActivityForResult(intent,REQUEST_CODE_QUIZ);

                        // 액티비티 이동시 페이드인아웃 연출
                        //overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        finish();

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

        if(!mediaplayer_main.isPlaying())
        {
            // BGN 실행
            mediaplayer_main = MediaPlayer.create(this, R.raw.selectmusic_new);
            mediaplayer_main.setLooping(true);
            mediaplayer_main.start();

        }

        loadHighscore();
        Intent intent = getIntent();
        int score = intent.getIntExtra(QuizMain.HIGH_SCORE, 0);
        Log.e("점수를 받았는가","?" + score);
        if (score > highscore){
            updateHighscore(score);
        }


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

        soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.buttonsound1,1);

        if(soundPool!=null){
            if(!effectCb_main){
                soundPoolVolume=0.0f;
            }

            else{
                soundPoolVolume=0.4f;
            }
        }

        ImageView remote = (ImageView) findViewById(R.id.Remote_main);
        ConstraintLayout remotebutton = (ConstraintLayout) findViewById(R.id.Remote_button_main);

        //리모컨이미지 올라옴
        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote.startAnimation(RemoteUp);

        //리모컨버튼 올라옴
        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton.startAnimation(RemoteButtonUp);

        startButton.setEnabled(true);
        settingOpen.setEnabled(true);
        quitButton.setEnabled(true);
        startButton.setTextColor(Color.WHITE);
        settingOpen.setTextColor(Color.WHITE);
        quitButton.setTextColor(Color.WHITE);
    }

    /*
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
    }*/

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
            if(mediaplayer_main!=null)
            {
                mediaplayer_main.stop();
                mediaplayer_main.release();
                mediaplayer_main = null;
            }
        }else{
            Toast.makeText(this, getString(R.string.BackQuit), Toast.LENGTH_SHORT).show();
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

                Animation lpLotate = AnimationUtils.loadAnimation(getApplication(), R.anim.rotate_lp);


                if(mediaplayer_main!=null){
                    if(!bgmCb.isChecked()){
                        mediaplayer_main.setVolume(0,0);
                        imageview_lp.clearAnimation();
                    }

                    else{
                        mediaplayer_main.setVolume(1,1);
                        imageview_lp.startAnimation(lpLotate);
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

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();


        mediaplayer_main.pause();

    }
}