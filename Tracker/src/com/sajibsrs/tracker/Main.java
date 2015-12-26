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


    @Override
    public void start(Stage primaryStage) {
        
        //Add screen controller to move to different screens
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);

        mainContainer.setScreen(Main.screen1ID);
        
        //Setup root and scene to be visible within your app
        //add screen width and height
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 500, 500);
    
        //Add application icon, title, set scene, and resizable to false
        //as we are only going to use a fixed size window
        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.setTitle("Tracker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }
    
    //launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
