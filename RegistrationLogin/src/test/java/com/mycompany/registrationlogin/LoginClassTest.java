package com.mycompany.registrationlogin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oratile
 */
public class LoginClassTest {

    LoginClass auth = new LoginClass();

    @Test
    public void testCheckUserName() {
        // Must contain "_" and be <= 5 characters
        assertTrue(auth.checkUserName("ky_le"), "Should be true: contains underscore and is 5 chars");
        assertFalse(auth.checkUserName("kyle1"), "Should be false: missing underscore");
        assertFalse(auth.checkUserName("kyle_long"), "Should be false: exceeds 5 characters");
    }

    @Test
    public void testCheckPasswordComplexity() {
        // Must be >= 8 chars, have Cap, Num, and Special Char
        assertTrue(auth.checkPasswordComplexity("Ch@nge123"), "Should be true: meets all requirements");
        assertFalse(auth.checkPasswordComplexity("Password123"), "Should be false: missing special character");
        assertFalse(auth.checkPasswordComplexity("short"), "Should be false: too short");
    }

    @Test
    public void testCheckCellPhoneNumber() {
        // Must start with "+" and be <= 13 characters
        assertTrue(auth.checkCellPhoneNumber("+27123456789"), "Should be true: starts with + and correct length");
        assertFalse(auth.checkCellPhoneNumber("07123456789"), "Should be false: missing '+' prefix");
    }

    @Test
    public void testRegisterUser() {
        String expected = "The two above conditions have been met, and the user has been registered successfully.";
        String actual = auth.registerUser("ky_le", "Ch@nge123", "+27123456789");
        assertEquals(expected, actual, "Registration message must match code exactly.");
    }

    @Test
    public void testLoginUser() {
        // Register the user first to populate userStore and passStore
        auth.registerUser("ky_le", "Ch@nge123", "+27123456789");
        
        // Test correct login
        assertTrue(auth.loginUser("ky_le", "Ch@nge123"), "Login should succeed with correct credentials");
        
        // Test incorrect login
        assertFalse(auth.loginUser("wrong", "wrong"), "Login should fail with incorrect credentials");
    }

    @Test
    public void testReturnLoginStatus() {
        // Test Success String
        String expectedSuccess = "Welcome John Doe, it is great to see you again.";
        assertEquals(expectedSuccess, auth.returnLoginStatus(true, "John", "Doe"));
        
        // Test Failure String
        String expectedFailure = "Username or password incorrect, please try again.";
        assertEquals(expectedFailure, auth.returnLoginStatus(false, "John", "Doe"));
    }
}