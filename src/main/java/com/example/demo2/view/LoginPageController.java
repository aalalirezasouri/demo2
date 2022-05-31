package com.example.demo2.view;

import com.example.demo2.App;
import com.example.demo2.Controller.UserController;
import com.example.demo2.MenuEnum;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginPageController {

    @FXML
    private Label response;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void login() {
        AllResponse loginResponse = UserController.login(username.getText(),password.getText());
        if (loginResponse == AllResponse.LoginSuccessful) {
            App.changeMenu(MenuEnum.MainMenuPage);
        } else {
            response.setVisible(true);
            response.setText(loginResponse.message);
        }
    }

    public void exit() {
        App.changeMenu(MenuEnum.WelcomePage);
    }
}

