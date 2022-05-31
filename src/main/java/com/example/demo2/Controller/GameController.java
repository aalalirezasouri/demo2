package com.example.demo2.Controller;

import com.example.demo2.model.Bullet.AbstractBullet;
import com.example.demo2.model.Character.CharacterGame;
import com.example.demo2.model.Character.Cuphead;
import com.example.demo2.model.Character.FirstBoss;
import com.example.demo2.model.Character.MiniBoss;
import com.example.demo2.model.Game;
import com.example.demo2.model.Movable;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class GameController {

    private static GameController gameController;
    private final Game game;
    private GameController(){
        this.game = new Game();
    }

    public static GameController getInstance(){
        if (gameController == null) gameController = new GameController();
        return gameController;
    }

    public static void setSetInstanceNull() {
        gameController = null;
    }


    private boolean checkCollision(Movable character, Movable bullet){
        int dx = character.getX() - bullet.getX();
        int dy = character.getY() - bullet.getY();
        int rh  = character.getHeight() + bullet.getHeight();
        int rw  = character.getWidth()  + bullet.getWidth();

        if (dx*dx < rw*rw/4 && dy*dy < rh*rh/4)
            return true;
        return false;
    }

    public Cuphead getCuphead() {
        return game.getCuphead();
    }

    public FirstBoss getFirstBoss() {
        return game.getFirstBoss();
    }

    public ArrayList<MiniBoss> getMiniBosses() {
        return game.getMiniBosses();
    }

    public boolean update(ArrayList<KeyCode> pressedKeyCodes, ArrayList<KeyCode> realisedKeyCodes) {
        Cuphead cuphead = game.getCuphead();
        cuphead.setVx(0);
        cuphead.setVy(0);
        if (!cuphead.isDied()) {
            for (KeyCode keyCode : pressedKeyCodes) {
                if (keyCode == KeyCode.W) cuphead.setVy(-1);
                if (keyCode == KeyCode.S) cuphead.setVy(+1);
                if (keyCode == KeyCode.A) cuphead.setVx(-1);
                if (keyCode == KeyCode.D) cuphead.setVx(+1);
                if (keyCode == KeyCode.SPACE) cuphead.fire(false);
                if (keyCode == KeyCode.TAB) cuphead.changeFire();
            }

            for (KeyCode keyCode : realisedKeyCodes) {
                if (keyCode == KeyCode.SPACE) cuphead.fire(true);
                if (keyCode == KeyCode.TAB) cuphead.canChangeFire();
            }
            realisedKeyCodes.clear();
        }

        cuphead.update();
        game.getFirstBoss().update();
        game.updateMiniBosses();

        if (cuphead.isDied()){
            UserController.getLoginUser().setScore(UserController.getLoginUser().getScore() + cuphead.getHealth());
            return true;
        }

        if (getFirstBoss().isDied())
            return true;

        return false;
    }

    public void setArea(int surfaceWidth, int surfaceHeight) {
        game.getCuphead().setArea(surfaceWidth,surfaceHeight);
        game.getFirstBoss().setArea(surfaceWidth,surfaceHeight);
    }

    public Game getGame() {
        return game;
    }

    public void handleCollision() {

        FirstBoss firstBoss = getFirstBoss();
        Cuphead cuphead = getCuphead();

        handleCollisionBulletsByCharacter(cuphead,firstBoss);

        for (MiniBoss miniBoss : game.getMiniBosses())
            handleCollisionBulletsByCharacter(cuphead,miniBoss);

        handleCollisionBulletsByCharacter(firstBoss,cuphead);

        for (MiniBoss miniBoss : game.getMiniBosses())
            handleCollisionCharacterByCharacter(cuphead,miniBoss);


    }

    private void handleCollisionCharacterByCharacter(CharacterGame characterGame1, CharacterGame characterGame2) {
        if (checkCollision(characterGame1,characterGame2)){
            characterGame1.getHurt(2);
            characterGame2.getHurt(2);
        }
    }

    private void handleCollisionBulletsByCharacter(CharacterGame characterGameA, CharacterGame characterGameB) {
        for (AbstractBullet bullet : characterGameA.getBullets()) {
            if (checkCollision(characterGameB, bullet)) {
                characterGameA.getOldBullets().add(bullet);
                characterGameB.getHurt(bullet.getDestruction());
            }
        }
    }
}
