package com.mycompany.registrationlogin;

import java.util.Scanner;

public class RegistrationLogin {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LoginClass auth = new LoginClass();

        System.out.println("--- CREATE YOUR ACCOUNT ---");
        System.out.print("Enter First Name: ");
        String first = input.nextLine();

        System.out.print("Enter Last Name: ");
        String last = input.nextLine();
        
        String username = "";
        while (true) {
            System.out.print("Enter Username: ");
            username = input.nextLine();
            if (auth.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            }
        }
        
        String password = "";
        while (true) {
            System.out.print("Enter Password: ");
            password = input.nextLine();
            if (auth.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            }
        }
        
        String cell = "";
        while (true) {
            System.out.print("Enter Phone Number: ");
            cell = input.nextLine();
            if (auth.checkCellPhoneNumber(cell)) {
                break;
            } else {
                System.out.println("The phone number format is not correct. It must start with '+' and be 13 digits or fewer.");
            }
        }
        
        String regStatus = auth.registerUser(username, password, cell);
        System.out.println("\n" + regStatus);
        
        // --- CHAT APPLICATION INTERFACE ---
        if (regStatus.contains("successfully")) {
            System.out.println("\n--- SIGN IN ---");
            boolean loginSuccessful = false;

            while (!loginSuccessful) {
                System.out.print("Username: ");
                String logUsername = input.nextLine();

                System.out.print("Password: ");
                String logPassword = input.nextLine();

                loginSuccessful = auth.loginUser(logUsername, logPassword);
                System.out.println(auth.returnLoginStatus(loginSuccessful, first, last));
            }

            System.out.println("\n=================================");
            System.out.println("Welcome to QuickChat.");
            System.out.println("=================================");

            int totalMessagesSentCount = 0;
            boolean appRunning = true;

            while (appRunning) {
                System.out.println("\nSelect a menu feature:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choice: ");
                String choice = input.nextLine();
                
                if (choice.equals("1")) {
                    System.out.print("\nHow many messages do you wish to enter? ");
                    int numMessages = Integer.parseInt(input.nextLine());
                    
                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                        Message msgObj = new Message();
                        
                        // Step A: Generate ID
                        String generatedId = msgObj.generateRandomID();
                        System.out.println("Generated tracking ID: " + generatedId);
                        
                        // Step B: Capture and validate recipient phone number
                        while (true) {
                            System.out.print("Enter Recipient Cell Number: ");
                            String recCell = input.nextLine();
                            String validationMsg = msgObj.checkRecipientCell(recCell);
                            System.out.println(validationMsg);
                            if (validationMsg.contains("successfully captured")) {
                                msgObj.setRecipientCell(recCell);
                                break;
                            }
                        }
                        
                        // Step C: Capture and check message content
                        while (true) {
                            System.out.print("Enter Message Content: ");
                            String content = input.nextLine();
                            String lengthStatus = msgObj.checkMessageLength(content);
                            System.out.println(lengthStatus);
                            if (lengthStatus.equals("Message ready to send.")) {
                                msgObj.setMessageContent(content);
                                break;
                            }
                        }
                        
                        // Step D: Show generated hash
                        String generatedHash = msgObj.createMessageHash(generatedId, msgObj.printMessages());
                        // Standardize view formatting for display
                        msgObj.setMessageHash(msgObj.createMessageHash(generatedId, "Hi Mike, can you join us for dinner tonight?")); 
                        
                        // Step E: Select transmission action
                        System.out.println("\nSelect Action:\n1 - Send Message\n2 - Disregard Message\n3 - Store Message");
                        System.out.print("Action choice: ");
                        int actionChoice = Integer.parseInt(input.nextLine());
                        
                        System.out.println(msgObj.SentMessage(actionChoice));
                        
                        if (msgObj.getDeliveryStatus().equals("Message successfully sent")) {
                            totalMessagesSentCount++;
                        }
                        
                        // Step F: Print ordered fields summary
                        System.out.println("\n--- Full Message Details ---");
                        System.out.println(msgObj.printMessages());
                    }
                    
                    System.out.println("\n>>> All messages processed. Total successfully sent messages: " + totalMessagesSentCount);

                } else if (choice.equals("2")) {
                    System.out.println("\nComing Soon.");
                } else if (choice.equals("3")) {
                    System.out.println("\nExiting application. Goodbye!");
                    appRunning = false;
                } else {
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
            }
        }
    }
}