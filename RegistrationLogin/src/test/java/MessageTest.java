package com.mycompany.registrationlogin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oratile
 */
public class MessageTest {

    public MessageTest() {
    }

    @Test
    public void testMessageLengthSuccess() {
        Message tester = new Message();
        String result = tester.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        Message tester = new Message();
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsg.append("X");
        }
        String result = tester.checkMessageLength(longMsg.toString());
        assertTrue(result.contains("Message exceeds 250 characters by"));
    }

    @Test
    public void testRecipientCellSuccess() {
        Message tester = new Message();
        String result = tester.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientCellFailure() {
        Message tester = new Message();
        String result = tester.checkRecipientCell("bad_number");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashGeneration() {
        Message tester = new Message();
        // ID starting with 00 and containing standard words
        String targetHash = tester.createMessageHash("0012345678", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(targetHash.startsWith("00:"));
        assertTrue(targetHash.endsWith("HITONIGHT"));
    }

    @Test
    public void testSentMessageChoices() {
        Message tester = new Message();
        assertEquals("Message successfully sent.", tester.SentMessage(1));
        assertEquals("Press 0 to delete the message.", tester.SentMessage(2));
        assertEquals("Message successfully stored.", tester.SentMessage(3));
    }
}