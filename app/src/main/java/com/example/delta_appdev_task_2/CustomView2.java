package com.example.delta_appdev_task_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView2 extends View {
    private ball ball1=new ball(15,false);
    private ball ball2=new ball(15,false);
    private ball ball3=new ball(15,false);
    private ball ball4=new ball(15,false);
    int a=0,b=0,c=0,d=0;
    private Paint bgrd,bgrdStroke;
    public CustomView2(Context context) {
        super(context);
        initialize(null);
    }

    public CustomView2(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs);
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(attrs);
    }
    private void initialize(AttributeSet attributeSet){
        bgrd = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgrd.setColor(Color.parseColor("#ffffff"));
        bgrdStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgrdStroke.setStyle(Paint.Style.STROKE);
        bgrdStroke.setStrokeWidth(2);
        bgrdStroke.setColor(Color.parseColor("#000000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ball1.setWidth(getWidth());
        ball1.setHeight(getHeight());
        if(a==0){
            ball1.setCx(getWidth()/2);
            ball1.setCy(getHeight()/2);
            ball1.setVelocity();
        }
        a=ball1.started(false);
        ball2.setWidth(getWidth());
        ball2.setHeight(getHeight());
        if(b==0){
            ball2.setCx(getWidth()/2);
            ball2.setCy(getHeight()/2);
            ball2.setVelocity();
        }
        b=ball2.started(false);
        ball3.setWidth(getWidth());
        ball3.setHeight(getHeight());
        if(c==0){
            ball3.setCx(getWidth()/2);
            ball3.setCy(getHeight()/2);
            ball3.setVelocity();
        }
        c=ball3.started(false);
        ball4.setWidth(getWidth());
        ball4.setHeight(getHeight());
        if(d==0){
            ball4.setCx(getWidth()/2);
            ball4.setCy(getHeight()/2);
            ball4.setVelocity();
        }
        d=ball4.started(false);
        canvas.drawColor(Color.parseColor("#1e14e0"));
        canvas.drawCircle(ball1.getCx(), ball1.getCy(), ball1.getRad(), bgrd);
        canvas.drawCircle(ball2.getCx(), ball2.getCy(), ball2.getRad(), bgrd);
        canvas.drawCircle(ball3.getCx(), ball3.getCy(), ball3.getRad(), bgrd);
        canvas.drawCircle(ball4.getCx(), ball4.getCy(), ball4.getRad(), bgrd);
        canvas.drawCircle(ball1.getCx(), ball1.getCy(), ball1.getRad(), bgrdStroke);
        canvas.drawCircle(ball2.getCx(), ball2.getCy(), ball2.getRad(), bgrdStroke);
        canvas.drawCircle(ball3.getCx(), ball3.getCy(), ball3.getRad(), bgrdStroke);
        canvas.drawCircle(ball4.getCx(), ball4.getCy(), ball4.getRad(), bgrdStroke);
        invalidate();
    }
}
