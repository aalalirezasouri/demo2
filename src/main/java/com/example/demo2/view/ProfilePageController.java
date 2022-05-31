package com.example.demo2.view;

import com.example.demo2.App;
import com.example.demo2.Controller.UserController;
import com.example.demo2.MenuEnum;
import com.example.demo2.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProfilePageController {

    @FXML
    private Label response;
    @FXML
    private Label username;
    @FXML
    private TextField newUsername;
    @FXML
    private Label password;
    @FXML
    private TextField newPassword;

    public void initialize(){
        username.setText(UserController.getLoginUser().getUsername());
        password.setText(UserController.getLoginUser().getPassword());
    }

    public void exit() {
        App.changeMenu(MenuEnum.MainMenuPage);
    }

    public void changeUsernameAndPassword() {
        AllResponse profileResponse = UserController.changeUsernameAndPassword(newUsername.getText(), newPassword.getText());
        if (profileResponse != null) {
            if (profileResponse == AllResponse.changeSuccessful) {
                username.setText(UserController.getLoginUser().getUsername());
                password.setText(UserController.getLoginUser().getPassword());
                response.setText(AllResponse.changeSuccessful.message);
            } else {
                response.setText(profileResponse.message);
                response.setVisible(true);
            }
        }
    }

    public void logout() {
        UserController.setLoginUser(null);
        App.changeMenu(MenuEnum.WelcomePage);
    }

    public void removeAccount() {
        UserController.removeLoginUser();
        logout();
    }
}

