package com.financialtracker.db;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabaseConfig {
    private static final String DB_NAME = "financial.db";

    public static String getDatabasePath() {
        String os = System.getProperty("os.name").toLowerCase();
        Path dbPath;

        if (os.contains("win")) {
            // Windows : C:\Users\USER\AppData\Roaming\FinancialTracker\database.db
            dbPath = Paths.get(System.getenv("APPDATA"), "FinancialTracker", DB_NAME);
        } else if (os.contains("mac")) {
            // macOS : /Users/USER/Library/Application Support/FinancialTracker/database.db
            dbPath = Paths.get(System.getProperty("user.home"), "Library", "Application Support", "FinancialTracker", DB_NAME);
        } else {
            // Linux : /home/USER/.local/share/FinancialTracker/database.db
            dbPath = Paths.get(System.getProperty("user.home"), ".local", "share", "FinancialTracker", DB_NAME);
        }

        File parentDir = dbPath.getParent().toFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        return dbPath.toString();
    }


}