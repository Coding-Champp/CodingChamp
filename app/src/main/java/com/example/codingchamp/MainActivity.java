package com.example.codingchamp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private boolean isAppReopened = false;
    private MediaPlayer mediaPlayer;
    RadioGroup g1;
    TextView question;
    RadioButton rb1, rb2, rb3, rb4;
    CountDownTimer timer;
    ModelClass modelClass;
    int index = 0;
    int timerVal = 60;
    ProgressBar progressBar;
    Button b1;
    private AudioManager audioManager;
    int correctAnswer = 0;
    int wrongAnwer = 0;

    ArrayList<ModelClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        b1 = findViewById(R.id.next_btn);


        arrayList = new ArrayList<>();
        arrayList.add(new ModelClass("Who Invented Java", "James Gostling", "Atharv", "Niraj", "Omakr", "Atharv"));
        arrayList.add(new ModelClass("Who Invented Python", "James Gostling", "MAne", "Niraj", "Omakr", "Niraj"));
        arrayList.add(new ModelClass("Who Invented C", "James Gostling", "Ath", "Niraj", "Omakr", "Ath"));
        arrayList.add(new ModelClass("Who Invented C++", "James Gostling", "Athy", "Niraj", "Omakr", "James Gostling"));
        arrayList.add(new ModelClass("Who Invented JAVA", "James Gostling", "Athy MAne", "Niraj", "Omakr", "Omakr"));

        Collections.shuffle(arrayList);
        modelClass = arrayList.get(index);


        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                timerVal = timerVal - 1;
                progressBar.setProgress(timerVal);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "You have Been Eliminated from the game", Toast.LENGTH_SHORT).show();
            }
        }.start();

        ID();
        setAllData();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (g1.getCheckedRadioButtonId() == R.id.a) {
                    if (modelClass.getA().equals(modelClass.getAns())) {
                        Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                        correctAnswer++;
                    } else {
                        wrongAnwer++;
                    }
                } else if (g1.getCheckedRadioButtonId() == R.id.b) {
                    if (modelClass.getB().equals(modelClass.getAns())) {
                        Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                        correctAnswer++;
                    } else {
                        wrongAnwer++;
                    }

                } else if (g1.getCheckedRadioButtonId() == R.id.c) {
                    if (modelClass.getC().equals(modelClass.getAns())) {
                        Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                        correctAnswer++;
                    } else {
                        wrongAnwer++;
                    }

                } else if (g1.getCheckedRadioButtonId() == R.id.d) {
                    if (modelClass.getD().equals(modelClass.getAns())) {
                        Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                        correctAnswer++;
                    } else {
                        wrongAnwer++;
                    }


                }
                next();
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.hindicopy);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int currentRingerMode = audioManager.getRingerMode();
        if (currentRingerMode == AudioManager.RINGER_MODE_SILENT) {

            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        }

    }

    private void setAllData() {


        question.setText(modelClass.getQuestions());
        rb1.setText(modelClass.getA());
        rb2.setText(modelClass.getB());
        rb3.setText(modelClass.getC());
        rb4.setText(modelClass.getD());
        timerVal = 60;
        timer.cancel();
        timer.start();
    }

    private void ID() {
        progressBar = findViewById(R.id.simpleProgressBar);
        g1 = findViewById(R.id.radio_ans);
        rb1 = findViewById(R.id.a);
        rb2 = findViewById(R.id.b);
        rb3 = findViewById(R.id.c);
        rb4 = findViewById(R.id.d);
        question = findViewById(R.id.txt_question);

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


    public void next() {
        if (index < arrayList.size() - 1) {
            index++;
            modelClass = arrayList.get(index);
            setAllData();
        } else {
            Toast.makeText(this, "Correct: " + correctAnswer + "\nWrong: " + wrongAnwer, Toast.LENGTH_SHORT).show();
        }
    }
}


