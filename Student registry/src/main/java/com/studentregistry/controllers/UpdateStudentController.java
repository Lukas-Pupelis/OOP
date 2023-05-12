package com.studentregistry.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.studentregistry.business.Student;
import com.studentregistry.dto.UpdateStudentInputData;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;

public class UpdateStudentController implements Initializable {

    private Student studentToUpdate;
    @FXML
    TextField nameField;

    @FXML
    TextField surnameField;

    @FXML
    IntegerSpinnerValueFactory courseSpinner;

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
    private void updateStudent() {
        UpdateStudentInputData inputData = new UpdateStudentInputData(nameField.getText(),
                surnameField.getText(),
                courseSpinner.getValue(),
                groupField.getText());

        studentToUpdate.update(inputData);

        closeWindow();
    }

    @Override
    public void initialize(URL x, ResourceBundle y) {
        studentToUpdate = MainController.getSelectedStudent();

        nameField.setText(studentToUpdate.getName());
        surnameField.setText(studentToUpdate.getSurname());
        courseSpinner.setValue(studentToUpdate.getCourse());
        groupField.setText(studentToUpdate.getGroup());
    }
}