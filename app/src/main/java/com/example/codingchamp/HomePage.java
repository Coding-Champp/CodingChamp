package com.example.codingchamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
CardView c1,c2,c3;
RelativeLayout r1,r2,r3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        c1=findViewById(R.id.c1);
        r1=findViewById(R.id.r1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("Name", getIntent().getStringExtra("Name"));
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        if(MainActivity.result==1)
        {
            c1.setClickable(false);
            Toast.makeText(this, "Round 1 Completed", Toast.LENGTH_SHORT).show();
          r1.setBackgroundColor(Color.rgb(144, 238, 144));
        }
        super.onResume();
    }
}