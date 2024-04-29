package com.example.addressbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for managing SQLite database connections.
 */
public class SqliteConnection {
    private static Connection instance = null;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private SqliteConnection() {
        String url = "jdbc:sqlite:contacts.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    /**
     * Retrieves the singleton instance of the SQLite connection.
     * If the instance does not exist, it is created.
     * @return The singleton instance of the SQLite connection.
     */
    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}