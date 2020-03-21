package com.globant.data.entities;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class Utils {

    private static double xOffset = 0;
    private static double yOffset = 0;

    public static void enableMoveWindow(Parent root, Stage stage){
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }


}
