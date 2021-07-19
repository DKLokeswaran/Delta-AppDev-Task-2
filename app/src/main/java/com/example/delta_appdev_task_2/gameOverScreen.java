package com.example.delta_appdev_task_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.WindowManager;

public class gameOverScreen extends AppCompatActivity {

    private Button back;
    private int score;
    private int highscore[]={0,0,0};
    private TextView scrDsp,msgDsp,hiScoreDsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        msgDsp=findViewById(R.id.message);
        hiScoreDsp=findViewById(R.id.highScr);
        back=findViewById(R.id.mainmenu);
        scrDsp=findViewById(R.id.scoreDisplay);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0);
        getHighScore();
        for (int i=1;i<=3;i++){
            if(score>highscore[i-1]){
                int k=3-i;
                while (k>0){
                    highscore[k]=highscore[k-1];
                    k--;
                }
                highscore[i-1]=score;
                break;
            }
        }
        if(highscore[0]<=score){
            msgDsp.setText("Congrats, this is a new high score");
        }
        hiScoreDsp.setText("High Score: "+highscore[0]);
        scrDsp.setText("You scored "+score);
        saveHighscore();
    }
    public void backto(View view){
        Intent intent=new Intent(gameOverScreen.this,MainActivity2.class);
        startActivity(intent);
        finish();
    }
    public void oncemore(View view){
        Intent intent=new Intent(gameOverScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void saveHighscore(){
        SharedPreferences sharedPreferences=this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        for(int i=1;i<=3;i++){
            editor.putInt("highScore"+i,highscore[i-1]);
        }
        editor.commit();
    }
    public void getHighScore(){
        SharedPreferences sharedPreferences=getSharedPreferences("pref", Context.MODE_PRIVATE);
        for (int i=1;i<=3;i++){
            highscore[i-1]=sharedPreferences.getInt("highScore"+i,0);
        }
    }
}