package com.financialtracker.db;

import com.financialtracker.Model.IncomeLine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {
    public static void insertIncome(IncomeLine income) {
        String insertIncomeSQL = "INSERT INTO income(date, salary, grant, buisnessSalary, passiveIncome, other) VALUES(?, ?, ?, ?, ?, ?)";
        try (var connection = Database.connect()) {
            assert connection != null;
            var statement = connection.prepareStatement(insertIncomeSQL);
            statement.setString(1, income.getPeriod().toString());
            statement.setFloat(2, income.getSalary());
            statement.setFloat(3, income.getGrant());
            statement.setFloat(4, income.getBusinessSalary());
            statement.setFloat(5, income.getPassiveIncome());
            statement.setFloat(6, income.getOther());
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Could not insert income into database");
        }
    }

    public static List<IncomeLine> getIncomes() {
        String getIncomesSQL = "SELECT * FROM income";
        List<IncomeLine> incomes = new ArrayList<>();
        try (var connection = Database.connect()) {
            assert connection != null;
            var statement = connection.prepareStatement(getIncomesSQL);
            statement.execute();
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                IncomeLine income = new IncomeLine();
                income.setPeriod(resultSet.getString("date"));
                income.setSalary(resultSet.getFloat("salary"));
                income.setGrant(resultSet.getFloat("grant"));
                income.setBusinessSalary(resultSet.getFloat("buisnessSalary"));
                income.setPassiveIncome(resultSet.getFloat("passiveIncome"));
                income.setOther(resultSet.getFloat("other"));
                incomes.add(income);
            }
        } catch (SQLException exception) {
            System.err.println("Could not retrieve incomes from database");
        }
        return incomes;
    }
}
