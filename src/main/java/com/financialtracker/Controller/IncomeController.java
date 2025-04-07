package com.financialtracker.Controller;

import com.financialtracker.IncomeLine;
import com.financialtracker.db.IncomeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IncomeController {
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

    private IncomesController incomesController;

    public void setIncomesController(IncomesController incomesController) {
        this.incomesController = incomesController;
    }

    @FXML
    public void addIncome() {
        IncomeLine incomeLine = new IncomeLine();
        String periodText = periodField.getText();
        if (periodText.matches("\\d{4}-\\d{2}")) {
            periodText += "-01"; // Ajouter le jour par défaut
        }
        incomeLine.setPeriod(periodText);
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
