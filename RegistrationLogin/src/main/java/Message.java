package com.mycompany.registrationlogin;

import java.util.Random;

public class Message {

    private String messageID;
    private String recipientCell;
    private String messageContent;
    private String messageHash;
    private String deliveryStatus;

    // Checks that the message ID is 10 characters or less
    public boolean checkMessageID(String messageID) {
        if (messageID == null) return false;
        return messageID.length() <= 10;
    }

    // This Checks the receipient cell number and does not exceed 10 characters and starts with a Code
    public String checkRecipientCell(String cell) {
        if (cell == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        // Match conditions: Must start with + or represent a valid code setup
        if (cell.startsWith("+") || (cell.length() == 11 && cell.startsWith("27")) || cell.startsWith("085")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Auto-generates a unique 10-digit number for message tracking
    public String generateRandomID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        this.messageID = sb.toString();
        return this.messageID;
    }

    // Checks message length constraints (Max 250 characters)
    public String checkMessageLength(String message) {
        if (message == null) return "Please enter a message of less than 250 characters.";
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int exceededBy = message.length() - 250;
            return "Message exceeds 250 characters by " + exceededBy + "; please reduce the size.";
        }
    }

    // Creates the Message Hash: First 2 numbers of ID : length of message : First and Last word in Caps
    public String createMessageHash(String id, String msg) {
        if (id == null || id.length() < 2 || msg == null || msg.trim().isEmpty()) {
            return "00:0:EMPTY";
        }
        
        String firstTwoId = id.substring(0, 2);
        int messageLength = msg.length();
        
        String[] words = msg.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "").toUpperCase();
        
        this.messageHash = firstTwoId + ":" + messageLength + ":" + firstWord + lastWord;
        return this.messageHash;
    }

    // Process user options: Send (1), Disregard (2), Store (3)
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                this.deliveryStatus = "Message successfully sent";
                return "Message successfully sent.";
            case 2:
                this.deliveryStatus = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.deliveryStatus = "Message successfully stored";
                return "Message successfully stored.";
            default:
                this.deliveryStatus = "Unknown";
                return "Invalid selection.";
        }
    }

    // Displays the info in the requested order: ID, Hash, Recipient, Message
    public String printMessages() {
        return "Message ID: " + this.messageID + "\n" +
               "Message Hash: " + this.messageHash + "\n" +
               "Recipient: " + this.recipientCell + "\n" +
               "Message: " + this.messageContent;
    }

    // Setters and Getters
    public void setRecipientCell(String cell) { this.recipientCell = cell; }
    public void setMessageContent(String msg) { this.messageContent = msg; }
    public void setMessageID(String id) { this.messageID = id; }
    public void setMessageHash(String hash) { this.messageHash = hash; }
    public String getDeliveryStatus() { return deliveryStatus; }
}