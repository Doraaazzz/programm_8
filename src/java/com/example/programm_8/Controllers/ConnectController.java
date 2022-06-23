package com.example.programm_8.Controllers;

import com.example.programm_8.Client.Client;
import com.example.programm_8.Data.User;
import com.example.programm_8.Utility.CommandManager;
import com.example.programm_8.Utility.Data;
import com.example.programm_8.exceptions.Disconnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;


public class ConnectController {

    @FXML
    private TextField port;

    @FXML
    public void connect() {
        boolean success = false;
        try {
            while (!success) {
                if (!(port == null) && !port.getText().isEmpty()) {
                    int p = Integer.parseInt(port.getText());
                    if (!(p >= 1000 && p < 30000)) {
                        throw new RuntimeException("Неверный порт, повторите ввод.");
                    }
                        Data.client = new Client(p, Data.user);
                        Data.commandManager = new CommandManager(Data.client, Data.user);
                        success = true;
                        Data.primaryStage.setScene(Data.successScene);
                    }
                }

            } catch(IOException e){
                Data.addMessage("Ошибка подключения =(");
                Data.primaryStage.setTitle("Error");
         //       ExceptionController.sms.setText(Data.messageOut());
                Data.primaryStage.setScene(Data.exceptionScene);
            } catch(NumberFormatException e){
                Data.addMessage("Порт должен быть целым числом");
                Data.primaryStage.setTitle("Error");
                Data.primaryStage.setScene(Data.exceptionScene);
            } catch(RuntimeException e){
                Data.addMessage("Число выходит за диапазон");
                Data.primaryStage.setTitle("Error");
                Data.primaryStage.setScene(Data.exceptionScene);
            } catch(Exception e){
                Data.primaryStage.close();
            }
    }

    // Изменение темы приложения
    @FXML
    private ImageView imgMode;

    @FXML
    private Label labelMode;

    @FXML
    private Pane parent;

    @FXML
    private ImageView connectImg;

    private boolean islightmode = false;

    public void changeMode(ActionEvent event) throws IOException {
        islightmode = !islightmode;

        if (islightmode){
            setDarkMode();
        }
        else{
            setLiteMode();
        }

        //Изменение всех сцен с предотвращением рекурсии
        if (Data.modeFlag != 1){
            Data.modeFlag = 1;

            Data.exceptionController.changeMode(event);
            Data.authorizationController.changeMode(event);

            Data.modeFlag = 0;
        }
    }

    private void setLiteMode(){
        parent.getStylesheets().remove("DarkMode.css");
        parent.getStylesheets().add("LightMode.css");

        Image image = new Image("LightMode.png");
        Image portImg = new Image("portLight.png");

        connectImg.setImage(portImg);
        imgMode.setImage(image);

        labelMode.setTextFill(Color.BLACK);
    }

    private void setDarkMode(){
        parent.getStylesheets().remove("LightMode.css");
        parent.getStylesheets().add("DarkMode.css");

        Image image = new Image("DarkMode.png");
        Image portImg = new Image("portDark.png");

        connectImg.setImage(portImg);
        imgMode.setImage(image);

        labelMode.setTextFill(Color.WHITE);
    }
}