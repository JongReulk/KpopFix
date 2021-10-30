package com.course.kpop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.CaseMap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import android.media.SoundPool;
import android.os.Handler;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import me.relex.circleindicator.CircleIndicator3;
import android.os.Handler;

public class TipsActivity extends AppCompatActivity{

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 3;
    private CircleIndicator3 mIndicator;

    // MediaPlayer 객체생성
    public static MediaPlayer mediaplayer_title;

    private Button close;
    private Button closeforever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        // BGN 실행
        mediaplayer_title = MediaPlayer.create(this, R.raw.tipsmusic);
        mediaplayer_title.setLooping(true);
        mediaplayer_title.start();

        // 버튼
        close = findViewById(R.id.close_Button);
        closeforever = findViewById(R.id.closeforever_Button);
        // 핸들러
        Handler handler = new Handler();

        //가로 슬라이드 뷰 Fragment
        //ViewPager2
        mPager = findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page,
                0);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);


        mPager.setCurrentItem(0); //시작 지점
        mPager.setOffscreenPageLimit(4); //최대 이미지 수
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // bgm 끄기
                if(mediaplayer_title!=null)
                {
                    mediaplayer_title.stop();
                    mediaplayer_title.release();
                    mediaplayer_title = null;
                }

                close.setEnabled(false);
                close.setTextColor(Color.GRAY);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                        // 액티비티 이동시 페이드인아웃 연출
                        //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        finish();
                    }
                }, 600); //딜레이 타임 조절
            }
        });

        closeforever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다시보지않기
                SharedPreferences closef = getSharedPreferences(TitleActivity.SHARED_CLOSE,MODE_PRIVATE);
                SharedPreferences.Editor closeEditor = closef.edit();
                closeEditor.putBoolean("closeforever", false);
                closeEditor.apply();
                Boolean close_Tips = closef.getBoolean("closeforever",true);

                // bgm 끄기
                if(mediaplayer_title!=null)
                {
                    mediaplayer_title.stop();
                    mediaplayer_title.release();
                    mediaplayer_title = null;
                }

                closeforever.setEnabled(false);
                closeforever.setTextColor(Color.GRAY);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                        // 액티비티 이동시 페이드인아웃 연출
                        //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        finish();
                    }
                }, 600); //딜레이 타임 조절
            }
        });
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




    /*
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
                if(mPager.getCurrentItem() == num_page - 1)
                {
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
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                // 터치가 취소될 때 할 일
                break;
            default:
                break;
        }
        return true;
    }*/
}
