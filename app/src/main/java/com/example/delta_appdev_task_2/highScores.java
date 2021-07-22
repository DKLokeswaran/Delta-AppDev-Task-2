 package com.example.delta_appdev_task_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class highScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        SharedPreferences sharedPreferences=getSharedPreferences("pref", Context.MODE_PRIVATE);
        TextView[] scores={findViewById(R.id.hs0),findViewById(R.id.hs1),findViewById(R.id.hs2)};
        TextView[] Scores={findViewById(R.id.HS0),findViewById(R.id.HS1),findViewById(R.id.HS2)};
        for (int i=1;i<=3;i++){
            scores[i-1].setText(Integer.toString(sharedPreferences.getInt("highScore"+i,0)));
            Scores[i-1].setText(Integer.toString(sharedPreferences.getInt("HighScore"+i,0)));
        }
    }
    public void backgo(View v){
        Intent intent=new Intent(highScores.this,MainActivity2.class);
        startActivity(intent);
        finish();
    }
}