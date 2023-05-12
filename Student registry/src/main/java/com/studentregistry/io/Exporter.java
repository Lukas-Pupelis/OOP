package com.studentregistry.io;

import javafx.stage.Stage;

public abstract class Exporter<T> extends FileLoader {

  public abstract void export(T items);

  public Exporter(Stage stage, String title, String initialFileName) {
    super(stage, title, initialFileName, true);
  }
}
