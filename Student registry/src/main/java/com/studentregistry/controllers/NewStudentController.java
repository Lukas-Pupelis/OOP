package com.studentregistry.controllers;

import com.studentregistry.business.StudentRegistry;
import com.studentregistry.dto.NewStudentInputData;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewStudentController {
    @FXML
    TextField nameField;

    @FXML
    TextField surnameField;

    @FXML
    Spinner<Integer> courseSpinner;

    @FXML
    TextField groupField;

    @FXML
    Button cancelButton;

    @FXML
    private void closeWindow() {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void createStudent() {
        NewStudentInputData inputData = new NewStudentInputData(nameField.getText(), surnameField.getText(),
                courseSpinner.getValue(), groupField.getText());

        StudentRegistry registry = StudentRegistry.getInstance();
        registry.addStudent(inputData);
        closeWindow();
    }
}