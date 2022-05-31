package com.example.demo2.model;

import com.example.demo2.App;
import com.example.demo2.model.Character.Cuphead;
import com.example.demo2.model.Character.FirstBoss;
import com.example.demo2.model.Character.MiniBoss;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private final Cuphead cuphead;
    private final FirstBoss firstBoss;
    private final ArrayList<MiniBoss> miniBosses = new ArrayList<>();
    private final ArrayList<MiniBoss> oldMiniBosses = new ArrayList<>();
    private final ArrayList<MiniBoss> newMiniBosses = new ArrayList<>();
    private final int timeMiniBossesC = 20;
    private int timeMiniBosses = 0;
    private final int numberOfMiniBosses = 5;

    private final int surfaceWidth = (int) App.getAppStage().getWidth();
    private final int surfaceHeight = (int) App.getAppStage().getHeight();

    public Game(){
        cuphead = new Cuphead(100,200,0,0);
        firstBoss = new FirstBoss(500,600,0,0);
    }

    public Cuphead getCuphead() {
        return cuphead;
    }

    public FirstBoss getFirstBoss() {
        return firstBoss;
    }

    public ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }

    public ArrayList<MiniBoss> getOldMiniBosses() {
        return oldMiniBosses;
    }

    public ArrayList<MiniBoss> getNewMiniBosses() {
        return newMiniBosses;
    }

    public void updateMiniBosses() {

        for (MiniBoss miniBoss : miniBosses) {
            miniBoss.update();
            if (miniBoss.getX() < miniBoss.getWidth() || miniBoss.isDied())
                oldMiniBosses.add(miniBoss);
        }
        miniBosses.removeAll(oldMiniBosses);

        if (!miniBosses.isEmpty()) return;

        if (timeMiniBosses > 0) {
            timeMiniBosses--;
            return;
        }

        timeMiniBosses = timeMiniBossesC;

        int ymb = RandomInt(20,surfaceHeight - 120);
        MiniBoss miniBoss;
        for (int i = 0; i < numberOfMiniBosses; i++) {
            miniBoss = new MiniBoss(surfaceWidth + 100 * i, ymb,-5,0);
            miniBosses.add(miniBoss);
            newMiniBosses.add(miniBoss);
        }

    }

    private static int RandomInt(int a, int b){
        Random random = new Random();
        int d = random.nextInt();
        if (d < 0) d =-d;
        return d%(b-a)+a;
    }
}
