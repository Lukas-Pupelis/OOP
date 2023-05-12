package com.studentregistry;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class WindowManager {
    private static Stage mainStage;

    WindowManager(Stage mainStage, String entryFXML, String title) throws IOException {
        WindowManager.mainStage = mainStage;

        Scene mainScene = new Scene(loadFXML(entryFXML), 640, 768);
        mainStage.setTitle(title);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void newWindow(String fxml, String title, boolean resizable) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle(title);
        newStage.setScene(new Scene(loadFXML(fxml)));
        newStage.setResizable(resizable);
        mainStage.setOnHidden(e -> newStage.hide());
        newStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL resourcePath = App.class.getResource(fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resourcePath);

        try {
            return fxmlLoader.load();
        } catch (Exception e) {
            System.err.println("Failed to load FXML " + resourcePath);
            throw e;
        }
    }
}
