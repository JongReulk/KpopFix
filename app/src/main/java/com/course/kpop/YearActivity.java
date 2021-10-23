package com.course.kpop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class YearActivity extends AppCompatActivity {

    private ScrollView year_scroll;
    private Button year_up;
    private Button year_down;
    private LinearLayout year_linear;
    private int button_total;
    private int button_num;
    private int[] loc = new int[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_new);

        year_scroll = findViewById(R.id.year_Scrollview);
        year_up = findViewById(R.id.year_up);
        year_down = findViewById(R.id.year_down);
        year_linear = findViewById(R.id.year_linearlayout);
        View year_v;


        Button year_b;

        button_num = 0;

        Log.e("Focus"," : " + getCurrentFocus());

        button_total = year_linear.getChildCount();

        Log.e("total : " ," : " +button_total);

        year_v = year_linear.getChildAt(button_num);

        year_v.setSelected(true);


        year_scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        year_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button_num < button_total-1) {

                    View year_v = year_linear.getChildAt(button_num);

                    year_v.setFocusable(false);
                    year_v.setSelected(false);

                    button_num++;

                    year_v = year_linear.getChildAt(button_num);
                    year_v.setSelected(true);
                    year_v.setFocusable(true);

                    Log.e("Button : " ," : " +button_num);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            View year_v = year_linear.getChildAt(button_num);
                            year_scroll.smoothScrollTo(0, year_v.getTop());

                        }
                    }, 100);
                }

            }
        });

        year_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_num > 0)
                {
                    View year_v = year_linear.getChildAt(button_num);

                    year_v.setSelected(false);

                    button_num--;
                    year_v = year_linear.getChildAt(button_num);
                    year_v.setSelected(true);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            View year_v = year_linear.getChildAt(button_num);
                            year_scroll.smoothScrollTo(0, year_v.getTop());

                        }
                    }, 100);
                }

            }
        });

    }

}