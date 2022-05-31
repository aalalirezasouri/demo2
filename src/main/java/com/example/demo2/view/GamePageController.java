package com.example.demo2.view;

import com.example.demo2.App;
import com.example.demo2.Controller.GameController;
import com.example.demo2.MenuEnum;
import com.example.demo2.model.Bullet.AbstractBullet;
import com.example.demo2.model.Character.CharacterGame;
import com.example.demo2.model.Character.Cuphead;
import com.example.demo2.model.Character.FirstBoss;
import com.example.demo2.model.Character.MiniBoss;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class GamePageController {


    private static GamePageController gamePageController;

    @FXML
    private Button exitButton;
    @FXML
    private AnchorPane surface;
    private int surfaceHeight;
    private int surfaceWidth;
    private final ArrayList<KeyCode> pressedKeyCodes = new ArrayList<>();
    private final ArrayList<KeyCode> realisedKeyCodes = new ArrayList<>();
    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {

            GameController gameController = GameController.getInstance();

            handleBullets(gameController.getFirstBoss());
            handleBullets(gameController.getCuphead());

            handleMiniBosses(gameController);

            gameController.handleCollision();
            gameController.update(pressedKeyCodes,realisedKeyCodes);

        }
    };

    private void handleMiniBosses(GameController gameController) {
        gameController.getGame().updateMiniBosses();
        for (MiniBoss newMiniBoss : gameController.getGame().getNewMiniBosses()) {
            surface.getChildren().add(newMiniBoss.getImageView());
            surface.getChildren().add(newMiniBoss.getProgressBar());
            surface.getChildren().add(newMiniBoss.getHealthPercentage());
        }
        for (MiniBoss newMiniBoss : gameController.getGame().getOldMiniBosses()) {
            surface.getChildren().remove(newMiniBoss.getImageView());
            surface.getChildren().remove(newMiniBoss.getProgressBar());
            surface.getChildren().remove(newMiniBoss.getHealthPercentage());
        }
        gameController.getGame().getOldMiniBosses().clear();
        gameController.getGame().getNewMiniBosses().clear();
    }

    public static GamePageController getInstance() {
        if (gamePageController == null)
            gamePageController = new GamePageController();
        return gamePageController;
    }

    public void handleBullets(CharacterGame character){
        for (AbstractBullet newBullet : character.getNewBullets())
            surface.getChildren().add(newBullet.getImageView());

        for (AbstractBullet oldBullet : character.getOldBullets())
            surface.getChildren().remove(oldBullet.getImageView());

        character.getOldBullets().clear();
        character.getNewBullets().clear();
    }

    public void initialize(){

        surfaceWidth = (int) App.getAppStage().getWidth();
        surfaceHeight = (int) App.getAppStage().getHeight();

        GameController gameController = GameController.getInstance();
        gameController.setArea(surfaceWidth,surfaceHeight);
        Cuphead cuphead = gameController.getCuphead();
        FirstBoss firstBoss = gameController.getFirstBoss();

        surface.getChildren().add(cuphead.getImageView());
        surface.getChildren().add(cuphead.getProgressBar());
        surface.getChildren().add(cuphead.getHealthPercentage());
        surface.getChildren().addAll(cuphead.getFireIcons());

        surface.getChildren().add(firstBoss.getImageView());
        surface.getChildren().add(firstBoss.getHealthPercentage());
        surface.getChildren().add(firstBoss.getProgressBar());

        surface.setBackground(new Background(
                new BackgroundImage(
                        new Image(App.class.getResource("images/" + "birdhouse_bg_0008" + ".png").toExternalForm()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                ),
                null, null, null, null, null)
        );

        surface.setOnKeyPressed((keyEvent -> {
            if (!pressedKeyCodes.contains(keyEvent.getCode()))
                pressedKeyCodes.add(keyEvent.getCode());
        }));

        surface.setOnKeyReleased((keyEvent) -> {
            pressedKeyCodes.remove(keyEvent.getCode());
            realisedKeyCodes.add(keyEvent.getCode());
        });

        exitButton.setLayoutY(surfaceHeight - 50);
        exitButton.setLayoutX(0);
        animationTimer.start();
    }

    public void exit() {
        animationTimer.stop();
        GameController.setSetInstanceNull();
        App.changeMenu(MenuEnum.MainMenuPage);
    }
}
