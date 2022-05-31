package com.example.demo2.view;

import com.example.demo2.App;
import com.example.demo2.Controller.UserController;
import com.example.demo2.MenuEnum;
import com.example.demo2.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ScoreBoardPageController {

    @FXML
    private ScrollBar board;

    public void initialize(){
        ArrayList<User> users = UserController.get10UserSortByScore();

        for (User user : users) {
            //board.;
        }
    }

    public void exit() {
        App.changeMenu(MenuEnum.MainMenuPage);
    }
}
