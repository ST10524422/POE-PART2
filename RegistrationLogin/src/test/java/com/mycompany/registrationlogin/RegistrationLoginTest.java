package com.mycompany.registrationlogin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oratile
 */
public class RegistrationLoginTest {
    
    public RegistrationLoginTest() {
    }

    @Test
    public void testUsernameSuccess() {
         LoginClass auth = new LoginClass();
        // Contains '_' and is <= 5 characters
        assertTrue(auth.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameFailure() {
        LoginClass auth = new LoginClass();
        // Fails: exceeds 5 characters
        assertFalse(auth.checkUserName("kyle_smith"));
    }

    @Test
    public void testPasswordSuccess() {
        LoginClass auth = new LoginClass();
        // Meets all rules: >=8 chars, capital, number, special char
        assertTrue(auth.checkPasswordComplexity("Ch3ckm@t3"));
    }

    @Test
    public void testPasswordFailure() {
        LoginClass auth = new LoginClass();
        // Fails: no capital letter or special character
        assertFalse(auth.checkPasswordComplexity("password123"));
    }

    @Test
    public void testCellNumberSuccess() {
        LoginClass auth = new LoginClass();
        // Meets rules: starts with '+' and <= 13 characters
        assertTrue(auth.checkCellPhoneNumber("+27123456789"));
    }

    @Test
    public void testCellNumberFailure() {
        LoginClass auth = new LoginClass();
        // Fails: missing the '+' sign
        assertFalse(auth.checkCellPhoneNumber("07123456789"));
    }
    
    @Test
    public void testRegistrationSuccess() {
        LoginClass auth = new LoginClass();
        String result = auth.registerUser("kyl_1", "Ch3ckm@t3", "+27123456789");
        assertEquals("The two above conditions have been met, and the user has been registered successfully.", result);
    }
}