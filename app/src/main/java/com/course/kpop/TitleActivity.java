package com.course.kpop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.service.wallpaper.WallpaperService;

public class TitleActivity extends AppCompatActivity {

    // MediaPlayer 객체생성
    private static MediaPlayer mediaplayer;
    private static TextView TitleText; // 터치투스타트
    private static ImageView TitleImage; // 타이틀이미지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        // BGN 실행
        mediaplayer = MediaPlayer.create(this, R.raw.titlesound1);
        mediaplayer.start();

        //타이틀 텍스트뷰
        TitleText = (TextView) findViewById(R.id.touchtext);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Animation animTreanTop = AnimationUtils.loadAnimation(getApplication(), R.anim.top);
        TitleText.startAnimation(animTreanTop);
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
                mediaplayer.stop();
                mediaplayer.release();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

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
