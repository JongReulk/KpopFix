package com.tenriver.kpop;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.tenriver.kpop.MainActivity.KEY_POINT;
import static com.tenriver.kpop.MainActivity.SHARED_POINT;

public class QuizAlphabet extends YouTubeBaseActivity {
    public static final String ALPHABETHIGH_SCORE = "alphabethighScore";
    private static final long COUNTDOWN_IN_MILLIS = 30500;

    private static final String QUIZ_SHARED = "quizshared";
    public static final String SHARED_MUSIC = "sharedMusic";
    
    private static final String INTERSTITIAL_AD_ID = "ca-app-pub-3940256099942544/1033173712";
    static int score = 0;
    static int plus = 0;
    static int pointplus = 0;
    static int videoLength;// ?????? ?????? ????????? ????????? ??????
    
    private String korean_Answer;
    private String english_Answer;
    private String alphabet_Question;
    private String singer_hint;
    private String realAnswer;
    
    private InterstitialAd screenAd;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private TextView txtQuestionCount;
    private TextView txtCountDown;
    private TextView scoreText;
    private EditText answerText;
    private TextView txtAlpha;
    private TextView txtSinger;
    private TextView correctText;
    private TextView realAnswerText;

    private Button confirmButton;
    private Button nextButton;
    private boolean isHandler = true;
    Handler handler;

    private long backPressedTime;
    

    private boolean isCountStart;
    private boolean isFirst = true;

    private ImageView leftSpeaker;
    private ImageView rightSpeaker;

    boolean isStarted =true;


    boolean isBackPressed = false;
    boolean isFinished = false;
    boolean isError = false;

    private int question_Num;


    // hint button
    private Button HintButton1; // ?????? ??????

    // point
    private static int hintPoint;
    private TextView txtHintPoint;

    // ????????? ??????
    private int basic_playtime = 0;

    private ImageView endimage;

    // MediaPlayer ????????????
    public static MediaPlayer mediaplayer_alphabet;

    //Sound
    SoundPool soundPool;	//??????
    int correctSound;		    //??????
    int wrongSound;

    private float soundPoolVolume;

