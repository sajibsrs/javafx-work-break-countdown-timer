package com.sajibsrs.tracker.validator;

import javafx.scene.control.TextField;

/**
 * Created by sajibsrs on 19-Nov-15.
 */
public class NumberTextField extends TextField {

    public NumberTextField(){
    }

    @Override
    public void replaceText(int i, int i1, String string){
        if (string.matches("[0-9]") || string.isEmpty()){
            super.replaceText(i, i1, string);
        }
    }

    @Override
    public void replaceSelection(String string){
        super.replaceSelection(string);
    }
}
