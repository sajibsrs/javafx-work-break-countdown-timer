package com.sajibsrs.tracker.model;

/**
 * Created by sajibsrs on 15-Nov-15.
 */
public enum UserActionType {
    FOCUS(30*60, "Focus time"),
    BREAK(5*60, "Break time");

    private int mTotalSeconds;
    private String mDisplayName;

    UserActionType(int totalSeconds, String displayName){
        mTotalSeconds = totalSeconds;
        mDisplayName = displayName;
    }

    public int getTotalSeconds(){
        return mTotalSeconds;
    }

    public String getDisplayName(){
        return mDisplayName;
    }
}
