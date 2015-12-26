package com.sajibsrs.tracker.controller;

import com.sajibsrs.tracker.Main;
import com.sajibsrs.tracker.model.UserAction;
import com.sajibsrs.tracker.model.UserActionType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class Home implements Initializable, ControlledScreen {

    ScreensController mController;

    @FXML private VBox container;
    @FXML private TextField firstName;
    @FXML private Button play_btn;
    @FXML private Button pause_btn;

    private AudioClip mNotify;

    //private TextArea message;

    private UserAction mUserAction;
    private StringProperty mTimerText;
    private StringProperty mTaskText;
    private Timeline mTimeline;

    public Home(){
        mTimerText = new SimpleStringProperty();
        mTaskText = new SimpleStringProperty();

        setTaskText("CURRENT TASK");

        setTimerText(0);
        mNotify = new AudioClip(getClass().getResource("/sounds/notify.mp3").toExternalForm());
    }

    public String getTaskText(){
        return mTaskText.get();
    }

    public StringProperty taskTextProperty(){
        return mTaskText;
    }

    public void setTaskText(String taskText){
        mTaskText.set(taskText);
    }

    public String getTimerText(){
        return  mTimerText.get();
    }

    public StringProperty timerTextProperty(){
        return mTimerText;
    }

    public void setTimerText(String timerText){
        mTimerText.set(timerText);
    }

    public void setTimerText(int remainingSeconds){
        int hours = (remainingSeconds/60/60);
        int minutes = (remainingSeconds /60) % 60;
        int seconds = remainingSeconds % 60;

        //Show only minute and second if hour is not available
        if (hours <= 0){
            setTimerText(String.format("%02d:%02d", minutes, seconds));
        }
        else {
            setTimerText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        }
    }

    public void prepareAction(UserActionType type){

        mUserAction = new UserAction(type, "");

        // set time from the user input
        int userTime = Menu.timeInSeconds;
        if (mUserAction.getType() == UserActionType.FOCUS ){
            setTaskText("WORK IN PROGRESS");
            if (userTime > 0){
                mUserAction.setRemainingSeconds(userTime);
            }
        }
        else if (mUserAction.getType() == UserActionType.BREAK ){
            setTaskText("TIME FOR A BREAK");
        }
        setTimerText(mUserAction.getRemainingSeconds());
        mTimeline = new Timeline();
        mTimeline.setCycleCount(mUserAction.getRemainingSeconds());
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            mUserAction.countDown();
            setTimerText(mUserAction.getRemainingSeconds());
        }));

        mTimeline.setOnFinished(event -> {
            saveUserAction();
            mNotify.play();
            if (mUserAction.getType() == UserActionType.FOCUS ){
                takeBreakNotification();
            }
            prepareAction(mUserAction.getType() == UserActionType.FOCUS ?
            UserActionType.BREAK : UserActionType.FOCUS);

            play_btn.getStyleClass().remove("hidden");
            pause_btn.getStyleClass().add("hidden");
        });
    }

    private void saveUserAction(){
       // mUserAction.setMessage(message.getText);
        mUserAction.save();
    }

    private void reset(){
        if (mTimeline != null && mTimeline.getStatus() == Animation.Status.RUNNING){
            mTimeline.stop();
        }
    }

    public void playTimer(){
        if (mTimeline == null){prepareAction(UserActionType.FOCUS);}
        mTimeline.play();
        play_btn.getStyleClass().add("hidden");
        pause_btn.getStyleClass().remove("hidden");
        getTimerStatus();
    }

    public void pauseTimer(){
        mTimeline.pause();
        play_btn.getStyleClass().remove("hidden");
        pause_btn.getStyleClass().add("hidden");
        getTimerStatus();
    }

    public void handleReset(){
        if (mTimeline != null && mTimeline.getStatus() == Animation.Status.RUNNING){
            play_btn.getStyleClass().remove("hidden");
            pause_btn.getStyleClass().add("hidden");
        }
        reset();
        prepareAction(UserActionType.FOCUS);
        getTimerStatus();
    }

    //debugging purpose
    private Animation.Status getTimerStatus(){
        Animation.Status mStatus = mTimeline.getStatus();
        System.out.println(mStatus);
        return mStatus;
    }

    public void takeBreakNotification() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Get the Stage.
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/images/icon.png").toString()));
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Great! Now Take a Break");
        alert.setContentText("You have worked 30 min long. Now you should take a at least 5 minutes break to relax yourself.");

        alert.show();
        //alert.showAndWait();
        System.out.println("take a break notification");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setScreenParent(ScreensController screenParent){
        mController = screenParent;
    }

    @FXML
    private void goToScreen2(ActionEvent event){
        mController.setScreen(Main.screen2ID);
    }

}