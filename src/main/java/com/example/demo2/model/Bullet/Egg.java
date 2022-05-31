package com.example.demo2.model.Bullet;

import com.example.demo2.App;

public class Egg extends AbstractBullet{
    public Egg(int x, int y, int vx, int vy) {
        super(x, y, vx, vy,2);
        this.width = 120;
        this.height = 120;
        this.imageView = App.loadImage("Egg",width,height);
    }

}
