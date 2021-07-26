package com.example.delta_appdev_task_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView3 extends View {

    private int c = 0;
    private float h,w,x;
    private Paint ballFiller,ballStroke,padStroke,liner;
    private ball2 baller = new ball2(15, false);
    private pad padder=new pad();
    private pad padComp=new pad();
    private interfaceListner interfaceListner;
    boolean isHard;
    public CustomView3(Context context) {
        super(context);
        initializer(null);
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializer(attrs);
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializer(attrs);
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        liner=new Paint(Paint.ANTI_ALIAS_FLAG);
        liner.setStrokeWidth(10);
        liner.setColor(Color.parseColor("#000000"));

    }
    public void setListner(interfaceListner il){
        this.interfaceListner=il;
        baller.setListner(il);
    }


    public void gravity() {
        if(isHard){
            padComp.setPadVelocity(20);
        }
        else {
            padComp.setPadVelocity(5);
        }
        interfaceListner.hide2(false);
        if (c == 1) {
            c = 2;
        }
        else if((c==0)||c==2){
            c=1;
        }
    }


    public void changer(boolean d){
        isHard=d;
    }

    public com.example.delta_appdev_task_2.ball2 getBaller() {
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
        padComp.setWidth(w);
        padComp.setHeight(h);
        if(interfaceListner!=null){
            interfaceListner.hide();
        }
        switch (c) {
            case 0:baller.setCx(w/2);
                baller.setCy(h/2);
                padder.setPad(50,240,0);
                padComp.setPad(50,240,1);
                baller.setForBall(padder);
                baller.setForComp(padComp);
                baller.setVelocity();
                break;
            case 1:
                c=baller.started(true,isHard);
                x=baller.getcor();
                moveComp(x);
                break;
            case 2:baller.setPlaying(false);
                interfaceListner.display();
                break;
            default:
                break;
        }


        canvas.drawColor(Color.parseColor("#1e14e0"));
        canvas.drawLine(0,h/2,w,h/2,liner);
        canvas.drawCircle(baller.getCx(), baller.getCy(), baller.getRad(), ballFiller);
        canvas.drawCircle(baller.getCx(), baller.getCy(), baller.getRad(), ballStroke);
        canvas.drawRect(padder.getRect(), ballFiller);
        canvas.drawRect(padder.getRect(), padStroke);
        canvas.drawRect(padComp.getRect(), ballFiller);
        canvas.drawRect(padComp.getRect(), padStroke);
        postInvalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value=true;
        if((event.getAction()==MotionEvent.ACTION_DOWN)||
                (event.getAction()==MotionEvent.ACTION_MOVE)){
            float x=event.getX();
            float y=event.getY();
            if((c==1)||(c==4)){
                do {
                    if((y>padder.getRect().top-150)&&(y<padder.getRect().bottom)){
                        if((x<w/2)&&(padder.getRect().left>0)){
                            padder.padMover(-padder.getPadVelocity());
                        }
                        if ((x>w/2)&&(padder.getRect().right<w)){
                            padder.padMover(padder.getPadVelocity());
                        }
                    }
                }while (event.getAction()==MotionEvent.ACTION_UP);
            }


                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(y<padder.getRect().top-150)
                        if(x<w)
                            gravity();

            }
        }
        return value;
    }
    public void moveComp(float a){
        if((a>padComp.getRect().centerX())&&(padComp.getRect().right<w)){
            padComp.padMover(padComp.getPadVelocity());
        }
        if((a<padComp.getRect().centerX())&&(padComp.getRect().left>0)){
            padComp.padMover(-padComp.getPadVelocity());
        }
    }
}
