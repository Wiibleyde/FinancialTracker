package com.financialtracker.Controller;


import com.financialtracker.FinancialTrackerApp;
import javafx.fxml.FXML;

import java.io.IOException;

public class HeaderController {
    @FXML
    private void handleItem1() throws IOException {
        FinancialTrackerApp.changeScene("main-view.fxml");
    }

    @FXML
    private void handleItem2() throws IOException {
        FinancialTrackerApp.changeScene("dashboard-view.fxml");
    }

    @FXML
    private void handleItem3() throws IOException {
        FinancialTrackerApp.changeScene("incomes-view.fxml");
    }
}