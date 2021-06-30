package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnauthorizedAccessTests extends CloudStorageApplicationTests {
    @Test
    @DisplayName("Unauthorized user can access login page")
    public void testUnauthorizedUserAccessLoginPage() {
        driver.get("http://localhost:" + port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    @DisplayName("Unauthorized user can access signup page")
    public void testUnauthorizedUserAccessSignupPage() {
        driver.get("http://localhost:" + port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    @DisplayName("Unauthorized user cannot access home page")
    public void testUnauthorizedUserAccessHomePage() {
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertFalse("Home".equalsIgnoreCase(driver.getTitle()));
    }
}
