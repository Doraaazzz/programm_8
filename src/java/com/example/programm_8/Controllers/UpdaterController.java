package com.example.programm_8.Controllers;

import com.example.programm_8.Data.*;
import com.example.programm_8.Utility.Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdaterController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField genre;
    @FXML
    private TextField mpaaRating;
    @FXML
    private TextField coordinates_x;
    @FXML
    private TextField coordinates_y;
    @FXML
    private TextField oscarsCount;
    @FXML
    private TextField operator_name;
    @FXML
    private TextField height;
    @FXML
    private TextField eyeColor;
    @FXML
    private TextField hairColor;
    @FXML
    private TextField goldenPalmCount;

    @FXML
    public void compileMovie() {
        Movie movie = new Movie();
        try {
            movie.setName(name.getText());
            movie.setCoordinates(new Coordinates(Long.parseLong(coordinates_x.getText()),Double.parseDouble(coordinates_y.getText())));
            movie.setCreationDate( ZonedDateTime.now());
            movie.setGenre(MovieGenre.valueOf(genre.getText()));
            movie.setMpaaRating(MpaaRating.valueOf(mpaaRating.getText()));
            movie.setOscarsCount(Integer.parseInt(oscarsCount.getText()));
            movie.setGoldenPalmCount(Long.parseLong(goldenPalmCount.getText()));
            Person operator = new Person();
            operator.setName(operator_name.getText());
            operator.setHeight(Double.parseDouble(height.getText()));
            operator.setEyeColor(Color.valueOf(eyeColor.getText()));
            operator.setHairColor(Color.valueOf(hairColor.getText()));
            movie.setOperator(operator);
            movie.setOwner(Data.user);
            Data.movie = movie;
            Data.commandManager.managerWork("update "+Data.updatableObject.getS0());
            Data.canvasStage.setTitle("User: " + Data.user.getUsername() + ": Canvas.");
            Data.canvasStage.setScene(Data.canvasScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(Data.updatableObject.getS1());
        oscarsCount.setText(Data.updatableObject.getS3());
        goldenPalmCount.setText(Data.updatableObject.getS4());
        genre.setText(Data.updatableObject.getS5());
        mpaaRating.setText(Data.updatableObject.getS6());
        coordinates_x.setText(Data.updatableObject.getS7());
        coordinates_y.setText(Data.updatableObject.getS8());
        operator_name.setText(Data.updatableObject.getS9());
        height.setText(Data.updatableObject.getS10());
        eyeColor.setText(Data.updatableObject.getS11());
        hairColor.setText(Data.updatableObject.getS12());

    }
}

