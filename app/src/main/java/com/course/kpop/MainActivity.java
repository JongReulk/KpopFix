package com.course.kpop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    public static final int REQUEST_CODE_QUIZ = 101;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LANGUAGE_PREFS = "sharedLanguage";
    public static final String KEY_HIGHSCORE = "keyhighscore";

    private int highscore;
    private TextView textViewHighscore;

    private long backPressedTime;

    private ImageView settingOpen;

    SettingDialog settingDialog;

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    private String locale;
    private int locale_number;

    private Spinner lanSpinner;
    ArrayList<String> locales;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        textViewHighscore = findViewById(R.id.txtbestScore);
        settingOpen = findViewById(R.id.settingOpen);
        //mediaPlayer = mediaPlayer.create(this, R.raw.titlesound1);

        //audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);


        // 언어설정 스피너
        SharedPreferences lanprefs = getSharedPreferences(LANGUAGE_PREFS,MODE_PRIVATE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = lanprefs.getString("locale",getResources().getConfiguration().getLocales().get(0).getLanguage());
        }
        else{
            locale = lanprefs.getString("locale", Resources.getSystem().getConfiguration().locale.getLanguage());
        }

        switch (locale){
            case "ko":{
                locale_number = 0;
                Log.e("언어",":"+locale);
                break;
            }

            case "en":{
                locale_number = 1;
                Log.e("언어",":"+locale);
                break;
            }
        }

        lanSpinner = findViewById(R.id.languagespinner);
        locales = new ArrayList<>();

        locales.add(getStringByLocal(this,R.string.Korean,locale));
        locales.add(getStringByLocal(this,R.string.English,locale));

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, locales);

        lanSpinner.setAdapter(adapter);
        lanSpinner.setSelection(locale_number);


        Intent intent = getIntent();


        loadHighscore();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DifficultyActivity.class);
                startActivityForResult(intent,REQUEST_CODE_QUIZ);
            }
        });


        settingOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dial();

            }
        });

        lanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != locale_number){

                    switch (position){
                        case 0:{
                            locale = "ko";
                            Log.e("언어",":"+locale);
                            break;
                        }
                        case 1:{
                            locale = "en";
                            Log.e("언어",":"+locale);
                            break;
                        }
                    }
                    SharedPreferences.Editor laneditor = lanprefs.edit();

                    laneditor.putString("locale",locale);

                    laneditor.commit();

                    Intent intent = getBaseContext().getPackageManager() .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                    startActivity(intent);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lanSpinner.setSelection(locale_number);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("데이터를 받았는가","?"+requestCode +" , " + resultCode + " , " + data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizMain.HIGH_SCORE, 0);
                Log.e("점수를 받았는가","?" + score);
                if (score > highscore){
                    updateHighscore(score);
                }
            }
        }
    }

    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;
        textViewHighscore.setText("Best: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Best: " + highscore);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            finish();
        }else{
            Toast.makeText(this, "한번 더 뒤로버튼을 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    private void dial(){
        settingDialog = new SettingDialog(this);
        settingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        settingDialog.setCancelable(false);
        settingDialog.show();


        Button cancelBtn = settingDialog.findViewById(R.id.btn_cancel);
        Button confirmBtn = settingDialog.findViewById(R.id.btn_confirm);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingDialog.dismiss();

            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingDialog.dismiss();

            }
        });

   }

    @NonNull
    public static String getStringByLocal(Activity context, int resId, String locale) {
        //버전에 따라서 언어를 설정해주는 방식이 다르기 때문에 분류해서 사용합니다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            return getStringByLocalPlus17(context, resId, locale);
        else return getStringByLocalBefore17(context, resId, locale);
    }

    //젤리빈 버전 이상일 경우
    @NonNull
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static String getStringByLocalPlus17(Activity context, int resId, String locale) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(new Locale(locale));
        return context.createConfigurationContext(configuration).getResources().getString(resId);
    }


    //젤리빈 버전 이하일 경우
    private static String getStringByLocalBefore17(Context context, int resId, String language) {
        Resources currentResources = context.getResources();
        AssetManager assets = currentResources.getAssets();
        DisplayMetrics metrics = currentResources.getDisplayMetrics();
        Configuration config = new Configuration(currentResources.getConfiguration());
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        config.locale = locale;
        /*
        * Note: This (temporarily) changes the devices locale!
        * TODO find a better way to get the string in the specific locale
        *  */
        Resources defaultLocaleResources = new Resources(assets, metrics, config);
        String string = defaultLocaleResources.getString(resId);
        // Restore device-specific locale
        new Resources(assets, metrics, currentResources.getConfiguration());
        return string;
    }








    /*
    public void startButton(View view) {
        Intent intent = new Intent(this, DifficultyActivity.class);
        startActivity(intent);
    }*/
}