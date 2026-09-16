/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RegistrationTest {

    Registration reg = new Registration();

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(reg.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(reg.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(reg.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(reg.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(reg.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(reg.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testRegisterUserUsernameError() {
        String expected = "Username is not correctly formatted; please ensure that your "
                        + "username contains an underscore and is no more than five "
                        + "characters in length.";
        assertEquals(expected, reg.registerUser("John", "Doe", "kyle!!!!!!!",
                                                 "Ch&&sec@ke99!", "+27838968976"));
    }

    @Test
    public void testRegisterUserPasswordError() {
        String expected = "Password is not correctly formatted; please ensure that the "
                        + "password contains at least eight characters, a capital letter, "
                        + "a number, and a special character.";
        assertEquals(expected, reg.registerUser("John", "Doe", "kyl_1",
                                                 "password", "+27838968976"));
    }

    @Test
    public void testRegisterUserCellError() {
        String expected = "Cell number is incorrectly formatted or does not contain an "
                        + "international code; please correct the number and try again.";
        assertEquals(expected, reg.registerUser("John", "Doe", "kyl_1",
                                                 "Ch&&sec@ke99!", "08966553"));
    }

    @Test
    public void testRegisterUserSuccess() {
        assertEquals("Username successfully captured.",
                     reg.registerUser("John", "Doe", "kyl_1",
                                      "Ch&&sec@ke99!", "+27838968976"));
    }
}