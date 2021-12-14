package com.tenriver.kpop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;

public class TitleActivity extends AppCompatActivity {

    // MediaPlayer 객체생성
    public static MediaPlayer mediaplayer_title;

    int MY_REQUEST_CODE = 100320;

    // 애니메이션을 위해 텍스트뷰 객체들 생성
    private TextView TitleTextK; // 터치투스타트 K
    private TextView TitleTextP1; // 터치투스타트 P
    private TextView TitleTextO; // 터치투스타트 O
    private TextView TitleTextP2; // 터치투스타트 P
    private TextView TitleTextGuess; // 터치투스타트 노래맞추기

    private TextView TitleTextTouch; // 터치투스타트 touch to start
    private TextView TitleTextVersion; // 터치투스타트 version

    InternetDialog internetDialog;

    private BroadcastReceiver receiver;
    private IntentFilter intentFilter;


    public static final String SHARED_CLOSE = "sharedClose";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);



        // 앱 업데이트 매니저 초기화
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());

        // 업데이트를 체크하는데 사용되는 인텐트 리턴
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> { // appUpdateManager이 추가되는데 성공하면 발생하는 이벤트
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE // UpdateAvailability.UPDATE_AVAILABLE == 2 이면 앱 true
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) { // 허용된 타입의 앱 업데이트이면 실행 (AppUpdateType.IMMEDIATE || AppUpdateType.FLEXIBLE)
                // 업데이트가 가능하고, 상위 버전 코드의 앱이 존재하면 업데이트를 실행한다.
                try {
                    appUpdateManager.startUpdateFlowForResult(
                            // getAppUpdateInfo() 에 의해 리턴된 인텐트
                            appUpdateInfo,
                            // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                            AppUpdateType.IMMEDIATE,
                            // 현재 업데이트 요청을 만든 액티비티
                            this,
                            // onActivityResult에서 사용될 REQUEST_CODE
                            MY_REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });



        // BGN 실행
        mediaplayer_title = MediaPlayer.create(this, R.raw.titlemusic_new);
        mediaplayer_title.setLooping(true);
        mediaplayer_title.start();

        SharedPreferences music = getSharedPreferences(MainActivity.SHARED_MUSIC,MODE_PRIVATE);

        Boolean bgmCb_title = music.getBoolean("bgmCb",true);
        Boolean effectCb_title = music.getBoolean("effectCb",true);

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        // 인터넷이 연결되어있을 때
        if(!isConnected){

            internetDialog = new InternetDialog(this);
            internetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            Button internetOk = internetDialog.findViewById(R.id.btn_Internetconfirm);

            internetDialog.setCancelable(false); // 밖에 선택해도 창이 안꺼짐
            internetDialog.show();

            internetOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaplayer_title!=null)
                    {
                        mediaplayer_title.stop();
                        mediaplayer_title.release();
                        mediaplayer_title = null;
                    }
                    finish();
                }
            });
        }


        if(mediaplayer_title!=null){
            if(!bgmCb_title){
                mediaplayer_title.setVolume(0,0);
            }

            else{
                mediaplayer_title.setVolume(1,1);
            }
        }

        // 전원 버튼 감지
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                    if(mediaplayer_title!=null) {
                        if (mediaplayer_title.isPlaying()) {
                            // BGM 중지
                            mediaplayer_title.pause();

                        }
                    }
                }

            }
        };

        registerReceiver(receiver, intentFilter);

        //타이틀 텍스트뷰
        TitleTextK = (TextView) findViewById(R.id.titletextK);
        TitleTextP1 = (TextView) findViewById(R.id.titletextP1);
        TitleTextO = (TextView) findViewById(R.id.titletextO);
        TitleTextP2 = (TextView) findViewById(R.id.titletextP2);
        TitleTextGuess = (TextView) findViewById(R.id.titletextGuess);

        TitleTextTouch = (TextView) findViewById(R.id.touchtext);
        TitleTextVersion = (TextView) findViewById(R.id.versiontext);

    }

    public void checkFirstRun() {

        // Tips Activity 다시보지않기를 위한 변수
        SharedPreferences closef = getSharedPreferences(SHARED_CLOSE,MODE_PRIVATE);
        Boolean close_Tips = closef.getBoolean("closeforever",true);


        if (close_Tips) {
            if(mediaplayer_title!=null)
            {
                mediaplayer_title.stop();
                mediaplayer_title.release();
                mediaplayer_title = null;
            }

            Intent newIntent = new Intent(this, TipsActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
            startActivity(newIntent);

            // 액티비티 이동시 페이드인아웃 연출
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();

            //closef.putBoolean("isFirstRun", false).apply();
        }

        else
        {
            if(mediaplayer_title!=null)
            {
                mediaplayer_title.stop();
                mediaplayer_title.release();
                mediaplayer_title = null;
            }

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
            startActivity(intent);

            // 액티비티 이동시 페이드인아웃 연출
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mediaplayer_title!=null)
        {
            mediaplayer_title.stop();
            mediaplayer_title.release();
            mediaplayer_title = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!mediaplayer_title.isPlaying())
        {
            mediaplayer_title.start();
        }

        TitleTextK.setAlpha(1);
        Animation bounceK = AnimationUtils.loadAnimation(getApplication(), R.anim.splash_text);
        TitleTextK.startAnimation(bounceK);

        ImageView imageview_lp = findViewById(R.id.imageView_lp);
        Animation lpLotate = AnimationUtils.loadAnimation(getApplication(), R.anim.rotate_lp);
        imageview_lp.startAnimation(lpLotate);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                TitleTextP1.setAlpha(1);
                Animation bounceP1 = AnimationUtils.loadAnimation(getApplication(), R.anim.splash_text);
                TitleTextP1.startAnimation(bounceP1);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TitleTextO.setAlpha(1);
                        Animation bounceO = AnimationUtils.loadAnimation(getApplication(), R.anim.splash_text);
                        TitleTextO.startAnimation(bounceO);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TitleTextP2.setAlpha(1);
                                Animation bounceP = AnimationUtils.loadAnimation(getApplication(), R.anim.rotate_text);
                                TitleTextP2.startAnimation(bounceP);

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        TitleTextGuess.setAlpha(1);

                                        Animation bounceGuess = AnimationUtils.loadAnimation(getApplication(), R.anim.splash_text);
                                        TitleTextGuess.startAnimation(bounceGuess);

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                TitleTextTouch.setAlpha(1);
                                                TitleTextVersion.setAlpha(1);

                                                Animation showtext = AnimationUtils.loadAnimation(getApplication(), R.anim.repeat_text);
                                                TitleTextTouch.startAnimation(showtext);
                                                Animation showversion = AnimationUtils.loadAnimation(getApplication(), R.anim.smalltobig);
                                                TitleTextVersion.startAnimation(showversion);
                                            }
                                        }, 500); //딜레이 타임 조절
                                    }
                                }, 500); //딜레이 타임 조절
                            }
                        }, 500); //딜레이 타임 조절
                    }
                }, 500); //딜레이 타임 조절
            }
        }, 500); //딜레이 타임 조절
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //손가락으로 화면을 누르기 시작했을 때 할 일
                break;
            case MotionEvent.ACTION_MOVE:
                //터치 후 손가락을 움직일 때 할 일
                break;
            case MotionEvent.ACTION_UP:
                //손가락을 화면에서 뗄 때 할 일
                // 타이틀 음악 종료 후 액티비티 이동
                // BGM 종료

                checkFirstRun();

                break;
            case MotionEvent.ACTION_CANCEL:
                // 터치가 취소될 때 할 일
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        SharedPreferences music = getSharedPreferences(MainActivity.SHARED_MUSIC,MODE_PRIVATE);

        Boolean bgmCb_title = music.getBoolean("bgmCb",true);

        if(mediaplayer_title.isPlaying())
        {
            // BGM 중지
            if(!bgmCb_title){
                mediaplayer_title.setVolume(0,0);
            }

            else{
                mediaplayer_title.setVolume(1,1);
            }
            mediaplayer_title.pause();

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK)
            {
                Toast.makeText(getApplicationContext(),getString(R.string.VersionCancel),Toast.LENGTH_SHORT).show();
                AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());
                Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

                appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                            // flexible한 업데이트를 위해서는 AppUpdateType.FLEXIBLE을 사용한다.
                            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                        // 업데이트를 다시 요청한다.
                        try {
                            appUpdateManager.startUpdateFlowForResult(
                                    // getAppUpdateInfo() 에 의해 리턴된 인텐트
                                    appUpdateInfo,
                                    // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                                    AppUpdateType.IMMEDIATE,
                                    // 현재 업데이트 요청을 만든 액티비티
                                    this,
                                    // onActivityResult에서 사용될 REQUEST_CODE
                                    MY_REQUEST_CODE);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mediaplayer_title!=null)
        {
            mediaplayer_title.stop();
            mediaplayer_title.release();
            mediaplayer_title = null;
        }
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();

        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());

        appUpdateManager.
                getAppUpdateInfo().
                addOnSuccessListener(
                        appUpdateInfo -> {
                            if (appUpdateInfo.updateAvailability()
                            == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                                try {
                                    appUpdateManager.startUpdateFlowForResult(
                                            appUpdateInfo,
                                            AppUpdateType.IMMEDIATE,
                                            this,
                                            MY_REQUEST_CODE);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();

                                }
                            }
                        });
    }
}
