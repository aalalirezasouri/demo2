package com.example.demo2.model.Bullet;

import com.example.demo2.App;
import javafx.scene.image.ImageView;

public class Bullet extends AbstractBullet {

    public Bullet(int x, int y, int vx, int vy) {
        super(x,y,vx,vy,1);
        this.width = 30;
        this.height = 20;
        this.imageView = App.loadImage("bullet",width,height);
    }

}
