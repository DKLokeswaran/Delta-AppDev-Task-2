package com.example.delta_appdev_task_2;


import java.util.Random;

public class ball {
    private int rad,height,width,theta;
    private double vx,vy,cx,cy;
    private boolean isPlaying;
    private int velocity;
    private Random random=new Random();
    private pad ForBall=new pad();
    private int score=0;
    private interfaceListner interfaceListner;


    public ball( int rad, boolean isPlaying) {

        this.rad = rad;
        this.isPlaying = isPlaying;



    }
    public void setVelocity(){
        velocity=15;
        if(interfaceListner!=null){
            int x=random.nextInt(45)+1;
            int t=random.nextInt(2);
            theta=105+x-(75*t);
        }
        else {
            theta=random.nextInt(120)+31;
        }
//        float theta = 90;
        vx=velocity*Math.cos(Math.toRadians(theta));
        vy=velocity*Math.sin(Math.toRadians(theta));
    }
    public void resetVelocity(){
        velocity+=1;
        double theta1=Math.atan(vy/vx);
        if(theta1<0){
            theta1+=Math.toRadians(180);
        }
        vx=velocity*Math.cos(theta1);
        vy=velocity*Math.sin(theta1);
    }
    public int started(boolean b){
        int gamer;
        gamer=collisionDetector(b);
        cx+=vx;
        cy+=vy;
        isPlaying=true;
        return gamer;
    }

    public void setHeight(float height) {
        this.height =(int) height;
    }

    public void setWidth(float width) {
        this.width =(int) width;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }



    public float getCx() {
        return (float) cx;
    }

    public float getCy() {
        return (float) cy;
    }
    public int collisionDetector(boolean bool) {
        int controller=1;
        if ((height - cy) < rad) {
            vy = -vy;
            controller=3;
            if (interfaceListner!=null){
                interfaceListner.switcher(score,bool);
                interfaceListner.sound(1);
            }
        }
        else if ((cy ) < rad) {
            vy = -vy;
            if (interfaceListner!=null){
                interfaceListner.sound(2);
            }

        }
        else if (((ForBall.getRect().top - cy-rad) < rad)&&((ForBall.getRect().top - cy-rad)>0)&&(cx+1>ForBall.getRect().left)&&(cx<ForBall.getRect().right+1)&&(ForBall!=null)) {
            if(bool){
                resetVelocity();
            }
            vy = -vy;
            score++;
            interfaceListner.scorer(score);
            if (interfaceListner!=null){
                interfaceListner.sound(3);
            }

        }

        if ((width - cx) <rad) {
            vx = -vx;
            if (interfaceListner!=null){
                interfaceListner.sound(2);
            }
        }
        else if (( (cx ) <rad)) {
            vx = -vx;
            if (interfaceListner!=null){
                interfaceListner.sound(2);
            }
        }
        else if(((ForBall.getRect().left-cx)<rad)&&(ForBall.getRect().top<cy)&&(ForBall.getRect().bottom>cy)&&(vx>0)&&((ForBall.getRect().left-cx)>0)&&(ForBall!=null)){
            vx=-vx;
            if (interfaceListner!=null){
                interfaceListner.sound(3);
            }
        }
        else if(((cx-ForBall.getRect().right)<rad)&&((cx-ForBall.getRect().right)>0)&&(ForBall.getRect().top<cy)&&(ForBall.getRect().bottom>cy)&&(vx<0)&&((cx-ForBall.getRect().right)>0)&&(ForBall!=null)){
            vx=-vx;
            if (interfaceListner!=null){
                interfaceListner.sound(3);
            }
        }
    return controller;
    }

    public int getRad() {
        return rad;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public void setForBall(pad forBall) {
        ForBall = forBall;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setListner(interfaceListner il){
          this.interfaceListner=il;
    }
}
