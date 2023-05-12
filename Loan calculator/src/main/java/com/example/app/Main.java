package com.example.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application
{
    // variable declaration
    Label loanSum, loanType, yearsLabel, monthsLabel, yearPercent, postponeLabel, filterFromLabel, filterToLabel, Title;
    Button chart, graph;
    TextField sumField, yearsField, monthsField, yearlyPercentField, filterFromField, filterToField, postponeField;
    ChoiceBox<String> choiceBox;
    TablePanel tablePanel;
    GraphPanel graphPanel;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        setupPanel();
    }

    private void setupPanel()
    {
        Title = initLabel(50, 10, "Mortgage Calculator");

        loanSum = initLabel(30, 40, "Loan amount:");
        sumField = InitTextField(30, 60, 20, 120, "0");

        yearsLabel = initLabel(30, 90, "Loan term in years:");
        yearsField = InitTextField(30, 110, 20, 120, "0");

        monthsLabel = initLabel(30, 140, "Loan term in months:");
        monthsField = InitTextField(30, 160, 20, 120, "0");

        yearPercent = initLabel(30, 190, "Interest Rate in %:");
        yearlyPercentField = InitTextField(30, 210, 20, 120, "0");

        postponeLabel = initLabel(30, 290, "Deferred payments in months:");
        postponeField = InitTextField(30, 310, 20, 120, "0");

        filterFromLabel = initLabel(30, 340, "Filter from:");
        filterFromField = InitTextField(30, 360, 20, 120, "");

        filterToLabel = initLabel(30, 390, "To:");
        filterToField = InitTextField(30, 410, 20, 120, "");

        loanType = initLabel(30, 240, "Loan type:");
        choiceBox = InitChoiceBox(30, 260);

        tablePanel = new TablePanel(sumField, yearsField, monthsField, yearlyPercentField, choiceBox, filterFromField, filterToField, postponeField);
        graphPanel = new GraphPanel(sumField, yearsField, monthsField, yearlyPercentField, postponeField, filterFromField, filterToField);

        // reacts on button click
        chart = InitButton(50, 450, "Chart");
        EventHandler<ActionEvent> tableEvent = event -> tablePanel.table();
        chart.setOnAction(tableEvent);

        // reacts on button click
        graph = InitButton(100, 450, "Graph");
        EventHandler<ActionEvent> graphEvent = event -> graphPanel.graph();
        graph.setOnAction(graphEvent);

        Group root = new Group();

        Stage mainStage = new Stage();
        mainStage.setHeight(520);
        mainStage.setWidth(230);
        mainStage.setTitle("Mortgage Calculator");

        root.getChildren().addAll(Title, loanSum, yearsLabel, monthsLabel, yearPercent, postponeLabel, filterFromLabel, filterToLabel, loanType);
        root.getChildren().addAll(chart, graph);
        root.getChildren().addAll(sumField, yearsField, monthsField, yearlyPercentField, postponeField, filterToField, filterFromField);
        root.getChildren().addAll(choiceBox);

        Scene mainScene = new Scene(root);

        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public Button InitButton(double x, double y, String text)
    {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    public TextField InitTextField(double x, double y, double height, double width, String text)
    {
        TextField field = new TextField();
        field.setLayoutX(x);
        field.setLayoutY(y);
        field.setPrefSize(width, height);
        field.setText(text);
        return field;
    }

    public Label initLabel(double x, double y, String text)
    {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    public ChoiceBox<String> InitChoiceBox(double x, double y)
    {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setLayoutX(x);
        choiceBox.setLayoutY(y);
        choiceBox.getItems().addAll("Linear", "Annuity");
        choiceBox.setValue("Annuity");
        return choiceBox;
    }
}

