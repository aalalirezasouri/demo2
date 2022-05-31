package com.example.demo2.model.Character;

import com.example.demo2.App;
import com.example.demo2.model.Bullet.Egg;
import javafx.scene.control.ProgressBar;

import java.util.Random;

public class FirstBoss extends CharacterGame {


    private Random random = new Random();
    private int timeBetweenV = 30;
    private int tChangeV = 10;
    private int vBullet = 20;
    private int bulletTime = 0;
    private final int timeBetweenTwoBullets = 80;

    public FirstBoss(int x, int y, int vx, int vy) {

        super(x, y, vx, 0,10,10,20,false);
        this.width = 300;
        this.height = 300;
        this.imageView = App.loadImage("Intro-Boss/bird_mad_flap_0001",width,height);

    }
    
    @Override
    public boolean update() {
        RandomVy();
        updatePos();
        setPosProgress();
        updateBullet();
        fireBullet();
        return isDied();
    }

    public void RandomVy(){
        if (tChangeV > 0){
            tChangeV--;
            return;
        }

        vy = random.nextInt() % (vyC) + vyC;
        vy -= (int)(((float)y/surfaceHeight) * 2*vyC);
        //System.out.println(vy +"**" + (int)(((float)y/surfaceHeight) * 2*vyC));
        tChangeV = -random.nextInt() % (timeBetweenV / 2) + timeBetweenV;
    }

    private void fireBullet() {
        if (bulletTime > 0){
            bulletTime--;
            return;
        }
        bulletTime = timeBetweenTwoBullets;
        Egg egg = new Egg(x - width/2,y,-vBullet,0);
        bullets.add(egg);
        newBullets.add(egg);
    }

    public void setArea(int surfaceWidth, int surfaceHeight) {
        this.surfaceWidth = surfaceWidth;
        this.surfaceHeight = surfaceHeight;
        x = surfaceWidth - width;
        y = surfaceHeight/2 - height/2;
    }


}
