package com.example.demo2.model;

public class User  implements Comparable<User>{
    private String username;
    private String password;
    private int Score;
    private boolean isGuest;

    public User(String username, String password, int score, boolean isGuest) {
        this.username = username;
        this.password = password;
        Score = score;
        this.isGuest = isGuest;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
            return password;
    }

    public int getScore() {
        return Score;
    }

    public boolean isGuest() {
        return isGuest;
    }

    @Override
    public int compareTo(User user) {
        return this.getScore() - user.getScore();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        Score = score;
    }
}
