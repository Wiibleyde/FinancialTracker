module com.financialtracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.financialtracker to javafx.fxml;
    exports com.financialtracker;
    exports com.financialtracker.db;
    opens com.financialtracker.db to javafx.fxml;
}