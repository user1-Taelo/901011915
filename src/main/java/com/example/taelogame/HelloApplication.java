package com.example.taelogame;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage){
        ViewManager viewManager = new ViewManager();
        stage = viewManager.getMainStage();
        stage.setTitle("AirWarship");
        stage.show();
    }

    //used Packages and classes
    public static void main(String[] args) {
        launch();
    }
}