package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    @FindBy(id = "buttonLogout")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "button-add-note")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "button-save-note")
    private WebElement noteSaveButton;

    @FindBy(id = "notesTable")
    private WebElement notesTable;

    @FindBy(id = "credentialTable")
    private WebElement credentialsTable;

    @FindBy(id = "button-add-credential")
    private WebElement addCredentialButton;

    @FindBy(id = "button-save-credential")
    private WebElement credentialSaveButton;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlInput;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordInput;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutButton.click();
    }

    public void openNotesTab() {
        notesTab.click();
    }

    public void openCredentialsTab() {
        credentialsTab.click();
    }

    public void showNoteModal() {
        addNoteButton.click();
    }

    public void showCredentialModal() {
        addCredentialButton.click();
    }

    public void setNoteTitleInput(String title) {
        noteTitleInput.clear();
        noteTitleInput.sendKeys(title);
    }

    public void setNoteDescriptionInput(String description) {
        noteDescriptionInput.clear();
        noteDescriptionInput.sendKeys(description);
    }

    public void setCredentialUrlInput(String url) {
        credentialUrlInput.clear();
        credentialUrlInput.sendKeys(url);
    }

    public void setCredentialUsernameInput(String username) {
        credentialUsernameInput.clear();
        credentialUsernameInput.sendKeys(username);
    }

    public void setCredentialPasswordInput(String password) {
        credentialPasswordInput.clear();
        credentialPasswordInput.sendKeys(password);
    }

    public String getCredentialPasswordInput() {
        return credentialPasswordInput.getAttribute("value");
    }

    public void saveNote() {
        noteSaveButton.click();
    }

    public void saveCredential() {
        credentialSaveButton.click();
    }

    public Integer getNotesTableSize() {
        WebElement tableBody = notesTable.findElement(By.tagName("tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        return rows.size();
    }

    public Integer getCredentialsTableSize() {
        WebElement tableBody = credentialsTable.findElement(By.tagName("tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        return rows.size();
    }

    public void editLastNote() {
        List<WebElement> rows = notesTable.findElements(By.tagName("tr"));
        WebElement lastNoteEditButton = rows.get(rows.size() - 1).findElement(By.className("btn-success"));
        lastNoteEditButton.click();
    }

    public void deleteLastNote() {
        List<WebElement> rows = notesTable.findElements(By.tagName("tr"));
        WebElement lastNoteDeleteButton = rows.get(rows.size() - 1).findElement(By.className("btn-danger"));
        lastNoteDeleteButton.click();
    }

    public void deleteLastCredential() {
        List<WebElement> rows = credentialsTable.findElements(By.tagName("tr"));
        WebElement lastNoteDeleteButton = rows.get(rows.size() - 1).findElement(By.className("btn-danger"));
        lastNoteDeleteButton.click();
    }

    public String getLastNoteTitle() {
        List<WebElement> rows = notesTable.findElements(By.tagName("tr"));
        WebElement lastNote = rows.get(rows.size() - 1).findElement(By.tagName("th"));
        return lastNote.getText();
    }

    public String getLastCredentialPassword() {
        List<WebElement> rows = credentialsTable.findElements(By.tagName("tr"));
        List<WebElement> tds = rows.get(rows.size() - 1).findElements(By.tagName("td"));
        return tds.get(rows.size() - 1).getText();
    }

    public String getLastCredentialUrl() {
        List<WebElement> rows = credentialsTable.findElements(By.tagName("tr"));
        WebElement lastCredentialUrl = rows.get(rows.size() - 1).findElement(By.tagName("th"));
        return lastCredentialUrl.getText();
    }

    public void editLastCredential() {
        List<WebElement> rows = credentialsTable.findElements(By.tagName("tr"));
        WebElement lastNoteEditButton = rows.get(rows.size() - 1).findElement(By.className("btn-success"));
        lastNoteEditButton.click();
    }
}
