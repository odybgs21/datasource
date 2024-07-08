/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.datastorafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author D-16
 */
public class DatabaseStorage implements DataStorage{
    private Connection connection;

    public DatabaseStorage(String databasePath) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+ databasePath);
            
            createDataTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    // ini private methode (internal methode tambahan di dalam kelas DatabaseStorage
    private void createDataTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS data (value TEXT)");
        }
    }

    @Override
    public void writeData(String data) {
        try ( PreparedStatement statement = connection.prepareStatement("INSERT INTO data (value) VALUES ('"+data+"')")) {
            //statement.setString(1, data);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        StringBuilder sb = new StringBuilder();
        try (Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery( "SELECT value FROM data")) {
            while (resultSet.next()) {
                sb.append(resultSet.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
