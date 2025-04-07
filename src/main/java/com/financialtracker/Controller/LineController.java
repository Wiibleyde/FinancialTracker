package com.financialtracker.Controller;

import com.financialtracker.Model.Line;
import com.financialtracker.db.ExpenseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LineController {
    @FXML
    private TextField periodField;
    @FXML
    private TextField housingField;
    @FXML
    private TextField foodField;
    @FXML
    private TextField exitsField;
    @FXML
    private TextField transportField;
    @FXML
    private TextField travelField;
    @FXML
    private TextField taxesField;
    @FXML
    private TextField otherField;

    private HelloController mainController;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addLine() {
        Line newLine = new Line();
        String periodText = periodField.getText();
        newLine.setPeriod(LocalDate.parse(periodText, DATE_FORMAT));
        newLine.setHousing(Float.parseFloat(housingField.getText()));
        newLine.setFood(Float.parseFloat(foodField.getText()));
        newLine.setExits(Float.parseFloat(exitsField.getText()));
        newLine.setTransport(Float.parseFloat(transportField.getText()));
        newLine.setTravel(Float.parseFloat(travelField.getText()));
        newLine.setTaxes(Float.parseFloat(taxesField.getText()));
        newLine.setOther(Float.parseFloat(otherField.getText()));

        newLine.setTotal(newLine.getHousing() + newLine.getFood() + newLine.getExits() + newLine.getTransport() + newLine.getTravel() + newLine.getTaxes() + newLine.getOther());

        mainController.addLine(newLine);

        ExpenseDAO.insertExpense(newLine);

        Stage stage = (Stage) periodField.getScene().getWindow();
        stage.close();
    }
}
