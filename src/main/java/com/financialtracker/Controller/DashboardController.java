package com.financialtracker.Controller;

import com.financialtracker.IncomeLine;
import com.financialtracker.Line;
import com.financialtracker.db.ExpenseDAO;
import com.financialtracker.db.IncomeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {
    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart; // Changer Date en String

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private ChoiceBox<String> periodChoiceBox;

    private String currentPeriod;

    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM yy");
    private final static DateTimeFormatter FULL_DATE_FORMAT = DateTimeFormatter.ofPattern("MMMM yyyy");


    @FXML
    public void initialize() {
        LocalDate date = LocalDate.now();

        loadPieChartData();
        loadLineChartData();
        loadBarChart();

        for (int i = 0; i < 12; i++) {
            periodChoiceBox.getItems().add(date.format(FULL_DATE_FORMAT));
            date = date.minusMonths(1);
        }
        periodChoiceBox.getSelectionModel().selectFirst();
        currentPeriod = periodChoiceBox.getSelectionModel().getSelectedItem();
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

    private void loadLineChartData() {
        List<Line> expenses = ExpenseDAO.getExpenses();
        String[] categories = {"Housing", "Food", "Exits", "Transport", "Travel", "Taxes", "Other"};

        for (String category : categories) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(category);
            for (Line line : expenses) {
                switch (category) {
                    case "Housing":
                        series.getData().add(new XYChart.Data<>(line.getPeriod().toString(), line.getHousing()));
                        break;
                    case "Food":
                        series.getData().add(new XYChart.Data<>(line.getPeriod().toString(), line.getFood()));
                        break;
                    case "Exits":
                        series.getData().add(new XYChart.Data<>(line.getPeriod().toString(), line.getExits()));
                        break;
                    case "Transport":
                        series.getData().add(new XYChart.Data<>(line.getPeriod().toString(), line.getTransport()));
                        break;
                    case "Travel":
                        series.getData().add(new XYChart.Data<>(line.getPeriod().toString(), line.getTravel()));
                        break;
                    case "Taxes":
                        series.getData().add(new XYChart.Data<>(line.getPeriod().toString(), line.getTaxes()));
                        break;
                    case "Other":
                        series.getData().add(new XYChart.Data<>(line.getPeriod().toString(), line.getOther()));
                        break;
                }
            }
            lineChart.getData().add(series);
        }
        lineChart.setTitle("Expenses Over Time");
    }

    private void loadBarChart() {
        List<IncomeLine> incomeLines = IncomeDAO.getIncomes();
        List<Line> expenseLines = ExpenseDAO.getExpenses();
        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
        incomeSeries.setName("Income");
        expenseSeries.setName("Expenses");
        for (IncomeLine incomeLine : incomeLines) {
            incomeSeries.getData().add(new XYChart.Data<>(incomeLine.getPeriod().toString(), incomeLine.getTotal()));
        }
        for (Line expenseLine : expenseLines) {
            expenseSeries.getData().add(new XYChart.Data<>(expenseLine.getPeriod().toString(), expenseLine.getTotal()));
        }

        barChart.getData().add(incomeSeries);
        barChart.getData().add(expenseSeries);
        barChart.setTitle("Income vs Expenses");
        barChart.setLegendVisible(true);
    }

    public void changePeriod(ActionEvent actionEvent) {
        var periodSelected = periodChoiceBox.getSelectionModel().getSelectedItem();
        LocalDate dateSelected = LocalDate.parse("01 " + periodSelected, DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        currentPeriod = dateSelected.toString();
        loadPieChartData();
        loadLineChartData();
        loadBarChart();
    }

}
