package com.hostelbookingsystem.view;

import java.util.List;
import java.util.Scanner;

import com.hostelbookingsystem.dao.BookingDAO;
import com.hostelbookingsystem.dao.RoomDAO;
import com.hostelbookingsystem.dao.UserDAO;
import com.hostelbookingsystem.model.Booking;
import com.hostelbookingsystem.model.Room;

public class AdminView {
    private final Scanner scanner;
    private final UserDAO userDAO;
    private boolean loggedIn;

    public AdminView() {
        scanner = new Scanner(System.in);
        userDAO = new UserDAO();
        loggedIn = false;
    }

    public void displayMenu() {
        while (!loggedIn) {
            login();
            if (!loggedIn) {
                return; // Exit if login failed
            }
        }

        while (loggedIn) {
            System.out.println("Welcome to Hostel Booking System (Admin View)");
            System.out.println("1. Create New Room");
            System.out.println("2. Delete Existing Room");
            System.out.println("3. Delete Existing Booking");
            System.out.println("4. Check Room Details");
            System.out.println("5. Check Booking Details");
            System.out.println("6. Update Payment Status");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createNewRoom();
                    break;
                case 2:
                    deleteExistingRoom();
                    break;
                case 3:
                    deleteExistingBooking();
                    break;
                case 4:
                    checkRoomDetails();
                    break;
                case 5:
                    checkBookingDetails();
                    break;
                case 6:
                    updatePaymentStatus();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Assuming you have a UserDAO class with a method to validate admin login
        if (userDAO.validateUser(username, password, "admin")) {
            System.out.println("Login successful.");
            loggedIn = true;
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void createNewRoom() {
        System.out.print("Enter block: ");
        String block = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter number of beds: ");
        int numberOfBeds = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        RoomDAO roomDAO = new RoomDAO();
        roomDAO.addRoom(new Room(block, roomNumber, numberOfBeds, "yes"));
    }

    private void deleteExistingRoom() {
        System.out.print("Enter block of the room to delete: ");
        String block = scanner.nextLine();
        System.out.print("Enter room number to delete: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        // Assuming you have a RoomDAO class with a method to delete a room
        RoomDAO roomDAO = new RoomDAO();
        roomDAO.deleteRoom(block, roomNumber);
    }

    private void deleteExistingBooking() {
        System.out.print("Enter username of the student to delete booking: ");
        String username = scanner.nextLine();
        // Assuming you have a BookingDAO class with a method to delete a booking
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.removeBooking(username);
    }

    private void checkRoomDetails() {
        // Assuming you have a RoomDAO class with a method to fetch all rooms
        RoomDAO roomDAO = new RoomDAO();
        List<Room> rooms = roomDAO.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            System.out.println("Room Details:");
            for (Room room : rooms) {
                System.out.println("Block: " + room.getBlock() + ", Room Number: " + room.getRoomNumber() + ", Beds: "
                        + room.getNumberOfBeds() + ", Availability: " + room.getAvailability());
            }
        }
    }

    private void checkBookingDetails() {
        // Assuming you have a BookingDAO class with a method to fetch all bookings
        BookingDAO bookingDAO = new BookingDAO();
        List<Booking> bookings = bookingDAO.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            System.out.println("Booking Details:");
            for (Booking booking : bookings) {
                System.out.println("Username: " + booking.getUsername() + ", Block: " + booking.getBlock()
                        + ", Room Number: " + booking.getRoomNumber() + ", Booking Date: " + booking.getBookingDate()
                        + ", Payment: " + booking.getPayment());
            }
        }
    }

    private void updatePaymentStatus() {
        System.out.print("Enter username of the student whose payment status to update: ");
        String username = scanner.nextLine();
        System.out.print("Enter new payment status (paid/due): ");
        String paymentStatus = scanner.nextLine();

        // Assuming you have a BookingDAO class with a method to update payment status
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.updatePaymentStatus(username, paymentStatus);
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void main() {
        clearConsole();
        AdminView adminView = new AdminView();
        adminView.displayMenu();
    }
}
