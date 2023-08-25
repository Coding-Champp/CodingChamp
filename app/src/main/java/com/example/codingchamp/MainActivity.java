package com.example.codingchamp;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    private boolean isAppReopened = false;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    MaterialCardView b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);

        mediaPlayer = MediaPlayer.create(this, R.raw.hindicopy);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int currentRingerMode = audioManager.getRingerMode();
        if (currentRingerMode == AudioManager.RINGER_MODE_SILENT) {

            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               b1.setCardBackgroundColor(Color.RED);
               b2.setCardBackgroundColor(Color.WHITE);
               b4.setCardBackgroundColor(Color.WHITE);

               b3.setCardBackgroundColor(Color.GREEN);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2.setCardBackgroundColor(Color.RED);
                b1.setCardBackgroundColor(Color.WHITE);
                b4.setCardBackgroundColor(Color.WHITE);
                b3.setCardBackgroundColor(Color.GREEN);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b3.setCardBackgroundColor(Color.GREEN);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b4.setCardBackgroundColor(Color.RED);
                b1.setCardBackgroundColor(Color.WHITE);
                b2.setCardBackgroundColor(Color.WHITE);
                b3.setCardBackgroundColor(Color.GREEN);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isAppReopened) {
            isAppReopened = true;

            Toast.makeText(this, "Don't Copy ", Toast.LENGTH_SHORT).show();


            mediaPlayer.start();
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);


        } else {
            isAppReopened = true;
        }
    }


}