package com.example.programm_8.Controllers;

import com.example.programm_8.Data.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.example.programm_8.Utility.Data;

import java.time.ZonedDateTime;
import java.util.Date;

public class InquiryController {
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
        public void compileMovie(){
            Movie movie = new Movie();
            try{
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
                Data.primaryStage.setTitle(Data.user.getUsername()+": Menu.");
                Data.primaryStage.setScene(Data.menuScene);
            } catch (Exception e){
                //exc
            }
        }




}
