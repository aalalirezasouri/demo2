package com.example.demo2.model.Bullet;

import com.example.demo2.model.Movable;
import javafx.scene.image.ImageView;

public abstract class AbstractBullet extends Movable {

    protected int destruction;

    public AbstractBullet(int x, int y, int vx, int vy, int destruction) {
        super(x, y, vx, vy);
        this.destruction = destruction;
    }
    public ImageView getImageView() {
        return imageView;
    }

    public boolean update() {
        x += vx * dt;
        y += vy * dt;
        imageView.setX(x - width/2);
        imageView.setY(y - height/2);
        return false;
    }

    public int getDestruction() {
        return destruction;
    }
}
