package com.example.demo2;

public enum MenuEnum {
    LoginPage("LoginPage"),
    MainMenuPage("MainMenuPage"),
    ProfilePage("ProfilePage"),
    WelcomePage("WelcomePage"),
    Game("Game"), Scoreboard("Scoreboard"), RegisterPage("RegisterPage");

    public final String fxmlName;

    MenuEnum(String fxmlName) {
        this.fxmlName = fxmlName;
    }
}
