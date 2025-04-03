package com.financialtracker;

import com.financialtracker.db.ExpenseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddLineView {
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

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addLine() {
        Line newLine = new Line();
        newLine.setPeriod(periodField.getText());
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
