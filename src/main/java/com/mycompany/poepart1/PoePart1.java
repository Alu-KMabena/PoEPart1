/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;

import java.util.Scanner;

public class PoePart1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- PART 1: REGISTRATION & LOGIN ---
        System.out.println("=== Chat App Registration ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username (must contain _ and be <= 5 chars): ");
        String username = scanner.nextLine();

        System.out.print("Enter password (min 8 chars, 1 capital, 1 number, 1 special): ");
        String password = scanner.nextLine();

        System.out.print("Enter SA cell number (format: +27831234567): ");
        String cellPhone = scanner.nextLine();

        Registration registration = new Registration();
        String registerResult = registration.registerUser(
                firstName, lastName, username, password, cellPhone);
        System.out.println("\n" + registerResult);

        if (registration.getUsername() == null) {
            System.out.println("Registration failed. Exiting.");
            scanner.close();
            return;
        }

        Login login = new Login();
        login.setCredentials(
                registration.getFirstName(),
                registration.getLastName(),
                registration.getUsername(),
                registration.getPassword());

        System.out.println("\n=== Login ===");
        int attempts = 0;
        boolean success = false;

        while (attempts < 3 && !success) {
            System.out.print("Enter username: ");
            String loginUser = scanner.nextLine();
            System.out.print("Enter password: ");
            String loginPass = scanner.nextLine();

            success = login.loginUser(loginUser, loginPass);
            System.out.println(login.returnLoginStatus(success));

            if (!success) {
                attempts++;
                if (attempts < 3) {
                    System.out.println("Try again. Attempts remaining: " + (3 - attempts));
                }
            }
        }

        if (!success) {
            System.out.println("Too many failed attempts. Exiting.");
            scanner.close();
            return;
        }

        // --- PART 2: QUICKCHAT MENU ---
        System.out.println("\nWelcome to QuickChat.");
        
        boolean running = true;
        int numMessagesToSend = 0;
        int messagesSentCount = 0;

        while (running) {
            System.out.println("\n--- QuickChat Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            
            String menuChoice = scanner.nextLine();

            switch (menuChoice) {
                case "1":
                    // If this is the first time sending, ask how many messages they want to enter
                    if (numMessagesToSend == 0) {
                        System.out.print("How many messages do you wish to enter? ");
                        try {
                            numMessagesToSend = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Defaulting to 1.");
                            numMessagesToSend = 1;
                        }
                    }

                    if (messagesSentCount < numMessagesToSend) {
                        // Capture Message details
                        System.out.print("Enter recipient cell number (e.g., +27718693002): ");
                        String recipient = scanner.nextLine();

                        System.out.print("Enter message text (max 250 chars): ");
                        String msgText = scanner.nextLine();

                        if (msgText.length() > 250) {
                            System.out.println("Please enter a message of less than 250 characters.");
                            break; // Go back to menu
                        }

                        // Create the message object (using the current count as the message number)
                        Message msg = new Message(messagesSentCount, recipient, msgText);

                        // Validate recipient cell
                        if (!msg.checkRecipientCell().equals("Cell phone number successfully captured.")) {
                            System.out.println(msg.checkRecipientCell());
                            break; // Go back to menu
                        }

                        // Show message details
                        System.out.println("\nMessage generated:");
                        System.out.println(msg.printMessages());

                        // Ask what to do with the message
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");
                        System.out.print("Choice: ");
                        int action = Integer.parseInt(scanner.nextLine());

                        String actionResult = msg.SentMessage(action);
                        System.out.println(actionResult);

                        if (action == 1) {
                            messagesSentCount++; // Only count if actually sent
                        }

                        if (messagesSentCount >= numMessagesToSend) {
                            System.out.println("\nTotal messages sent: " + msg.returnTotalMessages());
                            numMessagesToSend = 0; // Reset so they can choose a new batch later
                            messagesSentCount = 0;
                        }

                    } else {
                        System.out.println("You have reached your limit for this batch. Choose option 1 again to start a new batch.");
                    }
                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}