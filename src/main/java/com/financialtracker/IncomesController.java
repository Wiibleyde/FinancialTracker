package com.financialtracker;

import com.financialtracker.db.IncomeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class IncomesController {
    @FXML
    private TableView<IncomeLine> tableView;

    private ObservableList<IncomeLine> incomeData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        incomeData.addAll(IncomeDAO.getIncomes());
        tableView.setItems(incomeData);
    }
}
