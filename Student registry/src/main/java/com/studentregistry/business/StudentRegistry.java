package com.studentregistry.business;

import com.studentregistry.dto.NewStudentInputData;
import com.studentregistry.io.Exportable;
import com.studentregistry.io.StudentCSVExporter;
import com.studentregistry.io.StudentCSVImporter;
import com.studentregistry.io.StudentExcelExporter;
import com.studentregistry.io.StudentExcelImporter;
import com.studentregistry.io.TablePDFExporter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class StudentRegistry {
  private static StudentRegistry Instance;
  private final ObservableList<Student> students = FXCollections.observableArrayList();
  private final TableView<Student> mainTable;

  public StudentRegistry(TableView<Student> mainTable) throws Exception {
    if (Instance == null)
      Instance = this;
    else
      throw new Exception("StudentRegistry already initialized.");

    this.mainTable = mainTable;
  }

  public static StudentRegistry getInstance() {
    return Instance;
  }

  public void addStudent(NewStudentInputData inputData) {
    students.add(new Student(inputData, mainTable));
  }

  public void deleteStudent(Student studentToRemove) {
    students.remove(studentToRemove);
  }

  public void exportStudentsCSV(Stage stage) {
    new StudentCSVExporter(stage).export(students.toArray(Exportable[]::new));
  }

  public void exportStudentsExcel(Stage stage) {
    new StudentExcelExporter(stage).export(students.toArray(Exportable[]::new));
  }

  public void importStudentsCSV(Stage stage) {
    NewStudentInputData[] inputDataArray = new StudentCSVImporter(stage).importData();
    for (NewStudentInputData inputData : inputDataArray) {
      addStudent(inputData);
    }
  }

  public void importStudentsExcel(Stage stage) {
    NewStudentInputData[] inputDataArray = new StudentExcelImporter(stage).importData();
    for (NewStudentInputData inputData : inputDataArray) {
      addStudent(inputData);
    }
  }

  public void exportTablePDF(Stage stage) {
    new TablePDFExporter(stage).export(mainTable);
  }

  public ObservableList<Student> getStudents() {
    return students;
  }
}
