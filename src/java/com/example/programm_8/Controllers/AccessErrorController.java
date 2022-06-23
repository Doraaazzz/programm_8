package com.example.programm_8.Controllers;

import com.example.programm_8.Utility.Data;
import javafx.fxml.FXML;

public class AccessErrorController {

    @FXML
    public void back(){
        Data.errorStage.close();
    }
}
