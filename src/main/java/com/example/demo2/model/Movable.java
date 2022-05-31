package com.example.demo2.model;

import javafx.scene.image.ImageView;

public abstract class Movable{

    protected ImageView imageView;
    protected int width;
    protected int height;

    protected int x;
    protected int y;
    protected int vx;
    protected int vy;
    protected float dt = (float) 1;

    public Movable(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public abstract boolean update();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
