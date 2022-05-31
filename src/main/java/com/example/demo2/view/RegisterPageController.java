package com.example.demo2.view;

import com.example.demo2.App;
import com.example.demo2.Controller.UserController;
import com.example.demo2.MenuEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterPageController {

    @FXML
    private Label response;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void register() throws NullPointerException{
        AllResponse registerResponse = UserController.register(username.getText(),password.getText());
        if (registerResponse == AllResponse.RegisterSuccessful) {
            App.changeMenu(MenuEnum.MainMenuPage);
        } else {
            response.setVisible(true);
            response.setText(registerResponse.message);
        }
    }

    public void exit() {
        App.changeMenu(MenuEnum.WelcomePage);
    }

}


