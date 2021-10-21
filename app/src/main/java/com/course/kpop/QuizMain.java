package com.course.kpop;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class QuizMain extends YouTubeBaseActivity {
    public static final String HIGH_SCORE = "highScore";
    private static final long COUNTDOWN_IN_MILLIS = 31000;

    YouTubePlayerView playerView;
    YouTubePlayer player;

    //static String API_KEY ="AIzaSyDImlmmX6mnicXNlzed8TH1cn5YN62hBN0"; // 구글 콘솔사이트에서 발급받는 키
    static String API_KEY ="AIzaSyCnt7CWC3z_t_OimQLUwJ5-yXf6C6F83-A";
    static int score = 0;
    static int videoLength;// 이지 노말 하드에 따라서 바뀜
    
    private String question;
    private String korean_Answer;
    private String english_Answer;
    private String real_Answer;

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

    private ImageView leftSpeaker;
    private ImageView rightSpeaker;

    boolean isStarted =true;


    private static int[] arr = {51000,56000,61000};

    private ProgressBar musicProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);

        if(MainActivity.mediaplayer_main!=null)
        {
            MainActivity.mediaplayer_main.stop();
            MainActivity.mediaplayer_main.release();
            MainActivity.mediaplayer_main = null;
        }


        Intent intent = getIntent();

        videoLength = intent.getIntExtra("difficulty_time",10000);


        confirmButton = findViewById(R.id.confirmButton);
        nextButton = findViewById(R.id.nextButton);
        scoreText = findViewById(R.id.scoreText);

        playerView = findViewById(R.id.youtubeView);


        answerText = findViewById(R.id.editText);


        answerText.getBackground().setColorFilter(null);
        //textColorDefaultCd = txtCountDown.getTextColors();




        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        txtCountDown = findViewById(R.id.txtCountDown);

        // DB 관련 선언
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getQuestions("2010");

        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        // 스피커 애니메이션
        leftSpeaker = findViewById(R.id.imageView_speakerleft);
        rightSpeaker = findViewById(R.id.imageView_speakerright);

        Animation speakerleft_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_leftanim);
        leftSpeaker.startAnimation(speakerleft_anim);
        Animation speakerright_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.speaker_rightanim);
        rightSpeaker.startAnimation(speakerright_anim);

        nextButton.setEnabled(false);
        nextButton.setTextColor(Color.GRAY);

        isHandler = true;

        ImageView remote_quiz = (ImageView) findViewById(R.id.Remote_quizmain);
        ConstraintLayout remotebutton_quiz = (ConstraintLayout) findViewById(R.id.Remote_button_quizmain);

        Animation RemoteUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remote_quiz.startAnimation(RemoteUp);

        Animation RemoteButtonUp = AnimationUtils.loadAnimation(getApplication(), R.anim.remotemove_up);
        remotebutton_quiz.startAnimation(RemoteButtonUp);

        // 음악 로딩 다이얼로그 불러오기
        customProgressDialog = new ProgressDialog(this);
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        Random random = new Random();
        int randomInt = random.nextInt(max_num_value - min_num_value);

        randomTotal = arr[randomInt] + videoLength;
        randomStart = arr[randomInt];

        musicProgressbar = findViewById(R.id.musicProgressBar);

        musicProgressbar.setMax(videoLength);


        initPlayer();
        showNextQuestion();





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
                if(player.isPlaying()){
                    player.pause();
                }


                playerView.setAlpha(0.0f);
                confirmButton.setEnabled(true);
                confirmButton.setTextColor(Color.WHITE);
                nextButton.setEnabled(false);
                nextButton.setTextColor(Color.GRAY);
                answerText.setText("");

                answerText.getBackground().setColorFilter(null);

                if(questionCounter >= questionCountTotal)
                {
                    finishQuiz();
                }
                showNextQuestion();
                leftSpeaker.startAnimation(speakerleft_anim);
                rightSpeaker.startAnimation(speakerright_anim);

                playVideo();
            }

        });
    } // onCreate

    public void initPlayer() {

        // YouTubePlayerView 초기화하기
        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;
                player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                        // 로딩창 구현
                        customProgressDialog.show();
                    }

                    @Override
                    public void onLoaded(String s) {
                        Log.e("PlayerView", "onLoaded 호출됨: " + s);
                        customProgressDialog.dismiss();

                    }

                    @Override
                    public void onAdStarted() {}

                    @Override
                    public void onVideoStarted() {}

                    @Override
                    public void onVideoEnded() {}

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {}
                });
                playVideo();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // 못 불러왔을 때
            }
        });
    }

    public void playVideo() {
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();

//                player.cueVideo(videoId); // 여기에 있으면 동영상 재생이 안됨.
            }


            player.loadVideo(question, randomStart);


            Log.e("Max:"," "+(musicProgressbar.getMax()));
            ObjectAnimator progressAnimation = ObjectAnimator.ofInt(musicProgressbar, "progress", 0,musicProgressbar.getMax() );
            progressAnimation.setDuration(musicProgressbar.getMax());
            progressAnimation.setInterpolator(new DecelerateInterpolator());


            // 지정 시간동안 동영상 재생하기
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
                                    progressAnimation.start();
                                    isStarted=false;
                                }
                            }
                            handler.postDelayed(this, 1000);

                        } else {
                            handler.removeCallbacks(this);
                            player.pause();
                            musicProgressbar.setProgress(musicProgressbar.getMax());
                            rightSpeaker.clearAnimation();
                            leftSpeaker.clearAnimation();
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
            question = currentQuestion.getQuestion(); // 유튜브 url
            korean_Answer = currentQuestion.getKoreanAnswer(); // 한글 정답
            english_Answer = currentQuestion.getEnglishAnswer2(); // 영어 정답
            real_Answer = currentQuestion.getRealAnswer(); // 풀 정답

            questionCounter ++;
            txtQuestionCount.setText(questionCounter + " / " + questionCountTotal); // 문제 수 확인
            confirmButton.setText(getString(R.string.Confirm));
            answerText.getBackground().setColorFilter(null);


            //카운트 다운 시작
            isCountStart = true;

            musicProgressbar.setProgress(0);
            isStarted = true;
            
            checkLast();

        }

    }

    private void startCountDown() {
        if(isCountStart) {
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
            txtCountDown.setTextColor(Color.RED);
        }
        else{
            txtCountDown.setTextColor(Color.BLACK);
        }
    }

    // 영어인지 한글인지 확인 
    // 영어면 소문자로 변환하고 검사
    // 한글이면 바로 검사
    private void checkAnswer() {
        if(player.isPlaying()){
            player.pause();
        }
        countDownTimer.cancel(); // 타이머 종료

        playerView.setAlpha(1.0f);
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
            answerText.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            score++; // 점수 책정 방식
        }

        else {
            answerText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        
        scoreText.setText(""+score); // 점수 텍스트 변경
        //confirmButton.setVisibility(View.GONE);

        playVideo(); // 비디오 재생
        //nextButton.setVisibility(View.VISIBLE);
        answerText.setText(real_Answer);

    }


    // 마지막 문제인지 체크
    private void checkLast(){
        if (questionCounter < questionCountTotal) {
            nextButton.setText(getString(R.string.Next));
        }
        else{
            nextButton.setText(getString(R.string.Finish));
        }
    }


    private void finishQuiz() {
        isHandler = false;
        Intent resultIntent = new Intent();
        resultIntent.putExtra(HIGH_SCORE, score);
        Log.e("최고 점수",":" + score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }else{
            Toast.makeText(this, "한번 더 뒤로버튼을 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
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







