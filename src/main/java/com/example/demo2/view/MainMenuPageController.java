package com.example.demo2.view;

import com.example.demo2.App;
import com.example.demo2.MenuEnum;
import javafx.scene.input.MouseEvent;

public class MainMenuPageController {

    public void startNewGame(MouseEvent mouseEvent) {
        App.changeMenu(MenuEnum.Game);
    }

    public void continueGame(MouseEvent mouseEvent) {
        App.changeMenu(MenuEnum.Game);
    }

    public void profileMenu(MouseEvent mouseEvent) {
        App.changeMenu(MenuEnum.ProfilePage);
    }

    public void scoreboard(MouseEvent mouseEvent) {
        App.changeMenu(MenuEnum.Scoreboard);
    }

    public void exit() {
        App.changeMenu(MenuEnum.WelcomePage);
    }

    public void setGrayScale() {
        App.isGray = !App.isGray;
    }
}


