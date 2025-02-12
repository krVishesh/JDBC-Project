package com.hostelbookingsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hostelbookingsystem.model.User;

public class UserDAO {
    private Connection connection;

    // Constructor to initialize the database connection
    public UserDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:33061/hostel_booking_system", "root",
                    "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a user to the database
    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to validate user credentials
    public boolean validateUser(String username, String password, String role) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If there's at least one row, the user is validated
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
