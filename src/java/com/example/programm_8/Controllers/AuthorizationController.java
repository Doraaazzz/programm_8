package com.example.programm_8.Controllers;

import com.example.programm_8.Data.User;
import com.example.programm_8.Server.App;
import com.example.programm_8.Utility.Data;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthorizationController {

    @FXML
    private TextField log;
    @FXML
    private PasswordField pass;

    @FXML
    public void createRegistration(){
        if(!(log ==null) && !log.getText().isEmpty() && !(pass==null) && !pass.getText().isEmpty()){

            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-224");
            } catch (NoSuchAlgorithmException e) {
                //pass
            }
            byte[] hashedPass = md.digest(pass.getText().getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, hashedPass);
            String password = no.toString(16);
            while (password.length() < 32) {
                password = "0" + password;
            }
            Data.user = new User(log.getText(), password, true);
            Data.primaryStage.setTitle("Connect");
            Data.primaryStage.setScene(Data.connectScene);
        }
    }

    @FXML
    public void createUserLogin(){
        if(!(log ==null) && !log.getText().isEmpty() && !(pass==null) && !pass.getText().isEmpty()){
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-224");
            } catch (NoSuchAlgorithmException e) {
                //pass
            }
            byte[] hashedPass = md.digest(pass.getText().getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, hashedPass);
            String password = no.toString(16);
            while (password.length() < 32) {
                password = "0" + password;
            }
            Data.user = new User(log.getText(), password, false);
            Data.primaryStage.setTitle("Connect");
            Data.primaryStage.setScene(Data.connectScene);
        }
    }

    // Изменение темы приложения
    @FXML
    private Button btnMode;

    @FXML
    private ImageView imgMode;

    @FXML
    private Pane parent;

    @FXML
    private ImageView loginImg;

    @FXML
    private ImageView passwdImg;

    @FXML
    private Button signInMode;

    @FXML
    private Button signUpMode;

    @FXML
    private Label backgroundText1;

    @FXML
    private Label backgroundText2;

    public boolean islightmode = false;

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
            Data.exceptionController.changeMode(event);

            Data.modeFlag = 0;
        }
    }

    private void setLiteMode() throws IOException {

        parent.getStylesheets().remove("DarkMode.css");
        parent.getStylesheets().add("LightMode.css");

        Image image = new Image("LightMode.png");
        Image image1 = new Image("LoginLight.png");
        Image image2 = new Image("passwdLight.png");

        imgMode.setImage(image);
        loginImg.setImage(image1);
        passwdImg.setImage(image2);

        backgroundText1.setTextFill(Color.BLACK);
        backgroundText2.setTextFill(Color.BLACK);

    }

    private void setDarkMode(){
        parent.getStylesheets().remove("LightMode.css");
        parent.getStylesheets().add("DarkMode.css");

        Image image = new Image("DarkMode.png");
        Image image1 = new Image("LoginDark.png");
        Image image2 = new Image("passwdDark.png");

        imgMode.setImage(image);
        loginImg.setImage(image1);
        passwdImg.setImage(image2);

        backgroundText1.setTextFill(Color.WHITE);
        backgroundText2.setTextFill(Color.WHITE);
    }

    // Просмотр замаскированного пароля

    @FXML
    private TextField passView;

    private boolean isvisible = true;

    public void changeVisibility(ActionEvent event){
        isvisible = !isvisible;

        if (isvisible){
            pass.setText(passView.getText());
            passView.setVisible(false);
            passView.setText(pass.getText());
        }
        else {
            passView.setText(pass.getText());
            passView.setVisible(true);
            pass.setText(passView.getText());
        }
    }
}