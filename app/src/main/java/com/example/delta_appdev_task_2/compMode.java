package com.example.delta_appdev_task_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class compMode extends AppCompatActivity implements interfaceListner {

    private CustomView3 customView3;
    private TextView message;
    private static MediaPlayer sound,sound1,sound2,sound3,sound4;
    private TextView scr1,scr2;
    private Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_mode);
        customView3=findViewById(R.id.customView3);
        message=findViewById(R.id.hidden);
        scr1=findViewById(R.id.Score1);
        scr2=findViewById(R.id.Score2);
        aSwitch=findViewById(R.id.switch2);
        customView3.setListner(this);
    }
    public void changer(View v){
        customView3.changer(aSwitch.isChecked());
    }

    @Override
    public void switcher(int a, boolean var2,int b) {
        Intent intent=new Intent(compMode.this,gameOverComp.class);
        if(b==0){
            intent.putExtra("Score1",5);
            intent.putExtra("Score2",a);
        }
        if(b==1){
            intent.putExtra("Score1",a);
            intent.putExtra("Score2",5);
        }
        intent.putExtra("isHard",var2);
        startActivity(intent);
        finish();
    }

    @Override
    public void scorer(int b,int c) {
        if(c==0){
            scr1.setText(Integer.toString(b));
        }
        if(c==1){
            scr2.setText(Integer.toString(b));
        }
        if(c==3){
            scr1.setText(Integer.toString(b));
            scr2.setText(Integer.toString(b));
        }
    }

    @Override
    public void display() {
        message.setText("Game is paused. Tap to resume");
        message.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        message.setVisibility(View.INVISIBLE);
    }

    @Override
    public void sound(int s) {
        switch (s){
            case 1:
                sound = MediaPlayer.create(this, R.raw.gameover);
                sound.start();
                sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });
                break;
            case 2:
                sound1= MediaPlayer.create(this, R.raw.collision);
                sound1.start();
                sound1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });
                break;
            case 3:
                sound2 = MediaPlayer.create(this, R.raw.padsound);
                sound2.start();
                sound2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });
                break;
            case 4:
                sound3 = MediaPlayer.create(this, R.raw.fail);
                sound3.start();
                sound3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });
                break;
            case 5:
                sound4 = MediaPlayer.create(this, R.raw.win);
                sound4.start();
                sound4.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    };
                });
                break;
            default:break;
        }
    }

    @Override
    public void hide2(boolean b) {
        if(b){
            aSwitch.setVisibility(View.VISIBLE);
        }
        else {
            aSwitch.setVisibility(View.INVISIBLE);
        }
        aSwitch.setEnabled(b);
    }
}