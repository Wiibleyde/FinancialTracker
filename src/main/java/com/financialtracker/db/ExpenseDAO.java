package com.financialtracker.db;

import com.financialtracker.Line;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpenseDAO {
    public static void insertExpense(Line line) {
        String insertExpense = "INSERT INTO expense(date, housing, food, goingOut, transportation, travel, tax, other) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Database.connect()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(insertExpense);
            statement.setString(1, line.getPeriod());
            statement.setFloat(2, line.getHousing());
            statement.setFloat(3, line.getFood());
            statement.setFloat(4, line.getExits());
            statement.setFloat(5, line.getTransport());
            statement.setFloat(6, line.getTravel());
            statement.setFloat(7, line.getTaxes());
            statement.setFloat(8, line.getOther());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not insert expense into database");
        }
    }

    public static List<Line> getExpenses() {
        String getExpenses = "SELECT * FROM expense";
        List<Line> expenses = new ArrayList<Line>();
        try (Connection connection = Database.connect()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(getExpenses);
            statement.execute();
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Line line = new Line();
                line.setPeriod(resultSet.getString("date"));
                line.setHousing(resultSet.getFloat("housing"));
                line.setFood(resultSet.getFloat("food"));
                line.setExits(resultSet.getFloat("goingOut"));
                line.setTransport(resultSet.getFloat("transportation"));
                line.setTravel(resultSet.getFloat("travel"));
                line.setTaxes(resultSet.getFloat("tax"));
                line.setOther(resultSet.getFloat("other"));
                line.setTotal(line.getHousing() + line.getFood() + line.getExits() + line.getTransport() + line.getTravel() + line.getTaxes() + line.getOther());
                expenses.add(line);
            }
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not get expenses from database");
        }
        return expenses;
    }
}
