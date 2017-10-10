package com.example.matheussaviczki.accerateste;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectThemeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);

        MediaPlayer mdp = MediaPlayer.create(this,R.raw.star_theme);
        mdp.start();

    }
}
