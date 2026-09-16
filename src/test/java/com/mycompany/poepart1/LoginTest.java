/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTest {

    @Test
    public void testLoginSuccessful() {
        Login login = new Login();
        login.setCredentials("John", "Doe", "kyl_1", "Ch&&sec@ke99!");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login();
        login.setCredentials("John", "Doe", "kyl_1", "Ch&&sec@ke99!");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = new Login();
        login.setCredentials("John", "Doe", "kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome John,Doe it is great to see you.",
                     login.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        Login login = new Login();
        login.setCredentials("John", "Doe", "kyl_1", "Ch&&sec@ke99!");
        assertEquals("Username or password incorrect, please try again.",
                     login.returnLoginStatus(false));
    }
}