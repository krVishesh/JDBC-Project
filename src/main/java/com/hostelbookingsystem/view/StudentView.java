package com.hostelbookingsystem.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.hostelbookingsystem.dao.BookingDAO;
import com.hostelbookingsystem.dao.RoomDAO;
import com.hostelbookingsystem.dao.UserDAO;
import com.hostelbookingsystem.model.Booking;
import com.hostelbookingsystem.model.Room;
import com.hostelbookingsystem.model.User;

public class StudentView {
    private final Scanner scanner;
    private final UserDAO userDAO;
    private boolean loggedIn;

    public StudentView() {
        scanner = new Scanner(System.in);
        userDAO = new UserDAO();
        loggedIn = false;
    }

    public void displayMenu() {
        while (true) {
            if (!loggedIn) {
                System.out.println("Please login to access features.");
                System.out.println("1. Login");
                System.out.println("2. Create New Username");
                System.out.println("3. Cancel");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        createUser();
                        break;
                    case 3:
                        System.out.println("Login canceled.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else {
                System.out.println("Welcome to Hostel Booking System (Student View)");
                System.out.println("1. Check Available Rooms");
                System.out.println("2. Book Room");
                System.out.println("0. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        checkAvailableRooms();
                        break;
                    case 2:
                        bookRoom();
                        break;
                    case 0:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        return; // Exit the method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }

    private void createUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // For simplicity, assuming all users created through student view have the role
        // "student"
        User user = new User(username, password, "student");
        userDAO.addUser(user);
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (userDAO.validateUser(username, password, "student")) {
            System.out.println("Login successful.");
            loggedIn = true;
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void checkAvailableRooms() {
        // Assuming you have a RoomDAO class with a method to fetch available rooms
        RoomDAO roomDAO = new RoomDAO();
        List<Room> availableRooms = roomDAO.getAvailableRooms();

        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms.");
        } else {
            System.out.println("Available Rooms:");
            for (Room room : availableRooms) {
                System.out.println("Block: " + room.getBlock() + ", Room Number: " + room.getRoomNumber() + ", Beds: "
                        + room.getNumberOfBeds());
            }
        }
    }

    private void bookRoom() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (hasBookedRoom(username)) {
            System.out.println("You have already booked a room. Cannot book another room.");
            return;
        }

        BookingDAO bookingDAO = new BookingDAO();

        System.out.print("Enter block: ");
        String block = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter payment status (paid/due): ");
        String paymentStatus = scanner.nextLine();

        Booking booking = new Booking(username, block, roomNumber, new Date(System.currentTimeMillis()), paymentStatus);
        bookingDAO.addBooking(booking);
    }

    private boolean hasBookedRoom(String username) {
        BookingDAO bookingDAO = new BookingDAO();
        return bookingDAO.hasBookedRoom(username);
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void main() {
        clearConsole();
        StudentView studentView = new StudentView();
        studentView.displayMenu();
    }
}
