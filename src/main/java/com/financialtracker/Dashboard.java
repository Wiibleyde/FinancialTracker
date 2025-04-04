package com.financialtracker;

import com.financialtracker.db.ExpenseDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;

import java.util.List;

public class Dashboard {
    @FXML
    private PieChart pieChart;


    @FXML
    public void initialize() {
        // Initialize the pie chart with data
        Line lastMonthData = ExpenseDAO.getLastMonthExpenses();

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

    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }

    public PieChart getPieChart() {
        return pieChart;
    }
}
