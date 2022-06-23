package com.example.programm_8.Controllers;


import com.example.programm_8.Commands.getTable;
import com.example.programm_8.Utility.Data;
import com.example.programm_8.Utility.TableRows;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class TableController {

    @FXML
    private TableColumn<TableRows, String> id;
    @FXML
    private TableColumn<TableRows, String> name;
    @FXML
    private TableColumn<TableRows, String> creationDate;
    @FXML
    private TableColumn<TableRows, String> oscarsCount;
    @FXML
    private TableColumn<TableRows, String> goldenPalmCount;
    @FXML
    private TableColumn<TableRows, String> genre;
    @FXML
    private TableColumn<TableRows, String> mpaaRating;
    @FXML
    private TableColumn<TableRows, String> coordinate_x;
    @FXML
    private TableColumn<TableRows, String> coordinate_y;
    @FXML
    private TableColumn<TableRows, String> operator_name;
    @FXML
    private TableColumn<TableRows, String> height;
    @FXML
    private TableColumn<TableRows, String> eyeColor;
    @FXML
    private TableColumn<TableRows, String> hairColor;
    @FXML
    private TableColumn<TableRows, String> user;

    @FXML
    private TableView<TableRows> table;

    public void getterTable() throws Exception {
        initCols();
        String raw = Data.commandManager.managerWork("getTable");
        LinkedList<TableRows> rows = new LinkedList<>();
        String[] strs = new String[14];
        Scanner scanner = new Scanner(raw);
        scanner.useDelimiter(System.getProperty("line.separator"));
        System.out.println(raw);
        while (!scanner.hasNext("")) {
            strs[0] = scanner.next();
            strs[1] = scanner.next();
            strs[2] = scanner.next();
            strs[3] = scanner.next();
            strs[4] = scanner.next();
            strs[5] = scanner.next();
            strs[6] = scanner.next();
            strs[7] = scanner.next();
            strs[8] = scanner.next();
            strs[9] = scanner.next();
            strs[10] = scanner.next();
            strs[11] = scanner.next();
            strs[12] = scanner.next();
            strs[13] = scanner.next();
            System.out.println(strs);
            rows.add(new TableRows(strs));
        }
        System.out.println(rows);
        ObservableList<TableRows> tableRows = FXCollections.observableArrayList(rows);
        table.setItems(tableRows);
    }

    private void initCols() {
        id.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS0()));
        name.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS1()));
        creationDate.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS2()));
        oscarsCount.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS3()));
        goldenPalmCount.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS4()));
        genre.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS5()));
        mpaaRating.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS6()));
        coordinate_x.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS7()));
        coordinate_y.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS8()));
        operator_name.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS9()));
        height.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS10()));
        eyeColor.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS11()));
        hairColor.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS12()));
        user.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS13()));
    }

    public void back(){
        Data.primaryStage.setTitle(Data.user.getUsername()+": Menu.");
        Data.primaryStage.setScene(Data.menuScene);
    }
}

