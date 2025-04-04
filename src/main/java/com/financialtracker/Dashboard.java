package com.financialtracker;

import com.financialtracker.db.ExpenseDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Dashboard {
    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private ChoiceBox<String> periodChoiceBox;

    private String currentPeriod;


    @FXML
    public void initialize() {
        // Initialize the pie chart with data
        // Initialize the line chart with data
        List<Line> expenses = ExpenseDAO.getExpenses();
        String[] categories = {"Housing", "Food", "Exits", "Transport", "Travel", "Taxes", "Other"};

        for (String category : categories) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(category);
            for (Line line : expenses) {
                switch (category) {
                    case "Housing":
                        series.getData().add(new XYChart.Data<>(line.getPeriod(), line.getHousing()));
                        break;
                    case "Food":
                        series.getData().add(new XYChart.Data<>(line.getPeriod(), line.getFood()));
                        break;
                    case "Exits":
                        series.getData().add(new XYChart.Data<>(line.getPeriod(), line.getExits()));
                        break;
                    case "Transport":
                        series.getData().add(new XYChart.Data<>(line.getPeriod(), line.getTransport()));
                        break;
                    case "Travel":
                        series.getData().add(new XYChart.Data<>(line.getPeriod(), line.getTravel()));
                        break;
                    case "Taxes":
                        series.getData().add(new XYChart.Data<>(line.getPeriod(), line.getTaxes()));
                        break;
                    case "Other":
                        series.getData().add(new XYChart.Data<>(line.getPeriod(), line.getOther()));
                        break;
                }
            }
            lineChart.getData().add(series);
        }
        lineChart.setTitle("Expenses Over Time");

        loadPeriodChoiceBox();
        setupChoiceBoxListener();
    }

    private void loadPieChartData() {
        Line lastMonthData = ExpenseDAO.getSelectedPeriodData(currentPeriod);

        if (lastMonthData.getTotal() == null) return;

        pieChart.getData().clear();

        pieChart.getData().add(new PieChart.Data("Housing", lastMonthData.getHousing()));
        pieChart.getData().add(new PieChart.Data("Food", lastMonthData.getFood()));
        pieChart.getData().add(new PieChart.Data("Exits", lastMonthData.getExits()));
        pieChart.getData().add(new PieChart.Data("Transport", lastMonthData.getTransport()));
        pieChart.getData().add(new PieChart.Data("Travel", lastMonthData.getTravel()));
        pieChart.getData().add(new PieChart.Data("Taxes", lastMonthData.getTaxes()));
        pieChart.getData().add(new PieChart.Data("Other", lastMonthData.getOther()));

        pieChart.setTitle("Expense Distribution for " + lastMonthData.getPeriod());
        pieChart.setLegendVisible(true);
        pieChart.setLabelsVisible(true);
    }

    private void loadPeriodChoiceBox() {
        List<String> periods = getLast12Periods();
        periodChoiceBox.getItems().addAll(periods);
        periodChoiceBox.getSelectionModel().selectFirst();
        currentPeriod = periodChoiceBox.getValue();
        loadPieChartData();
    }

    private List<String> getLast12Periods() {
        List<String> periods = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");

        for (int i = 0; i < 12; i++) {
            periods.add(currentDate.minusMonths(i).format(formatter));
        }

        return periods;
    }

    private void setupChoiceBoxListener() {
        periodChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentPeriod = newValue;
            loadPieChartData();
        });
    }

}
