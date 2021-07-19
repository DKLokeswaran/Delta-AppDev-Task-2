package com.example.delta_appdev_task_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void start(View view){
        Intent intent=new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
    }
    public void hScore(View v){
        Intent intent=new Intent(MainActivity2.this,highScores.class);
        startActivity(intent);
    }
    public void exit(View v){
        this.finishAffinity();
    }
}