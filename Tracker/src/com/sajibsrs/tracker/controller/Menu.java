package com.sajibsrs.tracker.controller;

import com.sajibsrs.tracker.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable, ControlledScreen {

    @FXML private TextField hour_txt;
    @FXML private TextField minute_txt;
    @FXML private TextField second_txt;

    public static int timeInSeconds;


    ScreensController mController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent){
        mController = screenParent;
    }

    @FXML
    private void goToScreen1(ActionEvent event){
        mController.setScreen(Main.screen1ID);
    }

//    @FXML
//    private void goToScreen3(ActionEvent event){
//        mController.setScreen(Tracker.screen3ID);
//    }

    @FXML
    private void getCustomTime(){
        String hours = hour_txt.getText();
        String minutes = minute_txt.getText();
        String seconds = second_txt.getText();

        if (hours.isEmpty() || hours.length() > 2){
            hours = "0";
        }
        if (minutes.isEmpty() || hours.length() > 2){
            minutes = "0";
        }
        if (seconds.isEmpty() || hours.length() > 2){
            seconds = "0";
        }

        int h = Integer.parseInt(hours);
        int m = Integer.parseInt(minutes);
        int s = Integer.parseInt(seconds);

        timeInSeconds = (h*60*60)+(m*60)+s;
        //System.out.println(getTimeInSeconds());

    }
}
