package com.globant.logic;

import com.globant.presentation.commons.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartApplication extends Application {

    private Utils utils = new Utils();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        primaryStage.setTitle("Automation Exam - Java");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        utils.enableMoveWindow(root, primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
