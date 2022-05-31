package com.example.demo2.model.Character;

import com.example.demo2.App;
import com.example.demo2.model.Bullet.Bomb;
import com.example.demo2.model.Bullet.Bullet;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Cuphead extends CharacterGame {


    private int vBullet = 20;
    private int vBomb = 20;
    private boolean bombOrBullet = true;
    private boolean isFireChange = false;
    private int bombTime = 0;
    private int bulletTime = 0;
    private final int timeBetweenTwoBombs = 40;
    private final int timeBetweenTwoBullets = 10;
    private final ArrayList<ImageView> fireIcons = new ArrayList<>();

    public Cuphead(int x, int y, int vx, int vy) {
        super(x, y, vx, vy,10,15,10,false);

        fireIcons.add(getIcon("shmup_icon_bullet_0001",true,30,30));
        fireIcons.add(getIcon("shmup_icon_bomb_0001",false,30,30));
        this.width = 80;
        this.height = 80;
        this.imageView = App.loadImage("cuphead",width,height);

    }

    private ImageView getIcon(String name,boolean isVisible, int width, int height){
        ImageView imageView = App.loadImage(name,width,height);
        imageView.setVisible(isVisible);
        imageView.setLayoutX(width/2);
        imageView.setLayoutY(height/2);
        return imageView;
    }

    @Override
    public boolean update() {
        updatePos();
        updateBullet();
        setPosProgress();
        return isDied();
    }

    public void fire(boolean oneFire){
        if (bombOrBullet) fireBullet(oneFire);
        else fireBomb(oneFire);
    }

    private void fireBullet(boolean oneFire) {
        if (bulletTime > 0 && !oneFire){
            bulletTime--;
            return;
        }
        bulletTime = timeBetweenTwoBullets;
        Bullet bullet = new Bullet(x + width/2,y,vBullet,0);
        bullets.add(bullet);
        newBullets.add(bullet);
    }

    private void fireBomb(boolean oneFire) {
        System.out.println(oneFire);
        if (bombTime > 0 && !oneFire){
            bombTime--;
            return;
        }
        bombTime = timeBetweenTwoBombs;
        Bomb bomb = new Bomb(x,y + height/2,0,vBomb);
        bullets.add(bomb);
        newBullets.add(bomb);
    }

    public void changeFire() {
        if (isFireChange) return;
        bombOrBullet = !bombOrBullet;
        fireIcons.get(0).setVisible(!fireIcons.get(0).isVisible());
        fireIcons.get(1).setVisible(!fireIcons.get(1).isVisible());
        isFireChange = true;
    }

    public void canChangeFire() {
        isFireChange = false;
    }

    public void setArea(int surfaceWidth, int surfaceHeight) {
        this.surfaceWidth = surfaceWidth;
        this.surfaceHeight = surfaceHeight;
    }

    public ArrayList<ImageView> getFireIcons() {
        return fireIcons;
    }

}
