package com.sajibsrs.tracker.model;

/**
 * Created by sajibsrs on 15-Nov-15.
 */
public class UserAction {

    private String mMessage;
    private int mRemainingSeconds;
    private UserActionType mType;

    public UserAction(UserActionType type, String message){
        mType = type;
        mMessage = message;
        mRemainingSeconds = type.getTotalSeconds();
    }

    public UserActionType getType(){
        return mType;
    }

    public String getMessage(){
        return mMessage;
    }

    public int getRemainingSeconds(){
        return mRemainingSeconds;
    }

    public void setRemainingSeconds(int timeInSeconds){
        mRemainingSeconds = timeInSeconds;
    }

    public void setMessage(String message){
        mMessage = message;
    }

    public void countDown(){
        mRemainingSeconds--;
    }

    @Override
    public String toString(){
        return "UserAction{" + "mType =" + mType + ", message = '" + mMessage + '\'' +
                ", mRemainingSeconds = " + mRemainingSeconds + '}';
    }

    public void save(){
        System.out.printf("Saving: %s %n", this);
    }


}
