package com.studentregistry;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            new WindowManager(stage, "main", "Student registry");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}