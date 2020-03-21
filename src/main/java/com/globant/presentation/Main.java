package com.globant.presentation;

import com.globant.data.entities.Utils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample3.fxml"));
        primaryStage.setTitle("Automation Exam - Java");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Utils.enableMoveWindow(root, primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
