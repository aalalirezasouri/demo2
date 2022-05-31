package com.example.demo2.view;

import com.example.demo2.App;
import com.example.demo2.Controller.UserController;
import com.example.demo2.MenuEnum;
import javafx.scene.input.MouseEvent;

public class WelcomePageController {

    public void registerMenu() {
        App.changeMenu(MenuEnum.RegisterPage);
    }

    public void loginMenu() {
        App.changeMenu(MenuEnum.LoginPage);
    }

    public void loginGuest(MouseEvent mouseEvent) {
        UserController.loginGuest();
        App.changeMenu(MenuEnum.MainMenuPage);
    }
}
