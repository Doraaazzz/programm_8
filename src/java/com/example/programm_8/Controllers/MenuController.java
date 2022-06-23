package com.example.programm_8.Controllers;

import com.example.programm_8.Utility.Data;
import com.example.programm_8.exceptions.ArgumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private final ObservableList<String> list = FXCollections.observableArrayList("add", "clear", "execute_script", "head", "history", "info", "print_ascending",
            "print_field_descending_mpaa_rating", "remove_all_by_golden_palm_count", "remove_by_id", "remove_first",
            "show", "update", "help");

    @FXML
    private ChoiceBox commandChoice;
    @FXML
    private TextField argument;
    @FXML
    private TextArea result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commandChoice.setItems(list);
        commandChoice.setValue(list.get(0));
    }

    @FXML
    public void run() {
        String command = (String) commandChoice.getValue();
       if (!(argument == null) && !(argument.getText().isEmpty())) {
            command += " " + argument.getText();
        }
        try {
            result.setText(Data.commandManager.managerWork(command));
        } catch (ArgumentException e){
            result.setText(e.getMessage());
        }
    }

    @FXML
    public void exit() throws Exception {
        Data.commandManager.managerWork("exit");
        Data.primaryStage.close();
    }

    @FXML
    public void openTable() {
        Data.primaryStage.setTitle(Data.user.getUsername() + ": movies table");
        Data.primaryStage.setScene(Data.tableScene);
    }

    @FXML
    public void openAsker() {
        Data.primaryStage.setTitle(Data.user.getUsername() + ": Enter data");
        Data.primaryStage.setScene(Data.askerScene);
    }
    @FXML
    public void canvas(){
        Data.canvasStage.setTitle("User: " + Data.user.getUsername() + ". Page: Canvas.");
        Data.canvasStage.setScene(Data.canvasScene);
        Data.canvasStage.show();
    }

}
