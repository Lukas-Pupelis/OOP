package com.studentregistry.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import com.studentregistry.WindowManager;
import com.studentregistry.business.Student;
import com.studentregistry.business.StudentRegistry;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainController implements Initializable {

    private static Student selectedStudent;
    public MenuItem tableContextUpdate;
    public MenuItem tableContextDelete;
    private StudentRegistry registry;

    static Student getSelectedStudent() {
        return selectedStudent;
    }

    @FXML
    TableView<Student> tableView;

    @FXML
    DatePicker fromDatePicker;

    @FXML
    DatePicker toDatePicker;

    @FXML
    private void openNewStudent() throws IOException {
        WindowManager.newWindow("new-student", "New student", false);
    }

    @FXML
    private void openUpdateStudent() throws IOException {
        selectedStudent = tableView.getSelectionModel().getSelectedItem();

        if (selectedStudent == null)
            return;

        WindowManager.newWindow("update-student", "Update student", false);
    }

    @FXML
    private void openUpdateStudentAttendance() throws IOException {
        selectedStudent = tableView.getSelectionModel().getSelectedItem();

        if (selectedStudent == null)
            return;

        WindowManager.newWindow("update-student-attendance", "Update student attendance", false);
    }

    @FXML
    private void openUpdateGroupAttendance() throws IOException {
        selectedStudent = tableView.getSelectionModel().getSelectedItem();

        if (selectedStudent == null)
            return;

        WindowManager.newWindow("update-group-attendance", "Update group attendance", false);
    }

    @FXML
    private void deleteStudent() {
        Student studentToDelete = tableView.getSelectionModel().getSelectedItem();
        registry.deleteStudent(studentToDelete);
    }

    @FXML
    private void exportStudentsCSV() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.exportStudentsCSV(currentStage);
    }

    @FXML
    private void exportStudentsExcel() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.exportStudentsExcel(currentStage);
    }

    @FXML
    private void importStudentsCSV() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.importStudentsCSV(currentStage);
    }

    @FXML
    private void importStudentsExcel() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.importStudentsExcel(currentStage);
    }

    @FXML
    private void exportTablePDF() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.exportTablePDF(currentStage);
    }

    @FXML
    private void resetAttendanceFilter() {
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);
        tableView.getColumns().forEach(c -> c.setVisible(true));
    }

    @FXML
    private void filterAttendance() {
        tableView.getColumns().forEach(c -> {
            LocalDate columnDate;
            try {
                columnDate = LocalDate.parse(c.getText());
            } catch (DateTimeParseException e) {
                return;
            }

            c.setVisible(dateWithinRange(columnDate));
        });
    }

    private boolean dateWithinRange(LocalDate date) {
        return !date.isBefore(fromDatePicker.getValue()) && !date.isAfter(toDatePicker.getValue());
    }

    @Override
    public void initialize(URL x, ResourceBundle y) {
        try {
            registry = new StudentRegistry(tableView);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        tableView.setItems(registry.getStudents());
    }
}
