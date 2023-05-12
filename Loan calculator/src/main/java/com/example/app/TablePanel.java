package com.example.app;

import Calculation.AnnuityMortgage;
import Calculation.DataEntity;
import Calculation.LinearMortgage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.util.stream.IntStream.rangeClosed;

public class TablePanel extends Main
{

    // declare variables
    TextArea area;
    Button txtButton;
    TextField sumField, yearsField, monthField, yearlyPercentField, postponement, filterFrom, filterTo;
    SimpleObjectProperty<ChoiceBox> choiceBox = new SimpleObjectProperty<>(this, "choiceBox");
    StringBuilder info = new StringBuilder();
    int term, var1, var2;
    AnnuityMortgage annuity;
    LinearMortgage linear;

    public TablePanel(TextField sumField, TextField yearsField, TextField monthField, TextField yearlyPercentField,
                      ChoiceBox choiceBox, TextField filterFrom, TextField filterTo, TextField postponement)
    {
        this.sumField = sumField;
        this.yearsField = yearsField;
        this.monthField = monthField;
        this.yearlyPercentField = yearlyPercentField;
        this.choiceBox.set(choiceBox);
        this.postponement = postponement;
        this.filterFrom = filterFrom;
        this.filterTo = filterTo;
    }

    public void table()
    {
        txtButton = setupButton(460 , 30, "Print into text file");
        txtButton.setWrapText(true);
        setupArea();
        setupPanel();
    }

    public void setupArea()
    {
        info.replace(0, info.length(), "");
        term = (parseInt(yearsField.getText()) * 12) + parseInt(monthField.getText());
        annuity = new AnnuityMortgage(parseDouble(sumField.getText()), term, parseDouble(yearlyPercentField.getText()) / 100, parseInt(postponement.getText()));
        linear = new LinearMortgage(parseDouble(sumField.getText()), term, parseDouble(yearlyPercentField.getText()) / 100, parseInt(postponement.getText()));

        // if filter to is empty, then set it to the last number
        if (Objects.equals(filterFrom.getText(), ""))
        {
            var1 = 1;
        }
        else
        {
            var1 = parseInt(filterFrom.getText());
        }

        // if choice box equals to Annuity, then print the chart
        if (choiceBox.get().getSelectionModel().getSelectedItem() == "Annuity")
        {
            if (Objects.equals(filterTo.getText(), ""))
            {
                var2 = annuity.numbers.get().size();
            }

            else
            {
                var2 = parseInt(filterTo.getText());
            }
            putInformation(annuity.numbers.get(), var1, var2);
        }
        else if (choiceBox.get().getSelectionModel().getSelectedItem() == "Linear")
        {
            if (Objects.equals(filterTo.getText(), ""))
            {
                var2 = linear.numbers.get().size();
            }
            else
            {
                var2 = parseInt(filterTo.getText());
            }

            putInformation(linear.numbers.get(), var1, var2);
        }

        area = new TextArea();
        area.setLayoutX(30);
        area.setLayoutY(30);
        area.setPrefSize(420, 350);

        area.setText(info.toString());
    }

    protected void writeToFile()
    {
        try
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                writer.write(info.toString());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void setupPanel()
    {
        Pane root = new Pane();
        Stage stage = new Stage();
        root.setPrefSize(600, 400);
        root.getChildren().addAll(area, txtButton);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chart");

        stage.show();
    }

    private void putInformation(ArrayList<DataEntity> numbers, int temp1, int temp2)
    {
        info.append(rangeClosed(temp1, temp2).mapToObj(i -> i + ". Month: " + String.format("%.1f", numbers.get(i - 1).monthly) + ", Interest: "
                + String.format("%.1f", numbers.get(i - 1).interest) + ", Principal: " + String.format("%.1f", numbers.get(i - 1).credit) +
                ", Loan amount: " + String.format("%.2f", numbers.get(i - 1).reminder) + "\n").collect(Collectors.joining()));
    }

    public Button setupButton(double x, double y, String s)
    {
        Button txtButton = super.InitButton(x, y, s);
        txtButton.setOnAction(event -> writeToFile());
        return txtButton;
    }

     
}
