import database.Database;
import models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    private static Database db = new Database();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Delivery Service User Management ===");
            System.out.println("1. Register new user");
            System.out.println("2. View user account");
            System.out.println("3. Delete user account");
            System.out.println("4. View all users");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    viewUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    displayAllUsers();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the program...");
                    db.closeConnection();  
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: "); 
        String password = scanner.nextLine();

        User newUser = new User(username, email, password);
        db.addUser(newUser);
    }


    private static void viewUser() {
        System.out.print("Enter username to view: ");
        String username = scanner.nextLine();
        System.out.print("Enter password to confirm: "); 
        String password = scanner.nextLine();


        User user = db.getUser(username);
        if (user != null && user.getPassword().equals(hashPassword(password))) { 
            System.out.println("\n=== User Details ===");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("HashedPassword: " + user.getPassword());
        } else {
            System.out.println("User not found or password incorrect.");
        }
    }


    private static void displayAllUsers() {
        System.out.println("\n=== All Users ===");
        db.displayUsers();
    }


    private static void deleteUser() {
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();
        System.out.print("Enter password to confirm deletion: "); 
        String password = scanner.nextLine();
        db.deleteUser(username, password);
    }


    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}