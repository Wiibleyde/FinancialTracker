package com.financialtracker;


import javafx.fxml.FXML;

import java.io.IOException;

public class Header {
    @FXML
    private void handleItem1() throws IOException {
        HelloApplication.changeScene("main-view.fxml");
    }

    @FXML
    private void handleItem2() throws IOException {
        HelloApplication.changeScene("dashboard-view.fxml");
    }
}