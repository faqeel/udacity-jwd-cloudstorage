package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CredentialTests extends CloudStorageApplicationTests {
    @Test
    @DisplayName("Create and display created credential")
    public void testCreateCredential() {
        signUp();
        logIn();

        driver.get("http://localhost:" + port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());

        String password = "password";
        HomePage homePage = new HomePage(driver);
        homePage.openCredentialsTab();
        int rowsBefore = homePage.getCredentialsTableSize();
        homePage.showCredentialModal();
        homePage.setCredentialUrlInput("url");
        homePage.setCredentialUsernameInput("username");
        homePage.setCredentialPasswordInput(password);
        homePage.saveCredential();

        driver.get("http://localhost:" + port + "/home");
        homePage.openCredentialsTab();
        int rowsAfter = homePage.getCredentialsTableSize();
        String lastCredentialPassword = homePage.getLastCredentialPassword();

        Assertions.assertNotEquals(password, lastCredentialPassword);
        Assertions.assertEquals(rowsBefore + 1, rowsAfter);
    }

    @Test
    @DisplayName("Edit and display edited credential")
    public void testEditNote() {
        signUp();
        logIn();

        driver.get("http://localhost:" + port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());

        String password = "password";
        HomePage homePage = new HomePage(driver);
        homePage.openCredentialsTab();
        homePage.showCredentialModal();
        homePage.setCredentialUrlInput("url");
        homePage.setCredentialUsernameInput("username");
        homePage.setCredentialPasswordInput(password);
        homePage.saveCredential();

        String updatedUrl = "updated";

        driver.get("http://localhost:" + port + "/home");
        homePage.openCredentialsTab();
        homePage.editLastCredential();
        homePage.setCredentialUrlInput(updatedUrl);

        String viewablePassword = homePage.getCredentialPasswordInput();
        Assertions.assertEquals(password, viewablePassword);

        homePage.saveCredential();

        driver.get("http://localhost:" + port + "/home");
        homePage.openCredentialsTab();

        String lastCredentialUrl = homePage.getLastCredentialUrl();
        Assertions.assertEquals(updatedUrl, lastCredentialUrl);
    }

    @Test
    @DisplayName("Delete a credential")
    public void testDeleteNote() {
        signUp();
        logIn();

        driver.get("http://localhost:" + port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.openCredentialsTab();
        homePage.showCredentialModal();
        homePage.setCredentialUrlInput("url");
        homePage.setCredentialUsernameInput("username");
        homePage.setCredentialPasswordInput("password");
        homePage.saveCredential();

        driver.get("http://localhost:" + port + "/home");
        homePage.openCredentialsTab();
        int rowsBefore = homePage.getCredentialsTableSize();
        homePage.deleteLastCredential();

        driver.get("http://localhost:" + port + "/home");
        int rowsAfter = homePage.getCredentialsTableSize();
        Assertions.assertEquals(rowsBefore - 1, rowsAfter);
    }

    private void signUp() {
        SignUpPage signUpPage = new SignUpPage(driver);
        driver.get("http://localhost:" + port + "/signup");
        signUpPage.setFirstName("Fares");
        signUpPage.setLastNameInput("A");
        signUpPage.setUsername("faqeel");
        signUpPage.setPassword("123456");
        signUpPage.signup();
    }

    private void logIn() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + port + "/login");
        loginPage.setUsername("faqeel");
        loginPage.setPassword("123456");
        loginPage.login();
    }
}
