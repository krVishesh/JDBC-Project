// RoomDAO.java (DAO Class)
package com.hostelbookingsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hostelbookingsystem.model.Room;

public class RoomDAO {
    private Connection connection;

    public RoomDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:33061/hostel_booking_system", "root",
                    "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String block = resultSet.getString("block");
                int roomNumber = resultSet.getInt("room_number");
                int numberOfBeds = resultSet.getInt("no_of_beds");
                String availability = resultSet.getString("availability");
                Room room = new Room(block, roomNumber, numberOfBeds, availability);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void addRoom(Room room) {
        String sql = "INSERT INTO rooms (block, room_number, no_of_beds, availability) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, room.getBlock());
            statement.setInt(2, room.getRoomNumber());
            statement.setInt(3, room.getNumberOfBeds());
            statement.setString(4, room.getAvailability());
            statement.executeUpdate();
            System.out.println("Room added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(String block, int roomNumber) {
        String sql = "DELETE FROM rooms WHERE block = ? AND room_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, block);
            statement.setInt(2, roomNumber);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Room deleted successfully.");
            } else {
                System.out.println("Room not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE availability = 'yes'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String block = resultSet.getString("block");
                int roomNumber = resultSet.getInt("room_number");
                int numberOfBeds = resultSet.getInt("no_of_beds");
                String availability = resultSet.getString("availability");
                Room room = new Room(block, roomNumber, numberOfBeds, availability);
                availableRooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableRooms;
    }

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
