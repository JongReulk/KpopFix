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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class YearActivity extends AppCompatActivity {

    private ScrollView year_scroll;
    private ImageButton year_up;
    private ImageButton year_down;
    private Button year_confirm;
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
        year_confirm = findViewById(R.id.year_confirm);
        year_linear = findViewById(R.id.year_linearlayout);
        View year_v;


        Button year_b;

        button_num = 0;

        Log.e("Focus"," : " + getCurrentFocus());

        button_total = year_linear.getChildCount();
        button_num = button_total/2;

        Log.e("total : " ," : " +button_total);

        year_v = year_linear.getChildAt(button_num);

        year_v.setSelected(true);
        year_b = (Button)year_v;
        year_b.setTextColor(getResources().getColor(R.color.black));
        year_b.setTextSize(24);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.e("Num "," : " + button_num);
                View year_v = year_linear.getChildAt(button_num-1);
                year_scroll.smoothScrollTo(0, year_v.getTop());

            }
        }, 50);


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

                    year_v.setSelected(false);

                    Button year_b = (Button)year_v;
                    year_b.setTextColor(getResources().getColor(R.color.white));
                    year_b.setTextSize(18);

                    button_num++;

                    year_v = year_linear.getChildAt(button_num);
                    year_v.setSelected(true);

                    year_b = (Button)year_v;
                    year_b.setTextColor(getResources().getColor(R.color.black));
                    year_b.setTextSize(24);


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            View year_v = year_linear.getChildAt(button_num-1);
                            year_scroll.smoothScrollTo(0, year_v.getTop());

                        }
                    }, 500);


                    Log.e("Button : " ," : " +button_num);


                }

            }
        });

        year_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year_num = button_num + 2010;
                Toast.makeText(getApplicationContext(),"year : " + year_num,Toast.LENGTH_SHORT).show();

            }
        });

        year_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_num > 0)
                {
                    View year_v = year_linear.getChildAt(button_num);

                    year_v.setSelected(false);

                    Button year_b = (Button)year_v;
                    year_b.setTextColor(getResources().getColor(R.color.white));
                    year_b.setTextSize(18);

                    button_num--;
                    year_v = year_linear.getChildAt(button_num);
                    year_v.setSelected(true);

                    year_b = (Button)year_v;
                    year_b.setTextColor(getResources().getColor(R.color.black));
                    year_b.setTextSize(24);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(button_num == 0){
                                View year_v = year_linear.getChildAt(button_num);
                                year_scroll.smoothScrollTo(0, year_v.getTop());
                            }
                            else {
                                View year_v = year_linear.getChildAt(button_num - 1);
                                year_scroll.smoothScrollTo(0, year_v.getTop());
                            }


                        }
                    }, 50);
                    Log.e("Button : " ," : " +button_num);
                }

            }
        });

    }

}