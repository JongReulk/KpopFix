package com.course.kpop;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.service.wallpaper.WallpaperService;
import android.widget.Button;

public class TitleActivity extends AppCompatActivity {

    // MediaPlayer 객체생성
    public static MediaPlayer mediaplayer_title;
    private TextView TitleText; // 터치투스타트
    private ImageView TitleImage; // 타이틀이미지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        // BGN 실행
        mediaplayer_title = MediaPlayer.create(this, R.raw.titlesound1);
        mediaplayer_title.setLooping(true);
        mediaplayer_title.start();

        SharedPreferences music = getSharedPreferences(MainActivity.SHARED_MUSIC,MODE_PRIVATE);

        Boolean bgmCb_title = music.getBoolean("bgmCb",true);
        Boolean effectCb_title = music.getBoolean("effectCb",true);


        if(mediaplayer_title!=null){
            if(!bgmCb_title){
                mediaplayer_title.setVolume(0,0);
            }

            else{
                mediaplayer_title.setVolume(1,1);
            }
        }

        //타이틀 텍스트뷰
        TitleText = (TextView) findViewById(R.id.touchtext);
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
    protected void onStart()
    {
        super.onStart();

        Animation bounce = AnimationUtils.loadAnimation(getApplication(), R.anim.top);
        TitleText.startAnimation(bounce);
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
                if(mediaplayer_title!=null)
                {
                    mediaplayer_title.stop();
                    mediaplayer_title.release();
                    mediaplayer_title = null;
                }

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                // 액티비티 이동시 페이드인아웃 연출
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();

                break;
            case MotionEvent.ACTION_CANCEL:
                // 터치가 취소될 때 할 일
                break;
            default:
                break;
        }
        return true;
    }
}
