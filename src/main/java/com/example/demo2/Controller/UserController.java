package com.example.demo2.Controller;

import com.example.demo2.model.User;
import com.example.demo2.view.AllResponse;

import java.util.ArrayList;
import java.util.Collections;

public class UserController {
    private static User loginUser;
    private static final ArrayList<User> allUsers = new ArrayList<>();

    public static AllResponse login(String username, String password) {
        loginUser = null;
        for (User user : allUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                loginUser = user;
        }
        if (loginUser == null) return AllResponse.UsernameOrPasswordIsWrong;
        return AllResponse.LoginSuccessful;
    }

    public static AllResponse register(String username, String password) {
        if (username.equals("") || password.equals(""))
            return AllResponse.UsernameAndPasswordAreNecessary;
        loginUser = null;
        for (User user : allUsers) {
            if (user.getUsername().equals(username))
                return AllResponse.UsernameExists;
        }
        loginUser = new User(username,password,0,false);
        allUsers.add(loginUser);
        return AllResponse.RegisterSuccessful;
    }

    public static void loginGuest() {
        loginUser = new User("Guest","Guest",0,true);
    }

    public static AllResponse changeUsernameAndPassword(String newUsername, String newPassword) {
        if (newUsername.equals("") || newPassword.equals(""))
            return AllResponse.UsernameAndPasswordAreNecessary;
        for (User user : allUsers)
            if (user.getUsername().equals(newUsername))
                return AllResponse.UsernameExists;

        loginUser.setUsername(newUsername);
        loginUser.setPassword(newPassword);
        return AllResponse.changeSuccessful;
    }

    public static User getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(User loginUser) {
        UserController.loginUser = loginUser;
    }

    public static ArrayList<User> get10UserSortByScore() {
        Collections.sort(allUsers);
        if (allUsers.size() > 10)
            return (ArrayList<User>) allUsers.subList(0,10);
        return allUsers;
    }

    public static void removeLoginUser() {
        allUsers.remove(loginUser);
    }
}
