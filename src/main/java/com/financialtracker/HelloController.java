package com.financialtracker;

import com.financialtracker.db.ExpenseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TableView<Line> tableView;

    private ObservableList<Line> lineData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        lineData.addAll(ExpenseDAO.getExpenses());
        tableView.setItems(lineData);
    }

    @FXML
    public void openAddLineView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-line-view.fxml"));
        Parent parent = fxmlLoader.load();

        AddLineView controller = fxmlLoader.getController();
        controller.setMainController(this);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }

    public void addLine(Line line) {
        lineData.add(line);
    }
}