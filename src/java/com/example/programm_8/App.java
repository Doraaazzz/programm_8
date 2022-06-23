package com.example.programm_8;


import com.example.programm_8.Utility.CommandManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.programm_8.Utility.Data;
import java.io.IOException;


public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/authorization.fxml"));
        Data.loginScene = new Scene(fxmlLoader.load(), 600, 400);
        Data.authorizationController = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(getClass().getResource("view/askPort.fxml"));
        Data.connectScene = new Scene(fxmlLoader.load(), 600, 400);
        Data.connectController = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(getClass().getResource("view/error.fxml"));
        Data.exceptionScene = new Scene(fxmlLoader.load(), 600, 400);
        Data.exceptionController = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(getClass().getResource("view/success.fxml"));
        Data.successScene = new Scene(fxmlLoader.load(),600,400);

        fxmlLoader = new FXMLLoader(getClass().getResource("view/menu.fxml"));
        Data.menuScene = new Scene(fxmlLoader.load(),600,400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/table.fxml"));
        Data.tableScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/asker.fxml"));
        Data.askerScene = new Scene(fxmlLoader.load(), 600, 400);
      //  fxmlLoader = new FXMLLoader(getClass().getResource("view/accessexc.fxml"));
       // Data.accessScene = new Scene(fxmlLoader.load(), 300, 200);
//        fxmlLoader = new FXMLLoader(getClass().getResource("view/updater.fxml"));
//        Data.updaterScene = new Scene(fxmlLoader.load(), 600, 400);

    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        Data.primaryStage=stage;
        login(stage);
    }

    public void login(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Hello, my dear friend!");
        stage.setScene(Data.loginScene);
        stage.show();
    }
}
