package com.example.app;

import Calculation.AnnuityMortgage;
import Calculation.LinearMortgage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.scene.chart.XYChart.Data;
import static javafx.scene.chart.XYChart.Series;

public class GraphPanel extends Main
{
    private final SimpleObjectProperty<LineChart> graph;
    private AnnuityMortgage annuity;
    private LinearMortgage linear;
    private int term;

    TextField sumField, yearsField, monthField, yearlyPercentageField, postponement, filterFrom, filterTo;

    public GraphPanel(TextField sumField, TextField yearsField, TextField monthField, TextField yearlyPercentageField, TextField postponement, TextField filterFrom, TextField filterTo)
    {
        this.sumField = sumField;
        this.yearsField = yearsField;
        this.monthField = monthField;
        this.yearlyPercentageField = yearlyPercentageField;
        this.postponement = postponement;
        this.filterFrom = filterFrom;
        this.filterTo = filterTo;
        graph = new SimpleObjectProperty<>(this, "graph", new LineChart(new NumberAxis(), new NumberAxis()));
    }

    private void setupPanel()
    {
        Group root = new Group();
        Stage stage = new Stage();
        root.getChildren().add(graph.get());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Graph");
        stage.show();
    }

    public void graph()
    {
        term = (parseInt(yearsField.getText()) * 12) + parseInt(monthField.getText());
        annuity = new AnnuityMortgage(parseDouble(sumField.getText()), term, parseDouble(yearlyPercentageField.getText()) / 100, parseInt(postponement.getText()));
        linear = new LinearMortgage(parseDouble(sumField.getText()), term, parseDouble(yearlyPercentageField.getText()) / 100, parseInt(postponement.getText()));
        setupGraph();
        setupPanel();
    }

    private void setupGraph()
    {
        NumberAxis month = new NumberAxis("Month number", 0, term, 1);
        NumberAxis monthlyPay = new NumberAxis("Monthly payment", 0, annuity.getMonthly() * 1.5, annuity.getMonthly() / 10);

        ObservableList<XYChart.Series<Number, Number>> seriesList = observableArrayList();
        ObservableList<Data> aMatrix = observableArrayList();
        ObservableList<Data> bMatrix = observableArrayList();

        graph.set(new LineChart(month, monthlyPay, seriesList));
        graph.get().setPrefSize(840, 480);

        seriesList.addAll(new Series("Annuity", aMatrix), new Series("Linear", bMatrix));

        for (int i = 0; i < annuity.numbers.get().size(); i++)
        {
            aMatrix.add(new Data(i + 1, annuity.numbers.get().get(i).monthly));
            bMatrix.add(new Data(i + 1, linear.numbers.get().get(i).monthly));
        }
    }
}