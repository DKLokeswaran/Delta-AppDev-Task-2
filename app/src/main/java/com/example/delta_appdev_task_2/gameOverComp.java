package com.example.delta_appdev_task_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class gameOverComp extends AppCompatActivity {
    private int scr1,scr2;
    private boolean hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_comp);
        TextView messages[]={findViewById(R.id.msg1),findViewById(R.id.msg2),findViewById(R.id.msg3),findViewById(R.id.msg4)};
        Intent intent=getIntent();
        scr1=intent.getIntExtra("Score1",0);
        scr2=intent.getIntExtra("Score2",0);
        hard=intent.getBooleanExtra("isHard",false);
        if(hard){
            messages[3].setText("Mode:Hard");
        }
        else {
            messages[3].setText("Mode:Easy");
        }
        if(scr1>scr2){
            messages[0].setText("Try Harder Next Time");
            messages[1].setText("You Lost");
        }
        else {
            messages[0].setText("Congradulations");
            messages[1].setText("You Won");
        }
        messages[2].setText("Score: "+scr2+"-"+scr1);
    }
    public void again(View v){
        Intent intent=new Intent(gameOverComp.this,compMode.class);
        startActivity(intent);
        finish();
    }
    public void back(View v){
        Intent intent=new Intent(gameOverComp.this,MainActivity2.class);
        startActivity(intent);
        finish();
    }
}