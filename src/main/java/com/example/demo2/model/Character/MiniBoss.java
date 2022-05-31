package com.example.demo2.model.Character;

import com.example.demo2.App;

public class MiniBoss extends CharacterGame {
    public MiniBoss(int x, int y, int vx, int vy) {
        super(x, y, vx, vy,-10,0,1,true);
        this.width = 70;
        this.height = 70;
        this.imageView = App.loadImage("miniBoss/1",width,height);



    }

    @Override
    public boolean update() {
        updatePos();
        setPosProgress();
        return isDied();
    }
}
