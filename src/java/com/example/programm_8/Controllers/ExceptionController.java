package com.example.programm_8.Controllers;

import com.example.programm_8.Utility.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ExceptionController {

    @FXML
    public Label sms;

    @FXML
    public void soutError(){
        sms.setText(Data.messageOut());
    }

    public void loginAgain(){
        Stage stage = Data.primaryStage;
        stage.setTitle("Hello, dear friend!");
        stage.setScene(Data.loginScene);
        stage.show();
    }

    public void exit(){
        Data.primaryStage.close();
    }

    // Изменение темы приложения

    @FXML
    private ImageView imgMode;

    @FXML
    private Pane parent;

    @FXML
    private Label text;

    private boolean islightmode = false;

    public void changeMode(ActionEvent event) throws IOException {
        islightmode = !islightmode;

        if (islightmode){
            setDarkMode();
        }
        else{
            setLiteMode();
        }

        if (Data.modeFlag != 1){
            Data.modeFlag = 1;

            Data.connectController.changeMode(event);
            Data.authorizationController.changeMode(event);

            Data.modeFlag = 0;
        }
    }

    private void setLiteMode(){
        parent.getStylesheets().remove("DarkMode.css");
        parent.getStylesheets().add("LightMode.css");
        Image image = new Image("LightMode.png");
        imgMode.setImage(image);
        sms.setTextFill(Color.BLACK);
        text.setTextFill(Color.BLACK);
    }

    private void setDarkMode(){
        parent.getStylesheets().remove("LightMode.css");
        parent.getStylesheets().add("DarkMode.css");
        Image image = new Image("DarkMode.png");
        imgMode.setImage(image);
        sms.setTextFill(Color.WHITE);
        text.setTextFill(Color.WHITE);
    }

}