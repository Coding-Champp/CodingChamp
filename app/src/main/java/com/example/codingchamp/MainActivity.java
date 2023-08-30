package com.example.codingchamp;

import android.content.Context;
import android.content.Intent;
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
        arrayList.add(new ModelClass("What is the correct way to declare a variable in Java? ", "variable = 10;", "10 = variable;", "int variable = 10", "variable int = 10", "int variable = 10"));
        arrayList.add(new ModelClass("Which statement is used to take input from the user in Java?", "System.out.println();", "System.in.read()", "readLine()", "Scanner sc = new Scanner(System.in);", "Scanner sc = new Scanner(System.in);"));
        arrayList.add(new ModelClass("What is the correct way to declare a method that does not return any value in Java?", "void methodName() {}", "String methodName() {}", "methodName() {}", "int methodName() {}", "void methodName() {}"));
        arrayList.add(new ModelClass("Which operator is used for logical 'AND' in Java?", "&", "|", "&&", "||", "&&"));
        arrayList.add(new ModelClass("How do you print the Fibonacci sequence up to n terms in Java?", "Use a for loop to iterate over the sequence", "Use a while loop to iterate over the sequence", "Use a do-while loop to iterate over the sequence", "Use a recursive function to calculate the Fibonacci sequence", "Use a for loop to iterate over the sequence"));
        arrayList.add(new ModelClass("Which loop is guaranteed to execute at least once in Java?", "for loop", "While loop", "switch loop", "do-while loop", "do-while loop"));
        arrayList.add(new ModelClass("Which of the following is true about the break statement in a loop?", "It terminates the loop and continues with the next iteration.", "It is used to exit from a method.", "It is used to skip the rest of the code in the current iteration.", "It terminates the loop entirely.", "It terminates the loop entirely."));
        arrayList.add(new ModelClass("Number of primitive data types in Java are?", "6", "7", "8", "9", "8"));
        arrayList.add(new ModelClass("Arrays in java are?", "Object Reference", "Objects", "primitive Data Types", "None", "Objects"));
        arrayList.add(new ModelClass("Identify the modifier which cannot be used for constructor.", "Public", "Protected", "Static", "Private", "Static"));
        arrayList.add(new ModelClass("Identify the correct way of declaring constructor.", "JSPM(){}", "Public JSPM(){}", "JSPM(void){}", "Both (A) and (B)", "Both (A) and (B)"));
        arrayList.add(new ModelClass("Exception created by try block is caught in which block", "Final", "Catch", "Throw", "None", "Catch"));
        arrayList.add(new ModelClass("What is the extension of java code files?", ".js", ".class", ".java", ".txt", ".java"));
        arrayList.add(new ModelClass("Which one of the following is not an access modifier?", "Protected", "Void", "Public", "Private", "Void"));
        arrayList.add(new ModelClass("On which platforms Java runs?", "Windows", "MAC OS", "LINUX", "All of the above", "All of the above"));
        arrayList.add(new ModelClass("Which package contains the Scanner class?", "java.util package", "java.lang package", "java.awt package", "java.io package", "java.util package"));


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
                        correctAnswer++;
                    } else {
                        wrongAnwer++;
                    }
                } else if (g1.getCheckedRadioButtonId() == R.id.b) {
                    if (modelClass.getB().equals(modelClass.getAns())) {
                        correctAnswer++;
                    } else {
                        wrongAnwer++;
                    }

                } else if (g1.getCheckedRadioButtonId() == R.id.c) {
                    if (modelClass.getC().equals(modelClass.getAns())) {
                        correctAnswer++;
                    } else {
                        wrongAnwer++;
                    }

                } else if (g1.getCheckedRadioButtonId() == R.id.d) {
                    if (modelClass.getD().equals(modelClass.getAns())) {
                        correctAnswer++;
                    } else {
                        wrongAnwer++;
                    }


                }
                g1.clearCheck();
                next();
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.alert);
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
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("Name", getIntent().getStringExtra("Name"));
            intent.putExtra("score", correctAnswer);
            startActivity(intent);

        }
    }
}


