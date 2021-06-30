package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoteTests extends CloudStorageApplicationTests {
    @Test
    @DisplayName("Create and display created note")
    public void testCreateNote() {
        signUp();
        logIn();

        driver.get("http://localhost:" + port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.openNotesTab();
        int rowsBefore = homePage.getNotesTableSize();
        homePage.showNoteModal();
        homePage.setNoteTitleInput("note title");
        homePage.setNoteDescriptionInput("note description");
        homePage.saveNote();

        driver.get("http://localhost:" + port + "/home");
        homePage.openNotesTab();
        int rowsAfter = homePage.getNotesTableSize();
        Assertions.assertEquals(rowsBefore + 1, rowsAfter);
    }

    @Test
    @DisplayName("Edit and display edited note")
    public void testEditNote() {
        signUp();
        logIn();

        driver.get("http://localhost:" + port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.openNotesTab();
        homePage.showNoteModal();
        homePage.setNoteTitleInput("note title");
        homePage.setNoteDescriptionInput("note description");
        homePage.saveNote();

        String updatedTitle = "updated title";

        driver.get("http://localhost:" + port + "/home");
        homePage.openNotesTab();
        homePage.editLastNote();
        homePage.setNoteTitleInput(updatedTitle);
        homePage.setNoteDescriptionInput("the updated note description");
        homePage.saveNote();

        driver.get("http://localhost:" + port + "/home");
        homePage.openNotesTab();
        String lastNoteTitle = homePage.getLastNoteTitle();
        Assertions.assertEquals(updatedTitle, lastNoteTitle);
    }

    @Test
    @DisplayName("Delete a note")
    public void testDeleteNote() {
        signUp();
        logIn();

        driver.get("http://localhost:" + port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.openNotesTab();
        homePage.showNoteModal();
        homePage.setNoteTitleInput("note title");
        homePage.setNoteDescriptionInput("note description");
        homePage.saveNote();

        driver.get("http://localhost:" + port + "/home");
        homePage.openNotesTab();
        int rowsBefore = homePage.getNotesTableSize();
        homePage.deleteLastNote();

        driver.get("http://localhost:" + port + "/home");
        int rowsAfter = homePage.getNotesTableSize();
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
