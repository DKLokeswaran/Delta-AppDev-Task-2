package com.example.delta_appdev_task_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private int c = 0;
    private float h,w;
    private Paint ballFiller,ballStroke,padStroke,text;
    private ball baller = new ball(15, false);
    private pad padder=new pad();
    private interfaceListner interfaceListner;




    public CustomView(Context context) {
        super(context);
        initializer(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializer(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializer(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializer(attrs);
    }

    private void initializer(@Nullable AttributeSet attributeSet) {
        ballFiller = new Paint(Paint.ANTI_ALIAS_FLAG);
        ballFiller.setColor(Color.parseColor("#ffffff"));
        ballStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        ballStroke.setStyle(Paint.Style.STROKE);
        ballStroke.setStrokeWidth(2);
        ballStroke.setColor(Color.parseColor("#000000"));
        padStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        padStroke.setStyle(Paint.Style.STROKE);
        padStroke.setStrokeWidth(baller.getRad());
        padStroke.setColor(Color.parseColor("#000000"));
        padder.setPadVelocity(10);
        text=new Paint(Paint.ANTI_ALIAS_FLAG);
        text.setColor(Color.parseColor("#000000"));
        text.setTextAlign(Paint.Align.CENTER);
        text.setTextSize(100);
    }
    public void setListner(interfaceListner il){
        this.interfaceListner=il;
        baller.setListner(il);
    }


    public void gravity() {
        if (c == 1) {
            c = 2;
        } else {
            c = 1;
        }
    }
    public void def(){
        if(c!=3){
            c=0;
            interfaceListner.scorer(0);
        }

    }


    public com.example.delta_appdev_task_2.ball getBaller() {
        return baller;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        h = getHeight();
        w = getWidth();
        baller.setWidth(w);
        baller.setHeight(h);
        padder.setWidth(w);
        padder.setHeight(h);
        interfaceListner.hide();
        switch (c) {
            case 0:baller.setCx(w/2);
                   baller.setCy(h/2);
                   padder.setPad(50,240);
                   baller.setForBall(padder);
                   baller.setVelocity();
                   baller.setScore(0);
                   break;
            case 1:
                c=baller.started();
                break;
            case 2:baller.setPlaying(false);
                    interfaceListner.display();
                    break;
            default:
                break;
        }

//        if(c==2){
//            canvas.drawText("Game is paused. Tap to resume",w/2,h/2,text);
//        }
        canvas.drawColor(Color.parseColor("#1e14e0"));
        canvas.drawCircle(baller.getCx(), baller.getCy(), baller.getRad(), ballFiller);
        canvas.drawCircle(baller.getCx(), baller.getCy(), baller.getRad(), ballStroke);
        canvas.drawRect(padder.getRect(), ballFiller);
        canvas.drawRect(padder.getRect(), padStroke);
        postInvalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value=true;
        if((event.getAction()==MotionEvent.ACTION_DOWN)||
                (event.getAction()==MotionEvent.ACTION_MOVE)){
            float x=event.getX();
            float y=event.getY();
            if(c==1){
                do {
                    if((y>padder.getRect().top-150)&&(y<padder.getRect().bottom)){
                        if((x<w/2)&&(padder.getRect().left!=0)){
                            padder.padMover(-padder.getPadVelocity());
                        }
                        if ((x>w/2)&&(padder.getRect().right!=w)){
                            padder.padMover(padder.getPadVelocity());
                        }
                    }
                }while (event.getAction()==MotionEvent.ACTION_UP);
            }


            if((event.getAction()==MotionEvent.ACTION_DOWN)&&(c==3)){
                c=0;
            }
            else {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(y<padder.getRect().top-150)
                        if(x<w)
                            gravity();
                }
            }
        }


        return value;
    }

}

