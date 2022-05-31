package com.example.demo2.model.Character;

import com.example.demo2.model.Bullet.AbstractBullet;
import com.example.demo2.model.Movable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class CharacterGame extends Movable {


    protected int vxC;
    protected int vyC;
    protected final int healthC;
    protected int health;
    protected boolean canGoOutOfSurface;

    protected final ArrayList<AbstractBullet> bullets ;
    protected final ArrayList<AbstractBullet> newBullets ;
    protected final ArrayList<AbstractBullet> oldBullets ;

    protected int surfaceWidth;
    protected int surfaceHeight;

    protected Label healthPercentage;
    protected ProgressBar progressBar;


    public CharacterGame(int x, int y, int vx, int vy, int vxC, int vyC, int health, boolean canGoOutOfSurface) {
        super(x, y, vx, vy);
        this.vxC = vxC;
        this.vyC = vyC;
        this.healthC = health;
        this.health  = healthC;
        this.canGoOutOfSurface = canGoOutOfSurface;

        this.bullets = new ArrayList<>();
        this.newBullets = new ArrayList<>();
        this.oldBullets = new ArrayList<>();

        this.healthPercentage = new Label("100%");
        this.progressBar = new ProgressBar(1);
        progressBar.setPrefSize(width/1.5, 15);

        setPosProgress();
    }

    public boolean isDied(){
        if (health < 0) health = 0;
        return health == 0;
    }


    public void updatePos() {
        if (canGoOutOfSurface
                || (width/2 < x || vx > 0)
                && (x < surfaceWidth - width/2 || vx < 0))
            x += vx*dt;

        if (canGoOutOfSurface
                || (height/2 < y || vy > 0)
                && (y < surfaceHeight - height || vy < 0))
            y += vy*dt;
        imageView.setX(x - (width >> 1));
        imageView.setY(y - (height >> 1));
    }

    protected void updateBullet(){
        for (AbstractBullet bullet : bullets) {
            bullet.update();
            if (!(( 0 < bullet.getX() && bullet.getX() < surfaceWidth)
                    && (0 < bullet.getY() && bullet.getY() < surfaceWidth))){
                oldBullets.add(bullet);
            }
        }
        bullets.removeAll(oldBullets);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ArrayList<AbstractBullet> getNewBullets() {
        return newBullets;
    }

    public ArrayList<AbstractBullet> getOldBullets() {
        return oldBullets;
    }

    public ArrayList<AbstractBullet> getBullets() {
        return bullets;
    }

    public void getHurt(int destruction) {
        health -= destruction;
        if (health < 0) health = 0;
        progressBar.setProgress((float)health/healthC);
        int d = (int)(((float)health/healthC)*100);
        healthPercentage.setText(d +"%");
    }

    public void setVx(int i) {
        vx = i * vxC;
    }

    public void setVy(int i) {
        vy = i * vyC;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
    public Label getHealthPercentage() {
        return healthPercentage;
    }

    public void setPosProgress(){
        progressBar.setLayoutX(x - width/4.0);
        progressBar.setLayoutY(y - height/2.0 - 20);
        healthPercentage.setLayoutX(x - width/4.0);
        healthPercentage.setLayoutY(y - height/2.0 - 45);
    }

    public int getHealth() {
        return health;
    }
}
