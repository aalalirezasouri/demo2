package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class App extends Application {

    private static Scene scene;
    private static Stage appStage;
    public static boolean isGray;
    private static ColorAdjust grayscale = new ColorAdjust();

    public static ArrayList<ImageView> loadArrayImage(String name , int width, int height , int n) {
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Image image = new Image(App.class.getResource("images/" + name + (i+1) + ".png").toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageViews.add(imageView);
        }
        return imageViews;
    }
    public static ImageView loadImage(String name , int width, int height) {
        Image image = new Image(App.class.getResource("images/" + name + ".png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    public static ImageView loadImage(String name) {
        Image image = new Image(App.class.getResource("images/" + name + ".png").toExternalForm());
        return new ImageView(image);
    }

    public static Stage getAppStage() {
        return appStage;
    }

    @Override
    public void start(Stage stage) {
        appStage = stage;
        Parent root = loadFXML(MenuEnum.WelcomePage.fxmlName);
        if (root != null) {
            root.requestFocus();
            root.setFocusTraversable(true);
        }
        scene = new Scene(root, 1820, 800);
        appStage.setTitle("Hello!");
        appStage.setScene(scene);
        appStage.show();
        grayscale.setSaturation(-1);
        root.setEffect(grayscale);
        root.requestFocus();
    }


    public static void main(String[] args) {
        launch();
    }


    public static void changeMenu(MenuEnum menuEnum){
        Parent root = loadFXML(menuEnum.fxmlName);
        assert root != null;
        App.scene.setRoot(root);
        setGrayStyle();
        root.requestFocus();
    }

    public static void setGrayStyle(){
        if (isGray)
            grayscale.setSaturation(-1);
        else
            grayscale.setSaturation(0);
        System.out.println("aaaa");
        getAppStage().getScene().getRoot().setEffect(grayscale);
    }

    private static Parent loadFXML(String name){
        try {
            URL address = new URL(App.class.getResource("fxml/" + name + ".fxml").toString());
            return FXMLLoader.load(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Scene getScene() {
        return scene;
    }


}