    private int randomAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_alphabet);

        Log.d("start","quiz alphabet activity start!");

        question_Num = 0;

        plus = 10;

        // ?????? bgm ?????????
        if(MainActivity.mediaplayer_main!=null)
        {
            MainActivity.mediaplayer_main.stop();
            MainActivity.mediaplayer_main.release();
            MainActivity.mediaplayer_main = null;
        }

        // BGN ??????
        if(mediaplayer_alphabet ==null){
            mediaplayer_alphabet = MediaPlayer.create(this, R.raw.chosungmusic);
            mediaplayer_alphabet.setLooping(true);
            mediaplayer_alphabet.start();
        }

        SharedPreferences music = getSharedPreferences(SHARED_MUSIC,MODE_PRIVATE);

        Boolean bgmCb_main = music.getBoolean("bgmCb",true);
        Boolean effectCb_main = music.getBoolean("effectCb",true);



        if(mediaplayer_alphabet!=null){
            if(!bgmCb_main){
                mediaplayer_alphabet.setVolume(0,0);
            }

            else{
                mediaplayer_alphabet.setVolume(1,1);
            }
        }

        //Sound
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);	//??????
        wrongSound = soundPool.load(this,R.raw.wrongsound,1);
        correctSound = soundPool.load(this,R.raw.correctsound,1);

        if(soundPool!=null){
            if(!effectCb_main){
                soundPoolVolume=0.0f;
            }

            else{
                soundPoolVolume=1f;
            }
        }


        score = 0;


        Intent intent = getIntent();

        try {
            GamesClient gamesClient = Games.getGamesClient(this,GoogleSignIn.getLastSignedInAccount(this));
            gamesClient.setViewForPopups(findViewById(R.id.googlePopupQuizMain));
            //gamesClient.setGravityForPopups(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        } catch(Exception e){
            Log.d("??????", "?????? ???????????? ??????");
        }




        int year_num = intent.getIntExtra("year_num",2020);


        confirmButton = findViewById(R.id.confirmButton);
        nextButton = findViewById(R.id.nextButton);
        scoreText = findViewById(R.id.scoreText);


        answerText = findViewById(R.id.editText);

        answerText.setBackground(getResources().getDrawable(R.drawable.border_button_gray));
        //textColorDefaultCd = txtCountDown.getTextColors();

        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        txtCountDown = findViewById(R.id.txtCountDown);

        // Hint Button
        HintButton1 = findViewById(R.id.Quiz_hint1);  // ?????? ??????


        txtHintPoint = findViewById(R.id.txt_HintPoint);

        correctText = findViewById(R.id.correctText);
        correctText.setVisibility(View.GONE);

        // end ?????????
        endimage = findViewById(R.id.EndImage);
        
        // ????????? ?????? ?????????
        txtAlpha = findViewById(R.id.txt_alphabet);
        txtSinger = findViewById(R.id.txt_singer);
        realAnswerText = findViewById(R.id.txt_real_answer);

        txtSinger.setVisibility(View.INVISIBLE);
        txtAlpha.setText("");
        realAnswerText.setVisibility(View.INVISIBLE);


        // DB ?????? ??????
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        if(year_num == 101){
            questionList = dbHelper.getAllQuestions();
        }
        else {
            String year_num_string = String.valueOf(year_num);
            Log.e("year_QUIZAlphabet", " : " +year_num_string);

            questionList = dbHelper.getQuestions(year_num_string);
            Log.e("Question", " : "+year_num_string);
        }

        questionCountTotal = 10;



        Collections.shuffle(questionList);

        // ????????? ???????????????
        leftSpeaker = findViewById(R.id.imageView_speakerleft);
        rightSpeaker = findViewById(R.id.imageView_speakerright);

        Animation speakerleft_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_leftanim);

        leftSpeaker.startAnimation(speakerleft_anim);
        Animation speakerright_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_rightanim);
        rightSpeaker.startAnimation(speakerright_anim);

        // ?????? ?????? ????????????
        nextButton.setEnabled(false);
        nextButton.setTextColor(Color.GRAY);

        isHandler = true;

        ImageView remote_quiz = (ImageView) findViewById(R.id.Remote_quizalphabet);
        ConstraintLayout remotebutton_quiz = (ConstraintLayout) findViewById(R.id.Remote_button_alphabet);

        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote_quiz.startAnimation(RemoteUp);

        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton_quiz.startAnimation(RemoteButtonUp);


        confirmButton.setEnabled(false);
        confirmButton.setTextColor(Color.GRAY);

        // ?????? ?????? ?????????
        HintButton1.setEnabled(false);


        // ????????? ????????????
        SharedPreferences point = getSharedPreferences(SHARED_POINT,MODE_PRIVATE);

        hintPoint = point.getInt(KEY_POINT,100);

        txtHintPoint.setText(""+hintPoint);

        SharedPreferences quiz_shared = getSharedPreferences(QUIZ_SHARED,MODE_PRIVATE);


        showNextQuestion();



        // ?????? ??????
        HintButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hintPoint < 10){
                    Toast.makeText(getApplicationContext(), getString(R.string.lessPoint), Toast.LENGTH_SHORT).show();

                }
                else {
                    hintPoint = hintPoint - 10;

                    txtHintPoint.setText(""+hintPoint);
                    Toast.makeText(getApplicationContext(), getString(R.string.currentPoint) + hintPoint, Toast.LENGTH_SHORT).show();
                    Log.v("?????? ??????", "???????????? ?????? ??????");


                    isStarted = true;

                    txtSinger.setVisibility(View.VISIBLE);
                    txtSinger.setSingleLine(true);
                    txtSinger.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    txtSinger.setSelected(true);

                    HintButton1.setEnabled(false);

                    leftSpeaker.startAnimation(speakerleft_anim);
                    rightSpeaker.startAnimation(speakerright_anim);

                }

            }
        });






        // ?????? ?????? ?????? checkAnswer ?????? ????????????
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeLeftInMillis = COUNTDOWN_IN_MILLIS;

                answerText.setBackground(getResources().getDrawable(R.drawable.border_button_gray));

                nextButton.setEnabled(false);
                nextButton.setTextColor(Color.GRAY);
                answerText.setText("");
                answerText.setVisibility(View.VISIBLE);

                txtSinger.setVisibility(View.INVISIBLE);
                txtSinger.setText("");

                realAnswerText.setVisibility(View.INVISIBLE);


                if(questionCounter >= questionCountTotal)
                {
                    txtAlpha.setVisibility(View.GONE);
                    txtSinger.setVisibility(View.GONE);
                    endimage.setVisibility(View.VISIBLE);
                    Animation end_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.fade_in);
                    endimage.startAnimation(end_anim);
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finishQuiz();
                        }
                    },2000);
                }
                else {

                    showNextQuestion();
                    leftSpeaker.startAnimation(speakerleft_anim);
                    rightSpeaker.startAnimation(speakerright_anim);

                }
            }

        });
    } // onCreate


    private void showNextQuestion() {
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            korean_Answer = currentQuestion.getKoreanAnswer(); // ?????? ??????
            english_Answer = currentQuestion.getEnglishAnswer2(); // ?????? ??????
            alphabet_Question = currentQuestion.getHintAll();
            singer_hint = currentQuestion.getSinger();
            realAnswer = currentQuestion.getRealAnswer();
            txtAlpha.setEllipsize(null);
            txtSinger.setEllipsize(null);



            questionCounter ++;
            if(!isError){
                question_Num++;
            }
            else{
                isError = false;
            }
            txtQuestionCount.setText(question_Num + " / " + questionCountTotal); // ?????? ??? ??????
            confirmButton.setText(getString(R.string.Confirm));

            txtAlpha.setText(alphabet_Question);
            txtSinger.setText(singer_hint);
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // confirm ?????? ?????? ??? ??????
                    txtAlpha.setSingleLine(true);
                    txtAlpha.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    txtAlpha.setSelected(true);
                    txtSinger.setSingleLine(true);
                    txtSinger.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    txtSinger.setSelected(true);

                    confirmButton.setEnabled(true);
                    confirmButton.setTextColor(Color.WHITE);
                }
            },1000);


            //co

            //????????? ?????? ??????
            isCountStart = true;


            isStarted = true;

            startCountDown();

            checkLast();

        }

    }

    private void startCountDown() {

        if(isCountStart) {
            // ?????? ?????? ?????????
            HintButton1.setEnabled(true);

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    timeLeftInMillis = 0;
                    updateCountDownText();
                    checkAnswer();
                }
            }.start();
        }
        else{
        }
        isCountStart=false;
    }

    private void updateCountDownText(){
        int minutes = (int)(timeLeftInMillis / 1000 ) / 60;
        int seconds = (int)(timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.KOREA, "%02d", seconds);

        txtCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            txtCountDown.setTextColor(getResources().getColor(R.color.hard_color));
        }
        else{
            txtCountDown.setTextColor(getResources().getColor(R.color.teal_200));
        }
    }

    // ???????????? ???????????? ??????
    // ????????? ???????????? ???????????? ??????
    // ???????????? ?????? ??????
    private void checkAnswer() {
        countDownTimer.cancel(); // ????????? ??????

        // ?????? ?????? ????????????
        HintButton1.setEnabled(false);

        nextButton.setEnabled(true);
        nextButton.setTextColor(Color.WHITE);
        confirmButton.setEnabled(false);
        confirmButton.setTextColor(Color.GRAY);

        Animation speakerleft_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_leftanim);
        Animation speakerright_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_rightanim);

        leftSpeaker.startAnimation(speakerleft_anim);
        rightSpeaker.startAnimation(speakerright_anim);
        realAnswerText.setVisibility(View.VISIBLE);
        realAnswerText.setSingleLine(true);
        realAnswerText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        realAnswerText.setSelected(true);
        realAnswerText.setText(realAnswer);

        //?????? ?????? ??? ???????????? ?????? ?????????
        String answer = answerText.getText().toString().trim().replace(" ","");
        //answer.replaceAll(" ","");
        answer = answer.toLowerCase();
        Log.e("??? ???"," : "+answer);
        Log.e("???"," : "+korean_Answer);
        Log.e("???"," : "+english_Answer);

        // ?????? ?????? ????????? equals ??????
        if(answer.equals(korean_Answer) || answer.equals(english_Answer)){
            soundPool.play(correctSound,soundPoolVolume,soundPoolVolume,0,0,1f);
            answerText.setBackground(getResources().getDrawable(R.drawable.border_button_green));
            score = score+plus; // ?????? ?????? ??????
            correctText.setVisibility(View.VISIBLE);
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    correctText.setVisibility(View.GONE);

                }
            }, 1000);


        }

        else {
            soundPool.play(wrongSound,soundPoolVolume,soundPoolVolume,0,0,1f);
            answerText.setBackground(getResources().getDrawable(R.drawable.border_button_red));
        }

        scoreText.setText(""+score); // ?????? ????????? ??????


    }


    // ????????? ???????????? ??????
    private void checkLast(){
        if (questionCounter < questionCountTotal) {
            nextButton.setText(getString(R.string.Next));
        }
        else{
            isFinished=true;
            nextButton.setText(getString(R.string.Finish));
        }
    }


    private void finishQuiz() {
        isHandler = false;


        Intent resultIntent = new Intent(this, MainActivity.class);

        resultIntent.putExtra(ALPHABETHIGH_SCORE, score);

        pointplus = 10;
        
        if (isBackPressed) {
            score = 0;
            pointplus = 0;
            basic_playtime--;
        }

        updateHintPoint();
        startActivity(resultIntent);

        Log.e("?????? ??????", ":" + score);
        //setResult(RESULT_OK, resultIntent);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    private void updateHintPoint() {

        SharedPreferences point = getSharedPreferences(SHARED_POINT,MODE_PRIVATE);

        hintPoint = hintPoint + pointplus;
        SharedPreferences.Editor pointEditor = point.edit();
        pointEditor.putInt(KEY_POINT,hintPoint);
        pointEditor.apply();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        if(mediaplayer_alphabet!=null) {

            if (mediaplayer_alphabet.isPlaying()) {
                // BGM ??????
                mediaplayer_alphabet.pause();

            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mediaplayer_alphabet!=null) {

            mediaplayer_alphabet.start();
        }

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            isBackPressed = true;
            if(mediaplayer_alphabet!=null)
            {
                mediaplayer_alphabet.stop();
                mediaplayer_alphabet.release();
                mediaplayer_alphabet = null;
            }
            finishQuiz();

        }else{
            Toast.makeText(this, getString(R.string.BackQuit), Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }


}







