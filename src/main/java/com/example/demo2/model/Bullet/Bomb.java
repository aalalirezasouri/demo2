package com.example.demo2.model.Bullet;

import com.example.demo2.App;

public class Bomb extends AbstractBullet{
    public Bomb(int x, int y, int vx, int vy) {
        super(x, y, vx, vy, 2);
        this.width = 20;
        this.height = 20;
        this.imageView = App.loadImage("bomb",width,height);
    }

}
