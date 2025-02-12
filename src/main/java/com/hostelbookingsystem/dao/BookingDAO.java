// BookingDAO.java (DAO Class)
package com.hostelbookingsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hostelbookingsystem.model.Booking;

public class BookingDAO {
    private Connection connection;

    public BookingDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:33061/hostel_booking_system", "root",
                    "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String block = resultSet.getString("block");
                int roomNumber = resultSet.getInt("room_number");
                java.sql.Date bookingDate = resultSet.getDate("booking_date");
                String payment = resultSet.getString("payment");
                Booking booking = new Booking(username, block, roomNumber, bookingDate, payment);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public void addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (username, block, room_number, booking_date, payment) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, booking.getUsername());
            statement.setString(2, booking.getBlock());
            statement.setInt(3, booking.getRoomNumber());
            statement.setDate(4, booking.getBookingDate());
            statement.setString(5, booking.getPayment());
            statement.executeUpdate();
            System.out.println("Booking added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePaymentStatus(String username, String paymentStatus) {
        String sql = "UPDATE bookings SET payment = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, paymentStatus);
            statement.setString(2, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Payment status updated successfully.");
            } else {
                System.out.println("Booking not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBooking(String username) {
        String sql = "DELETE FROM bookings WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Booking removed successfully.");
            } else {
                System.out.println("Booking not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasBookedRoom(String username) {
        String sql = "SELECT * FROM bookings WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if the user has booked a room, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of any exception
        }
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
