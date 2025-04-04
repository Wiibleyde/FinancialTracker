package com.financialtracker;

import com.financialtracker.db.ExpenseDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class Dashboard {
    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    public void initialize() {
        // Initialize the pie chart with data
        Line lastMonthData = ExpenseDAO.getLastMonthExpenses();

        if (lastMonthData.getTotal() == null) return;

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
    }
}
