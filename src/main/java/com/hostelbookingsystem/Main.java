package com.hostelbookingsystem;

import java.util.Scanner;
import com.hostelbookingsystem.view.AdminView;
import com.hostelbookingsystem.view.StudentView;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            clearConsole();
            System.out.println("Welcome to Hostel Booking System");
            System.out.println("1. Student View");
            System.out.println("2. Admin View");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    StudentView studentView = new StudentView();
                    studentView.main();
                    ;
                    break;
                case 2:
                    AdminView adminView = new AdminView();
                    adminView.main();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
