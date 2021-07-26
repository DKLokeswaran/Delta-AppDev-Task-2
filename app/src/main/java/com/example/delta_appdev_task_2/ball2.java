package com.example.delta_appdev_task_2;

import java.util.Random;

public class ball2 {
    private int rad,height,width,theta;
    private double vx,vy,cx,cy;
    private boolean isPlaying;
    private int velocity;
    private Random random=new Random();
    private pad ForBall=new pad();
    private pad ForComp=new pad();
    private int score1=0,score2=0;
    private interfaceListner interfaceListner;


    public ball2( int rad, boolean isPlaying) {

        this.rad = rad;
        this.isPlaying = isPlaying;
    }
    public void setVelocity(){
        velocity=15;
            int x=random.nextInt(45)+1;
            int t=random.nextInt(2);
            theta=115+x-(85*t);
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
    public void resetVelocity2(){
        velocity+=1;
        double theta1=Math.atan(vy/vx);
        if(theta1<0){
            theta1+=Math.toRadians(180);
        }
        vx=-velocity*Math.cos(theta1);
        vy=-velocity*Math.sin(theta1);
    }
    public int started(boolean b,boolean c){
        int gamer;
        gamer=collisionDetector(b,c);
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
    public int collisionDetector(boolean bool,boolean bool1) {
        int controller=1;
        if ((height - cy) < rad) {
            vy = -vy;
            controller=3;
            score2++;
            interfaceListner.scorer(score2,1);
            if ((interfaceListner!=null)){
                if(score2==5){
                    interfaceListner.switcher(score1,bool1,0);
                    interfaceListner.sound(1);
                }
                else {
                    controller=0;
                    interfaceListner.sound(4);
                }
            }
        }

        else if (((ForBall!=null)&&(ForBall.getRect().top - cy-rad) < rad)&&((ForBall.getRect().top - cy-rad+vy)>rad)&&(cx+1>ForBall.getRect().left)&&(cx<ForBall.getRect().right+1)) {
            if(bool){
                resetVelocity();
            }
            vy = -vy;
            if (interfaceListner!=null){
                interfaceListner.sound(3);
            }
        }
        else if ((cy ) < rad) {
            vy = -vy;
            controller=3;
            score1++;
            interfaceListner.scorer(score1,0);
            if ((interfaceListner!=null)){
                if(score1==5){
                    interfaceListner.switcher(score2,bool1,1);
                    interfaceListner.sound(5);
                }
                else {
                    controller=0;
                    interfaceListner.sound(4);
                }
            }

        }
        else if (((ForComp!=null)&&(-ForComp.getRect().bottom + cy-rad) < rad)&&((-ForComp.getRect().bottom + cy-rad-vy)>rad)&&(cx+1>ForComp.getRect().left)&&(cx<ForComp.getRect().right+1)) {
            if(bool){
                resetVelocity2();
            }
            vy = -vy;
            if (interfaceListner!=null){
                interfaceListner.sound(3);
            }
        }
        else if(((ForComp!=null)&&(ForComp.getRect().left-cx)<rad)&&(ForComp.getRect().top<cy)&&(ForComp.getRect().bottom>cy)&&(vx>0)&&((ForComp.getRect().left-cx)>0)){
            vx=-vx;
            if (interfaceListner!=null){
                interfaceListner.sound(3);
            }
        }
        else if(((ForComp!=null)&&(cx-ForComp.getRect().right)<rad)&&(ForComp.getRect().top<cy)&&(ForComp.getRect().bottom>cy)&&(vx<0)&&((cx-ForComp.getRect().right)>0)){
            vx=-vx;
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
        else if(((ForBall!=null)&&(ForBall.getRect().left-cx)<rad)&&(ForBall.getRect().top<cy)&&(ForBall.getRect().bottom>cy)&&(vx>0)&&((ForBall.getRect().left-cx)>0)){
            vx=-vx;
            if (interfaceListner!=null){
                interfaceListner.sound(3);
            }
        }
        else if(((ForBall!=null)&&(cx-ForBall.getRect().right)<rad)&&((cx-ForBall.getRect().right)>0)&&(ForBall.getRect().top<cy)&&(ForBall.getRect().bottom>cy)&&(vx<0)&&((cx-ForBall.getRect().right)>0)){
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

    public void setListner(interfaceListner il){
        this.interfaceListner=il;
    }

    public void setForComp(pad forComp) {
        ForComp = forComp;
    }
    public float getcor(){
        float corx=(float) cx;
        float cory=(float) cy;
        while (((-ForComp.getRect().bottom + cory-rad) < rad)&&((-ForComp.getRect().bottom + cory-rad)>0)){
            corx+=vx;
            cory+=vy;
        }
        return corx;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
