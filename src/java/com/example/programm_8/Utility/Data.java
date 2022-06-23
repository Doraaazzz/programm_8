package com.example.programm_8.Utility;

import com.example.programm_8.Client.Client;
import com.example.programm_8.Controllers.AuthorizationController;
import com.example.programm_8.Controllers.ConnectController;
import com.example.programm_8.Controllers.ExceptionController;
import com.example.programm_8.Data.Movie;
import com.example.programm_8.Data.User;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Data {

    public static User user;
    public static Client client;
    public static Stage primaryStage;
    public static Scene connectScene;
    public static Scene exceptionScene;
    public static Scene loginScene;
    public static Scene menuScene;
    public static Scene successScene;
    public static String message = "";
    public static CommandManager commandManager;
    public static Movie movie;
    public static Scene askerScene;
    public static Scene tableScene;
    public static Scene canvasScene;
    public static Stage canvasStage;
    public static TableRows updatableObject;
    public static Stage errorStage;
    public static Scene accessScene;
    public static Scene updaterScene;
    public static LinkedList<TableRows> rows;

    public static int modeFlag = 0;

    public static AuthorizationController authorizationController;
    public static ConnectController connectController;
    public static ExceptionController exceptionController;

    public static void addMessage(String s) {
        message += s + "\n";
    }

    public static String messageOut(){
        String m = message;
        message = "";
        return m;
    }

}