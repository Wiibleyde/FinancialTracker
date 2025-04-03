package com.financialtracker;

import com.financialtracker.db.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        if(!Database.isOK()) {
            System.out.println("Database is not OK");
            System.exit(1);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Finance Tracker");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/com/financialtracker/favicon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}