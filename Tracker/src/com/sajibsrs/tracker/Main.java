package com.sajibsrs.tracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage homeStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        homeStage.getIcons().add(new Image("/images/icon.png"));

        homeStage.setTitle("Tracker");
        homeStage.setScene(new Scene(root, 500, 500));
        homeStage.setResizable(false);
        homeStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
