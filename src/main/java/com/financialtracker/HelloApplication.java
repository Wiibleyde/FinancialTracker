package com.financialtracker;

import com.financialtracker.db.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
    }

    @Override
    public void start(Stage stage) throws IOException {
        if(!Database.isOK()) {
            System.out.println("Database is not OK");
            System.exit(1);
        }
        HelloApplication.setPrimaryStage(stage);
        HelloApplication.changeScene("dashboard-view.fxml");
        stage.setTitle("Finance Tracker");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("/com/financialtracker/favicon.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}