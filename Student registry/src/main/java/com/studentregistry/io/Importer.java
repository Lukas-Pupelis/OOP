package com.studentregistry.io;

import javafx.stage.Stage;

public abstract class Importer<T> extends FileLoader {

  public abstract T[] importData();

  Importer(Stage stage, String title, String initialFileName) {
    super(stage, title, initialFileName, false);
  }
}
