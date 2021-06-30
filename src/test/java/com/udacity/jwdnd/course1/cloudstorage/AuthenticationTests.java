package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthenticationTests extends CloudStorageApplicationTests {
    @Test
    @DisplayName("User can signup")
    public void tesSignUp() {
        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:" + port + "/signup");
        signUpPage.setFirstName("Fares");
        signUpPage.setLastNameInput("A");
        signUpPage.setUsername("faqeel");
        signUpPage.setPassword("123456");
        signUpPage.signup();

        String expected = "You have successfully signed up!";
        LoginPage loginPage = new LoginPage(driver);
        String actual = loginPage.getAlertText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("User can login")
    public void testLogin() {
        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:" + port + "/signup");
        signUpPage.setFirstName("Fares");
        signUpPage.setLastNameInput("A");
        signUpPage.setUsername("faqeel");
        signUpPage.setPassword("123456");
        signUpPage.signup();

        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + this.port + "/login");
        loginPage.setUsername("faqeel");
        loginPage.setPassword("123456");
        loginPage.login();
        Assertions.assertEquals("Home", driver.getTitle());
    }

    @Test
    @DisplayName("User can logout")
    public void testLogout() {
        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:" + port + "/signup");
        signUpPage.setFirstName("Fares");
        signUpPage.setLastNameInput("A");
        signUpPage.setUsername("faqeel");
        signUpPage.setPassword("123456");
        signUpPage.signup();

        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + this.port + "/login");
        loginPage.setUsername("faqeel");
        loginPage.setPassword("123456");
        loginPage.login();
        Assertions.assertEquals("Home", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/home");
        HomePage homePage = new HomePage(driver);
        homePage.logout();
        Assertions.assertFalse("Home".equalsIgnoreCase(driver.getTitle()));
    }
}
