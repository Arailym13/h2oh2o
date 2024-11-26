package com.example.h2oh2o.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgresManager {

    private static final String URL = "jdbc:postgresql://<host>:<port>/db";
    private static final String USER = "moni";
    private static final String PASSWORD = "moni";

    public static void connectToPostgres() {
        new Thread(() -> {
            try {
                // Load the PostgreSQL driver
                Class.forName("org.postgresql.Driver");

                // Establish the connection
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();

                // Execute a sample query
                ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table");
                while (resultSet.next()) {
                    String data = resultSet.getString("column_name");
                    System.out.println("Data: " + data);
                }

                // Close the connection
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
