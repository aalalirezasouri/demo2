package com.example.demo2.view;

public enum AllResponse {
    LoginSuccessful("Successful",""),
    UsernameOrPasswordIsWrong("Error","username or password is wrong"),

    RegisterSuccessful("Successful",""),
    UsernameAndPasswordAreNecessary("Error","username and password are necessary"),
    UsernameExists("Error","username exists"),

    changeSuccessful("Successful",""),
    PasswordIsWrong("Error",""),
    ;


    public final String type;
    public final String message;

    AllResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
