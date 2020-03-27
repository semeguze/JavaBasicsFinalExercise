package com.globant.logic;

import com.globant.presentation.commons.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

/**
 * Entry point of the application
 */
@Slf4j
public class StartApplication extends Application {

    private UtilsUniversity utils = new UtilsUniversity();

    /**
     * This method is the start point of the application after the Main
     * @param primaryStage default stage
     * @throws Exception handle exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Loading Main View");
        Parent root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));
        primaryStage.setTitle("Automation Exam - Java");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        utils.enableMoveWindow(root, primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * This method is the start point of the application
     * @param args allows to receive different arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
