package com.course.kpop;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

public class SettingDialog extends Dialog {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;


    public SettingDialog(@NonNull Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_setting);

    }
}
