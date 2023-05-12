package com.studentregistry.io;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public abstract class FileLoader {
  abstract ExtensionFilter[] getExtensions();

  File file;

  FileLoader(Stage stage, String title, String initialFileName, boolean save) {
    file = showDialog(stage, title, initialFileName, save);
  }

  File showDialog(Stage stage, String title, String initialFileName, boolean save) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(title);
    fileChooser.setInitialFileName(initialFileName);
    fileChooser.getExtensionFilters().addAll(getExtensions());

    return save ? fileChooser.showSaveDialog(stage) : fileChooser.showOpenDialog(stage);
  }
}
