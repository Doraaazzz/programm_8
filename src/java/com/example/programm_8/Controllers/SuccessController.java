package com.example.programm_8.Controllers;

import com.example.programm_8.App;
import com.example.programm_8.Utility.Data;
import com.example.programm_8.Utility.TableRows;
import com.example.programm_8.exceptions.ArgumentException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class SuccessController {
    private LinkedList<String> users = new LinkedList<>();
    private LinkedList<Color> colors = new LinkedList<>();

    public void next() {
        try {
            Data.canvasStage = new Stage();
            canvasInit();
        } catch (Exception e) {
            Data.message = e.getMessage();
            Data.primaryStage.setTitle("Error");
            Data.primaryStage.setScene(Data.exceptionScene);
        }
        Data.primaryStage.setTitle("User: " + Data.user.getUsername() + ": Menu.");
        Data.primaryStage.setScene(Data.menuScene);
    }

    private void canvasInit() throws ArgumentException, FileNotFoundException {

//        LinkedList<TableRows> data = new LinkedList<>();
//        if(Data.rows==null){
//            data = readCollectionData();
//        } else {
//            data = Data.rows;
//        }

        LinkedList<TableRows> data = null;
        try {
            data = readCollectionData();
        } catch (ArgumentException e) {
            throw new RuntimeException(e);
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/com/example/programm_8/images/corgi.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Pane layout = new Pane();
        Group group = new Group(layout);

        data.forEach(tableRows -> {
            if (!users.contains(tableRows.getS13())) {
                users.add(tableRows.getS13());
            }
        });
        for (int i = 0; i < users.size(); i++) {
            colors.add(Color.rgb(randomRGB(), randomRGB(), randomRGB()));
        }
        Image corgi = new Image(fis, 150, 100, true, true);


        data.forEach(tableRows -> {

            Canvas canvas = new Canvas(150.00f, 100.00f);
            GraphicsContext graphics_context =
                    canvas.getGraphicsContext2D();
            double coordinate_x = Long.parseLong(tableRows.getS7());
            double coordinate_y = Double.parseDouble(tableRows.getS8());
            Color color = colors.get(users.indexOf(tableRows.getS13()));


            graphics_context.setFill(Color.BLACK);
            graphics_context.fillRect(0, 0, 150, 100);

            // set fill for rectangle
            graphics_context.setFill(color);
            graphics_context.fillRect(10, 10, 130, 80);
            graphics_context.drawImage(corgi, 10, 0);
            Tooltip tip = new Tooltip();
            tip.setText(
                    "id: " + tableRows.getS0() + "\n" +
                            "movie name: " + tableRows.getS1() + "\n" +
                            "creation date: " + tableRows.getS2() + "\n" +
                            "oscars count: " + tableRows.getS3() + "\n" +
                            "golden palm count: " + tableRows.getS4() + "\n" +
                            "genre: " + tableRows.getS5() + "\n" +
                            "rating: " + tableRows.getS6() + "\n" +
                            "coordinate x: " + tableRows.getS7() + "\n" +
                            "coordinate y: " + tableRows.getS8() + "\n" +
                            "operator:\n" +
                            "name: " + tableRows.getS9() + "\n" +
                            "height: " + tableRows.getS10() + "\n" +
                            "eye color: " + tableRows.getS11() + "\n" +
                            "hair color: " + tableRows.getS12() + "\n" +
                            "movie owner: " + tableRows.getS13());
            Button baton = new Button();
            baton.setGraphic(canvas);
            baton.setLayoutX(coordinate_x);
            baton.setLayoutY(coordinate_y);
            baton.setTooltip(tip);
            EventHandler<ActionEvent> update = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (tableRows.getS13().equals(Data.user.getUsername())) {
                        Data.updatableObject = tableRows;
                        try {
                            Data.updaterScene = new Scene(new FXMLLoader(App.class.getResource("view/updater.fxml")).load(), 300, 200);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Data.canvasStage.setTitle("Отредактируйте поля.");
                        Data.canvasStage.setScene(Data.updaterScene);
                    } else {
                        try {
                            errorWindowOpen();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            baton.setOnAction(update);
            group.getChildren().add(baton);


        });

//        Button back = new Button("BACK");
//
//        EventHandler<ActionEvent> event = new EventHandler<>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                Data.canvasStage.close();
//            }
//
//        };

//        back.setOnAction(event);

        Data.canvasScene = new Scene(group, 600, 400);

    }

    private LinkedList<TableRows> readCollectionData() throws ArgumentException {
        String data = Data.commandManager.managerWork("getTable");
        LinkedList<TableRows> rows = new LinkedList<>();
        String[] strs = new String[14];
        Scanner scanner = new Scanner(data);
        scanner.useDelimiter(System.getProperty("line.separator"));
        boolean over = false;
        while (!over) {
            try {
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

                rows.add(new TableRows(strs));
            } catch (Exception ing) {
                over = true;
            }
        }
        return rows;
    }

    private int randomRGB() {
        return (int) (Math.random() * 255);
    }

    private void errorWindowOpen() throws IOException {
        Data.errorStage = new Stage();
        Data.errorStage.setTitle("Error");
        Data.errorStage.setScene(Data.accessScene);
        Data.errorStage.initModality(Modality.WINDOW_MODAL);
        Data.errorStage.show();
    }
}
