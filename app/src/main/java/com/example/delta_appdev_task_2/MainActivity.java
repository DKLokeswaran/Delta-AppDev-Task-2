package com.example.delta_appdev_task_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements interfaceListner{

    private CustomView customView;
    TextView scr,disp;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        scr=findViewById(R.id.textView2);
        disp=findViewById(R.id.textView4);
        customView=findViewById(R.id.customView);
        customView.setListner(this);
        reset=findViewById(R.id.button2);

    }



    public void stop(View view){
        customView.def();
    }


    @Override
    public void switcher(int var) {
        Intent intent=new Intent(MainActivity.this,gameOverScreen.class);
            intent.putExtra("score",var);
        startActivity(intent);
        finish();
    }

    @Override
    public void scorer(int b) {
        scr.setText(Integer.toString(b));
    }

    @Override
    public void display() {
        disp.setText("Game is paused. Tap to resume");
        disp.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        disp.setVisibility(View.INVISIBLE);
    }
}