package com.financialtracker.Controller;

import com.financialtracker.Model.IncomeLine;
import com.financialtracker.db.IncomeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class IncomeController {
    @FXML
    private TableView<IncomeLine> tableView;

    private ObservableList<IncomeLine> incomeData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        incomeData.addAll(IncomeDAO.getIncomes());
        tableView.setItems(incomeData);
    }

    public void addIncome(IncomeLine incomeLine) {
        incomeData.add(incomeLine);
    }

    @FXML
    public void openAddIncomeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/financialtracker/add-income-view.fxml"));
        Parent parent = fxmlLoader.load();

        IncomeDialog controller = fxmlLoader.getController();
        controller.setIncomesController(this);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
}
