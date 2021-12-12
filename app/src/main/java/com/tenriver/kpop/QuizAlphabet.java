package com.tenriver.kpop;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
    
    private static final String INTERSTITIAL_AD_ID = "ca-app-pub-3940256099942544/1033173712";
    static int score = 0;
    static int plus = 0;
    static int pointplus = 0;
    static int videoLength;// 이지 노말 하드에 따라서 바뀜
    
    private String korean_Answer;
    private String english_Answer;
    private String alphabet_Question;
    private String singer_hint;
    
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
    private Button HintButton1; // 가수 공개

    // point
    private static int hintPoint;
    private TextView txtHintPoint;

    // 플레이 횟수
    private int basic_playtime = 0;

    private ImageView endimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);

        Log.d("start","quiz alphabet activity start!");

        question_Num = 0;


        if(MainActivity.mediaplayer_main!=null)
        {
            MainActivity.mediaplayer_main.stop();
            MainActivity.mediaplayer_main.release();
            MainActivity.mediaplayer_main = null;
        }

        score = 0;


        Intent intent = getIntent();

        try {
            GamesClient gamesClient = Games.getGamesClient(this,GoogleSignIn.getLastSignedInAccount(this));
            gamesClient.setViewForPopups(findViewById(R.id.googlePopupQuizMain));
            //gamesClient.setGravityForPopups(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            Toast.makeText(getApplicationContext(), "알림 불러오기 성공!", Toast.LENGTH_SHORT).show();
        } catch(Exception e){
            Log.d("로그", "알림 불러오기 실패");
        }




        int year_num = intent.getIntExtra("year_num",2020);


        confirmButton = findViewById(R.id.confirmButton);
        nextButton = findViewById(R.id.nextButton);
        scoreText = findViewById(R.id.scoreText);


        answerText = findViewById(R.id.editText);

        answerText.getBackground().setColorFilter(null);
        //textColorDefaultCd = txtCountDown.getTextColors();

        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        txtCountDown = findViewById(R.id.txtCountDown);

        // Hint Button
        HintButton1 = findViewById(R.id.Quiz_hint1);  // 다시 듣기


        txtHintPoint = findViewById(R.id.txt_HintPoint);

        // end 이미지
        endimage = findViewById(R.id.EndImage);
        txtAlpha = findViewById(R.id.txt_alphabet);
        txtSinger = findViewById(R.id.txt_singer);

        txtSinger.setVisibility(View.INVISIBLE);

        // DB 관련 선언
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

        // 스피커 애니메이션
        leftSpeaker = findViewById(R.id.imageView_speakerleft);
        rightSpeaker = findViewById(R.id.imageView_speakerright);

        Animation speakerleft_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_leftanim);

        leftSpeaker.startAnimation(speakerleft_anim);
        Animation speakerright_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_rightanim);
        rightSpeaker.startAnimation(speakerright_anim);

        // 다음 버튼 비활성화
        nextButton.setEnabled(false);
        nextButton.setTextColor(Color.GRAY);

        isHandler = true;

        ImageView remote_quiz = (ImageView) findViewById(R.id.Remote_quizmain);
        ConstraintLayout remotebutton_quiz = (ConstraintLayout) findViewById(R.id.Remote_button_quizmain);

        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote_quiz.startAnimation(RemoteUp);

        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton_quiz.startAnimation(RemoteButtonUp);


        confirmButton.setEnabled(false);
        confirmButton.setTextColor(Color.GRAY);

        // 힌트 버튼 활성화
        HintButton1.setEnabled(false);


        // 포인트 가져오기
        SharedPreferences point = getSharedPreferences(SHARED_POINT,MODE_PRIVATE);

        hintPoint = point.getInt(KEY_POINT,100);

        txtHintPoint.setText(""+hintPoint);

        SharedPreferences quiz_shared = getSharedPreferences(QUIZ_SHARED,MODE_PRIVATE);

        txtAlpha.setSingleLine(true);
        txtAlpha.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        txtAlpha.setSelected(true);

        txtSinger.setSingleLine(true);
        txtSinger.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        txtSinger.setSelected(true);



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        LoadAD();



        // 다시 듣기
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
                    Log.v("다시 듣기", "다시듣기 버튼 클릭");


                    isStarted = true;

                    txtSinger.setVisibility(View.VISIBLE);

                    HintButton1.setEnabled(false);

                    leftSpeaker.startAnimation(speakerleft_anim);
                    rightSpeaker.startAnimation(speakerright_anim);

                }

            }
        });






        // 정답 확인 버튼 checkAnswer 함수 불러오기
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();
                leftSpeaker.startAnimation(speakerleft_anim);
                rightSpeaker.startAnimation(speakerright_anim);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeLeftInMillis = COUNTDOWN_IN_MILLIS;


                nextButton.setEnabled(false);
                nextButton.setTextColor(Color.GRAY);
                answerText.setText("");
                answerText.setVisibility(View.VISIBLE);

                txtSinger.setVisibility(View.INVISIBLE);




                if(questionCounter >= questionCountTotal)
                {
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

                    showNextQuestion();
                    leftSpeaker.startAnimation(speakerleft_anim);
                    rightSpeaker.startAnimation(speakerright_anim);

                }
            }

        });
    } // onCreate

    private void LoadAD() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,INTERSTITIAL_AD_ID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        QuizAlphabet.this.screenAd = interstitialAd;

                        if(isFirst){
                            showInterstitial();
                            isFirst = false;
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        QuizAlphabet.this.screenAd = null;
                        Toast.makeText(getApplicationContext(), getString(R.string.fatalerror), Toast.LENGTH_SHORT).show();
                    }

                });
    }



    private void showNextQuestion() {
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            korean_Answer = currentQuestion.getKoreanAnswer(); // 한글 정답
            english_Answer = currentQuestion.getEnglishAnswer2(); // 영어 정답
            alphabet_Question = currentQuestion.getHintAll();
            singer_hint = currentQuestion.getSinger();

            questionCounter ++;
            if(!isError){
                question_Num++;
            }
            else{
                isError = false;
            }
            txtQuestionCount.setText(question_Num + " / " + questionCountTotal); // 문제 수 확인
            confirmButton.setText(getString(R.string.Confirm));

            txtAlpha.setText(alphabet_Question);
            txtSinger.setText(singer_hint);


            //카운트 다운 시작
            isCountStart = true;


            isStarted = true;

            checkLast();

        }

    }

    private void startCountDown() {

        if(isCountStart) {
            // 힌트 버튼 활성화
            HintButton1.setEnabled(true);

            confirmButton.setEnabled(true);
            confirmButton.setTextColor(Color.WHITE);
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

    // 영어인지 한글인지 확인
    // 영어면 소문자로 변환하고 검사
    // 한글이면 바로 검사
    private void checkAnswer() {
        countDownTimer.cancel(); // 타이머 종료

        // 힌트 버튼 비활성화
        HintButton1.setEnabled(false);

        nextButton.setEnabled(true);
        nextButton.setTextColor(Color.WHITE);
        confirmButton.setEnabled(false);
        confirmButton.setTextColor(Color.GRAY);

        //좌우 공백 및 띄어쓰기 공백 없애기
        String answer = answerText.getText().toString().trim().replace(" ","");
        //answer.replaceAll(" ","");
        answer = answer.toLowerCase();
        Log.e("쓴 답"," : "+answer);
        Log.e("답"," : "+korean_Answer);
        Log.e("답"," : "+english_Answer);

        // 내용 비교 위해서 equals 사용
        if(answer.equals(korean_Answer) || answer.equals(english_Answer)){
            answerText.setBackground(getResources().getDrawable(R.drawable.border_button_green));
            score = score+plus; // 점수 책정 방식



        }

        else {
            answerText.setBackground(getResources().getDrawable(R.drawable.border_button_red));
        }

        scoreText.setText(""+score); // 점수 텍스트 변경

        answerText.setVisibility(View.INVISIBLE);

    }


    // 마지막 문제인지 체크
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

        Log.e("최고 점수", ":" + score);
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
        screenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
                QuizAlphabet.this.screenAd = null;
                if (isFinished){
                    finishQuiz();
                }

                else{
                    showNextQuestion();
                }
                LoadAD();
            }
        });

        if (screenAd != null) {
            screenAd.show(QuizAlphabet.this);
            screenAd = null;
        } else {
            Toast.makeText(this, getString(R.string.fatalerror), Toast.LENGTH_SHORT).show();
            Log.e("TAG","NO SHOW!");
        }

    }

}







