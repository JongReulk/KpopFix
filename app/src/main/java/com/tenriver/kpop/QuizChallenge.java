package com.tenriver.kpop;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class QuizChallenge extends YouTubeBaseActivity {
    public static final String HIGH_SCORE = "highScore";
    public static final String CHALLENGE_HIGH_SCORE = "challengehighScore";
    private static final long COUNTDOWN_IN_MILLIS = 30500;
    private static final String INTERSTITIAL_AD_ID = "ca-app-pub-6633751318337334/2526988236";
    private static final String INTERSTITIAL_TESTAD_ID = "ca-app-pub-3940256099942544/1033173712";

    private static final String QUIZ_SHARED = "quizshared";
    private static final String CHALLENGE_PLAY_TIME = "challengeplaytime";

    YouTubePlayerView playerView;
    YouTubePlayer player;

    //static String API_KEY ="AIzaSyDImlmmX6mnicXNlzed8TH1cn5YN62hBN0"; // ?????? ????????????????????? ???????????? ???
    static String API_KEY ="AIzaSyCnt7CWC3z_t_OimQLUwJ5-yXf6C6F83-A";
    static int score_challenge = 0;
    static int plus = 0;
    static int videoLength;// ?????? ?????? ????????? ????????? ??????

    private String question;
    private String korean_Answer;
    private String english_Answer;
    private String real_Answer;
    private String hint_all;

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
    private TextView correctText;
    private EditText answerText;
    private TextView hintText;

    private Button confirmButton;
    private Button nextButton;
    private boolean isHandler = true;
    Handler handler;

    private long backPressedTime;

    ProgressDialog customProgressDialog;

    private int max_num_value = 3;
    private int min_num_value = 0;

    private static int randomTotal;
    private static int randomStart;

    private boolean isCountStart;
    private boolean isFirst = true;

    private ImageView allSpeaker;


    boolean isStarted =true;

    boolean isEasy=false;

    private static int[] arr = {51000,61000,71000};


    private ProgressBar musicProgressbar;

    private TextView txt_answer;


    boolean isChallengefinish = false;
    boolean isBackPressed = false;
    boolean isFinished = false;
    boolean isError = false;

    private int question_Num;


    // hint button
    private Button HintButton1; // ?????? ??????
    private Button HintButton2; // ?????? ??????
    private Button HintButton3; // ?????? ??????

    // point
    private static int hintPoint;
    private TextView txtHintPoint;

    // hint count : ?????????????????? ?????????????????? 5???
    private int hintCount = 5;
    private int hintCountTotal = 5;
    private TextView txthintCount;

    // ????????? ??????
    private int challenge_playtime = 0;

    private ImageView endimage;

    private int randomAd;

    private boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_challenge);

        Log.d("start","quiz challenge activity start!");

        question_Num = 0;


        if(MainActivity.mediaplayer_main!=null)
        {
            MainActivity.mediaplayer_main.stop();
            MainActivity.mediaplayer_main.release();
            MainActivity.mediaplayer_main = null;
        }

        score_challenge = 0;


        Intent intent = getIntent();

        videoLength = intent.getIntExtra("difficulty_time",10000);


        int year_num = intent.getIntExtra("year_num",2020);

        confirmButton = findViewById(R.id.confirmButton);
        nextButton = findViewById(R.id.nextButton);
        scoreText = findViewById(R.id.scoreText);
        correctText = findViewById(R.id.correctText);
        correctText.setVisibility(View.GONE);

        playerView = findViewById(R.id.youtubeView);


        answerText = findViewById(R.id.editText);
        txt_answer = findViewById(R.id.txt_answer);

        txt_answer.setVisibility(View.GONE);

        answerText.getBackground().setColorFilter(null);
        //textColorDefaultCd = txtCountDown.getTextColors();

        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        txtCountDown = findViewById(R.id.txtCountDown);

        // Hint Button
        HintButton1 = findViewById(R.id.Quiz_hint1);  // ?????? ??????
        HintButton2 = findViewById(R.id.Quiz_hint2);  // ?????? ??????
        HintButton3 = findViewById(R.id.Quiz_hint3);  // ?????? ??????

        hintText = findViewById(R.id.txt_hintall);
        txtHintPoint = findViewById(R.id.txt_HintPoint);
        txthintCount = findViewById(R.id.txt_HintCount);

        endimage = findViewById(R.id.EndImage);

        // DB ?????? ??????
        QuizDbHelper dbHelper = new QuizDbHelper(this);

        // challenge ?????? ?????? ??????
        plus = 10;
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = 100;


        Collections.shuffle(questionList);

        // ????????? ???????????????
        allSpeaker = findViewById(R.id.imageView_speakerall);

        Animation allSpeaker_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_allspeaker);
        allSpeaker.startAnimation(allSpeaker_anim);



        // ?????? ?????? ????????????
        nextButton.setEnabled(false);
        nextButton.setTextColor(Color.GRAY);

        isHandler = true;

        ImageView remote_quiz = (ImageView) findViewById(R.id.Remote_quizmain);
        ConstraintLayout remotebutton_quiz = (ConstraintLayout) findViewById(R.id.Remote_button_quizmain);

        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote_quiz.startAnimation(RemoteUp);

        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton_quiz.startAnimation(RemoteButtonUp);

        // ?????? ?????? ??????????????? ????????????
        customProgressDialog = new ProgressDialog(this);
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        Random random = new Random();
        int randomInt = random.nextInt(max_num_value - min_num_value);

        randomTotal = arr[randomInt] + videoLength;
        randomStart = arr[randomInt];

        musicProgressbar = findViewById(R.id.musicProgressBar);

        musicProgressbar.setMax(videoLength);

        confirmButton.setEnabled(false);
        confirmButton.setTextColor(Color.GRAY);

        // ?????? ?????? ?????????
        HintButton1.setEnabled(false);
        HintButton2.setEnabled(false);
        HintButton3.setEnabled(false);

        hintText.setVisibility(View.GONE);

        // ????????? ????????????
        SharedPreferences point = getSharedPreferences(SHARED_POINT,MODE_PRIVATE);

        hintPoint = point.getInt(KEY_POINT,100);

        txtHintPoint.setText(""+hintPoint);

        txthintCount.setText(hintCount + "/" + hintCountTotal);

        SharedPreferences quiz_shared = getSharedPreferences(QUIZ_SHARED,MODE_PRIVATE);

        challenge_playtime = quiz_shared.getInt(CHALLENGE_PLAY_TIME,0);


        InternetDialog internetDialog;
        // ????????? ?????? ??????
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        // ???????????? ?????????????????? ???
        if(!isConnected){

            internetDialog = new InternetDialog(this);
            internetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            Button internetOk = internetDialog.findViewById(R.id.btn_Internetconfirm);

            internetDialog.setCancelable(false); // ?????? ???????????? ?????? ?????????
            internetDialog.show();

            internetOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                }
            });
        }

        try {
            GamesClient gamesClient = Games.getGamesClient(this,GoogleSignIn.getLastSignedInAccount(this));
            gamesClient.setViewForPopups(findViewById(R.id.content));
            gamesClient.setGravityForPopups(Gravity.TOP | Gravity.CENTER_HORIZONTAL);

        } catch(Exception e){
            Log.d("??????", "?????? ???????????? ??????");
        }



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        Random Adrandom = new Random();
        randomAd = Adrandom.nextInt(3);

        if(randomAd == 0){
            LoadAD();
        }
        else{
            isFirst = false;
            initPlayer();
            showNextQuestion();
        }



        // ?????? ??????
        HintButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hintCount <= 0){
                    Toast.makeText(getApplicationContext(), getString(R.string.lessCount), Toast.LENGTH_SHORT).show();
                    Log.v("??????????????????","?????????????????? ?????? ??????");
                }

                else if (hintPoint < 10){
                    Toast.makeText(getApplicationContext(), getString(R.string.lessPoint), Toast.LENGTH_SHORT).show();
                    Log.v("???????????????","???????????? ?????????");
                }
                else {
                    hintPoint = hintPoint - 10;

                    txtHintPoint.setText(""+hintPoint);
                    Toast.makeText(getApplicationContext(), getString(R.string.currentPoint) + hintPoint, Toast.LENGTH_SHORT).show();
                    Log.v("?????? ??????", "???????????? ?????? ??????");
                    hintCount--; // ????????????
                    txthintCount.setText(hintCount + "/" + hintCountTotal);
                    Log.v("?????? ????????????", "?????? ?????? ?????? : "+ hintCount);

                    musicProgressbar.setAlpha(0);

                    isStarted = true;

                    if (player.isPlaying()) {
                        player.pause();
                    }
                    HintButton1.setEnabled(false);

                    allSpeaker.startAnimation(allSpeaker_anim);

                    playVideo();
                }

            }
        });

        // ?????? ??????
        HintButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hintCount <= 0){
                    Toast.makeText(getApplicationContext(), getString(R.string.lessCount), Toast.LENGTH_SHORT).show();
                    Log.v("??????????????????","?????????????????? ?????? ??????");
                }

                else if (hintPoint < 20){
                    Toast.makeText(getApplicationContext(), getString(R.string.lessPoint), Toast.LENGTH_SHORT).show();
                    Log.v("???????????????","???????????? ?????????");
                }
                else {
                    hintPoint = hintPoint - 20;

                    txtHintPoint.setText(""+hintPoint);
                    Toast.makeText(getApplicationContext(), getString(R.string.currentPoint) + hintPoint, Toast.LENGTH_SHORT).show();
                    Log.v("?????? ??????", "???????????? ?????? ??????");
                    hintCount--; // ????????????
                    txthintCount.setText(hintCount + "/" + hintCountTotal);
                    Log.v("?????? ????????????", "?????? ?????? ?????? : "+ hintCount);

                    playerView.setAlpha(1.0f);

                    HintButton2.setEnabled(false);
                    HintButton3.setEnabled(false);
                }

            }
        });

        // ?????? ??????
        HintButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hintCount <= 0){
                    Toast.makeText(getApplicationContext(), getString(R.string.lessCount), Toast.LENGTH_SHORT).show();
                    Log.v("??????????????????","?????????????????? ?????? ??????");
                }

                else if (hintPoint < 30){
                    Toast.makeText(getApplicationContext(), getString(R.string.lessPoint), Toast.LENGTH_SHORT).show();
                    Log.v("???????????????","???????????? ?????????");
                }
                else {
                    hintPoint = hintPoint - 30;

                    txtHintPoint.setText(""+hintPoint);

                    Toast.makeText(getApplicationContext(), getString(R.string.currentPoint) + hintPoint, Toast.LENGTH_SHORT).show();
                    Log.v("?????? ??????", "???????????? ?????? ??????");
                    hintCount--; // ????????????
                    txthintCount.setText(hintCount + "/" + hintCountTotal);
                    Log.v("?????? ????????????", "?????? ?????? ?????? : "+ hintCount);

                    hintText.setVisibility(View.VISIBLE);
                    hintText.setText(hint_all);
                    hintText.setSingleLine(true);
                    hintText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    hintText.setSelected(true);

                    HintButton2.setEnabled(false);
                    HintButton3.setEnabled(false);
                }

            }
        });





        // ?????? ?????? ?????? checkAnswer ?????? ????????????
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                musicProgressbar.setAlpha(0.0f);
                checkAnswer();
                allSpeaker.startAnimation(allSpeaker_anim);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    player.pause();
                }
                timeLeftInMillis = COUNTDOWN_IN_MILLIS;


                hintText.setVisibility(View.GONE);



                playerView.setAlpha(0.0f);

                txt_answer.setBackground(null);

                nextButton.setEnabled(false);
                nextButton.setTextColor(Color.GRAY);
                answerText.setText("");
                answerText.setVisibility(View.VISIBLE);
                txt_answer.setVisibility(View.GONE);

                if(isChallengefinish){
                    //AdRequest adRequest = new AdRequest.Builder().build();
                    //finishQuiz();
                    isFinished=true;
                    endimage.setVisibility(View.VISIBLE);
                    Animation end_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.fade_in);
                    endimage.startAnimation(end_anim);
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showInterstitial();
                        }
                    },2000);
                }



                else {
                    if (questionCounter >= questionCountTotal) {
                        isFinished=true;
                        endimage.setVisibility(View.VISIBLE);
                        Animation end_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.fade_in);
                        endimage.startAnimation(end_anim);
                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                showInterstitial();
                            }
                        },2000);
                    } else {
                        showNextQuestion();
                        allSpeaker.startAnimation(allSpeaker_anim);

                        playVideo();

                    }
                }
            }

        });
    } // onCreate

    private void LoadAD() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,INTERSTITIAL_TESTAD_ID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        QuizChallenge.this.screenAd = interstitialAd;

                        if(isFirst){
                            showInterstitial();
                            isFirst = false;
                        }


                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        QuizChallenge.this.screenAd = null;
                        Log.d("??????", loadAdError+" ????????????");
                        Toast.makeText(getApplicationContext(), getString(R.string.fatalerror), Toast.LENGTH_SHORT).show();
                        initPlayer();
                        showNextQuestion();
                    }

                });
    }

    public void initPlayer() {

        // YouTubePlayerView ???????????????
        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;
                player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                        // ????????? ??????
                        customProgressDialog.show();
                    }

                    @Override
                    public void onLoaded(String s) {
                        Log.e("PlayerView", "onLoaded ?????????: " + s);

                    }

                    @Override
                    public void onAdStarted() {}

                    @Override
                    public void onVideoStarted() {
                        customProgressDialog.dismiss();
                    }

                    @Override
                    public void onVideoEnded() {}

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {
                        Log.d("??????", "init player ????????????"+errorReason);
                        Toast.makeText(getApplicationContext(), getString(R.string.waitasecond), Toast.LENGTH_SHORT).show();

                        isError = true;

                        showNextQuestion();
                        playVideo();

                    }
                });

                playVideo();


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // ??? ???????????? ???
                Log.e("Fail!!","");
            }
        });
    }

    public void playVideo() {
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();

//                player.cueVideo(videoId); // ????????? ????????? ????????? ????????? ??????.
            }

            // ????????? ?????? ??????
            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            // ???????????? ?????????????????? ???
            if(!isConnected){
                Toast.makeText(this,getString(R.string.videointernet),Toast.LENGTH_SHORT).show();
            }

            player.loadVideo(question, randomStart);






            ObjectAnimator progressAnimation = ObjectAnimator.ofInt(musicProgressbar, "progress", 0,musicProgressbar.getMax() );
            progressAnimation.setDuration(musicProgressbar.getMax());
            progressAnimation.setInterpolator(new LinearInterpolator());




            // ?????? ???????????? ????????? ????????????
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isHandler)
                    {
                        if (player.getCurrentTimeMillis() <= randomTotal ) {
                            if(player.isPlaying()){
                                //musicProgressbar.incrementProgressBy(1000);
                                startCountDown();
                                if(isStarted){
                                    Log.e("START", " ANI ");
                                    musicProgressbar.setAlpha(1);
                                    progressAnimation.start();
                                    isStarted=false;
                                }
                            }
                            handler.postDelayed(this, 1000);

                        } else {
                            handler.removeCallbacks(this);
                            player.pause();
                            musicProgressbar.setProgress(musicProgressbar.getMax());
                            musicProgressbar.clearAnimation();
                            allSpeaker.clearAnimation();
                        }
                    }

                    else{
                        handler.removeCallbacks(this);
                    }

                }
            }, 1000);

        }


    }


    private void showNextQuestion() {
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            question = currentQuestion.getQuestion(); // ????????? url
            korean_Answer = currentQuestion.getKoreanAnswer(); // ?????? ??????
            english_Answer = currentQuestion.getEnglishAnswer2(); // ?????? ??????
            real_Answer = currentQuestion.getRealAnswer(); // ??? ??????
            hint_all = currentQuestion.getHintAll(); // ?????? ????????????

            questionCounter ++;
            if(!isError){
                question_Num++;
            }
            else{
                isError = false;
            }
            txtQuestionCount.setText(question_Num + " / " + questionCountTotal); // ?????? ??? ??????
            confirmButton.setText(getString(R.string.Confirm));


            //????????? ?????? ??????
            isCountStart = true;

            musicProgressbar.setProgress(0);
            isStarted = true;

            checkLast();

        }

    }

    private void startCountDown() {

        if(isCountStart) {
            // ?????? ?????? ?????????
            HintButton1.setEnabled(true);
            HintButton2.setEnabled(true);
            HintButton3.setEnabled(true);

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // confirm ?????? ?????? ??? ??????
                    confirmButton.setEnabled(true);
                    confirmButton.setTextColor(Color.WHITE);
                }
            },1000);

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
        if(player.isPlaying()){
            player.pause();
        }
        countDownTimer.cancel(); // ????????? ??????

        // ?????? ?????? ????????????
        HintButton1.setEnabled(false);
        HintButton2.setEnabled(false);
        HintButton3.setEnabled(false);


        playerView.setAlpha(1.0f);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // next ?????? ?????? ??? ??????
                nextButton.setEnabled(true);
                nextButton.setTextColor(Color.WHITE);
            }
        },1000);

        confirmButton.setEnabled(false);
        confirmButton.setTextColor(Color.GRAY);

        //?????? ?????? ??? ???????????? ?????? ?????????
        String answer = answerText.getText().toString().trim().replace(" ","");
        //answer.replaceAll(" ","");
        answer = answer.toLowerCase();
        Log.e("??? ???"," : "+answer);
        Log.e("???"," : "+korean_Answer);
        Log.e("???"," : "+english_Answer);

        // ?????? ?????? ????????? equals ??????
        if(answer.equals(korean_Answer) || answer.equals(english_Answer)){
            correctText.setVisibility(View.VISIBLE);
            txt_answer.setBackground(getResources().getDrawable(R.drawable.border_button_green));
            score_challenge = score_challenge+plus; // ?????? ?????? ??????
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    correctText.setVisibility(View.GONE);

                }
            }, 1000);


        }

        else {
            nextButton.setText(getString(R.string.Finish));
            isChallengefinish = true;
            if(randomAd == 0){

            }
            else{
                LoadAD();
            }

            txt_answer.setBackground(getResources().getDrawable(R.drawable.border_button_red));
        }

        scoreText.setText(""+score_challenge); // ?????? ????????? ??????
        //confirmButton.setVisibility(View.GONE);

        playVideo(); // ????????? ??????
        //nextButton.setVisibility(View.VISIBLE);
        answerText.setVisibility(View.GONE);
        txt_answer.setVisibility(View.VISIBLE);
        txt_answer.setText(real_Answer);
        txt_answer.setSingleLine(true);
        txt_answer.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        txt_answer.setSelected(true);

    }


    // ????????? ???????????? ??????
    private void checkLast(){
        if (questionCounter < questionCountTotal) {
            nextButton.setText(getString(R.string.Next));
        }
        else{
            //LoadAD();
            isFinished=true;
            nextButton.setText(getString(R.string.Finish));
        }
    }


    private void finishQuiz() {
        isHandler = false;

        challenge_playtime++;

        try {
            if (score_challenge > 1000) {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .increment(getString(R.string.achievement_road_to_challenge_master), 4);
            }
            else if (score_challenge > 500) {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .increment(getString(R.string.achievement_road_to_challenge_master), 3);
            }
            else if (score_challenge > 300) {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .increment(getString(R.string.achievement_road_to_challenge_master), 2);
            }
            else if (score_challenge > 100) {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .increment(getString(R.string.achievement_road_to_challenge_master), 1);
            }
        } catch(Exception e){
            Log.d("??????", "????????? ?????? ???????????? ??????");
        }

        try {
            if (challenge_playtime > 100) {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .increment(getString(R.string.achievement_a_challenger_is_beautiful), 4);
            }
            else if (challenge_playtime > 50) {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .increment(getString(R.string.achievement_a_challenger_is_beautiful), 3);
            }
            else if (challenge_playtime > 30) {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .increment(getString(R.string.achievement_a_challenger_is_beautiful), 2);
            }
            else if (challenge_playtime > 10) {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .increment(getString(R.string.achievement_a_challenger_is_beautiful), 1);
            }
            else {
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .unlock(getString(R.string.achievement_can_you_challenge));
            }
        } catch(Exception e){
            Log.d("??????", "????????? ?????? ???????????? ??????");
        }
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra(CHALLENGE_HIGH_SCORE, score_challenge);

        if (isBackPressed) {
            score_challenge = 0;
            challenge_playtime--;
        }

        updateHintPoint();
        updateChallengePlayTime();
        startActivity(resultIntent);

        Log.e("?????? ??????", ":" + score_challenge);
        //setResult(RESULT_OK, resultIntent);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    private void updateChallengePlayTime() {
        SharedPreferences quiz_shared = getSharedPreferences(QUIZ_SHARED,MODE_PRIVATE);

        SharedPreferences.Editor quizeditor = quiz_shared.edit();
        quizeditor.putInt(CHALLENGE_PLAY_TIME,challenge_playtime);
        quizeditor.apply();
    }

    private void updateHintPoint() {

        SharedPreferences point = getSharedPreferences(SHARED_POINT,MODE_PRIVATE);

        SharedPreferences.Editor pointEditor = point.edit();
        pointEditor.putInt(KEY_POINT,hintPoint);
        pointEditor.apply();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            isBackPressed = true;
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



    private void showInterstitial() {

        if (screenAd != null) {
            screenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    QuizChallenge.this.screenAd = null;
                    if (isFinished){
                        finishQuiz();
                    }

                    else{
                        initPlayer();
                        showNextQuestion();
                    }
                    LoadAD();
                }
            });
            screenAd.show(QuizChallenge.this);
            screenAd = null;
        } else {
            Toast.makeText(this, getString(R.string.waitasecond), Toast.LENGTH_SHORT).show();
            Log.e("TAG","NO SHOW!");
            if(!isLoaded) {
                isLoaded = true;
                LoadAD();
                showInterstitial();
            }
            else {
                if (isFinished){
                    finishQuiz();
                }

                else{
                    initPlayer();
                    showNextQuestion();
                }
                LoadAD();
            }
        }

    }

}







