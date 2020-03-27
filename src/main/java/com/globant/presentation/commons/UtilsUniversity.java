package com.globant.presentation.commons;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class perform utilities for all classes
 */
public class UtilsUniversity {

    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * Allows to move the window
     * @param root default root
     * @param stage default stage
     */
    public void enableMoveWindow(Parent root, Stage stage) {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    /**
     * Manage the logic to close the window
     * @param event default action event
     */
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
