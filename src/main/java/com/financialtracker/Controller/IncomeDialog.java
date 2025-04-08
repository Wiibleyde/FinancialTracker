package com.financialtracker.Controller;

import com.financialtracker.Model.IncomeLine;
import com.financialtracker.db.IncomeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IncomeDialog {
    @FXML
    private TextField periodField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField grantField;
    @FXML
    private TextField businessSalaryField;
    @FXML
    private TextField passiveIncomeField;
    @FXML
    private TextField otherField;

    private IncomeController incomesController;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void setIncomesController(IncomeController incomeController) {
        this.incomesController = incomeController;
    }

    @FXML
    public void addIncome() {
        IncomeLine incomeLine = new IncomeLine();
        String periodText = periodField.getText();
        incomeLine.setPeriod(String.valueOf(LocalDate.parse(periodText, DATE_FORMAT)));
        incomeLine.setSalary(Float.parseFloat(salaryField.getText()));
        incomeLine.setGrant(Float.parseFloat(grantField.getText()));
        incomeLine.setBusinessSalary(Float.parseFloat(businessSalaryField.getText()));
        incomeLine.setPassiveIncome(Float.parseFloat(passiveIncomeField.getText()));
        incomeLine.setOther(Float.parseFloat(otherField.getText()));

        incomesController.addIncome(incomeLine);

        IncomeDAO.insertIncome(incomeLine);

        Stage stage = (Stage) periodField.getScene().getWindow();
        stage.close();
    }
}
