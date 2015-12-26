package com.sajibsrs.tracker;

import com.sajibsrs.tracker.controller.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static String screen1ID = "home";
    public static String screen1File = "/fxml/home.fxml";
    public static String screen2ID = "menu";
    public static String screen2File = "/fxml/menu.fxml";
//    public static String screen3ID = "timeline";
//    public static String screen3File = "timeline.fxml";


    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        //mainContainer.loadScreen(Tracker.screen3ID, Tracker.screen3File);

        mainContainer.setScreen(Main.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 500, 500);

        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.setTitle("Tracker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }
//    @Override
//    public void start(Stage homeStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
//        homeStage.getIcons().add(new Image("/images/icon.png"));
//        homeStage.setTitle("Tracker");
//        homeStage.setScene(new Scene(root, 500, 500));
//        homeStage.setResizable(false);
//        homeStage.show();
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
