package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void openNotesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();
    }

    public void openCredentialsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
    }

    public void showNoteModal() {
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();
    }

    public void showCredentialModal() {
        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton)).click();
    }

    public void setNoteTitleInput(String title) {
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleInput)).sendKeys(title);
    }

    public void setNoteDescriptionInput(String description) {
        wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionInput)).sendKeys(description);
    }

    public void setCredentialUrlInput(String url) {
        wait.until(ExpectedConditions.elementToBeClickable(credentialUrlInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUrlInput)).sendKeys(url);
    }

    public void setCredentialUsernameInput(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsernameInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsernameInput)).sendKeys(username);
    }

    public void setCredentialPasswordInput(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(credentialPasswordInput)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(credentialPasswordInput)).sendKeys(password);
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
        WebElement tableBody = wait.until(driver -> credentialsTable.findElement(By.tagName("tbody")));
        List<WebElement> rows = wait.until(driver -> tableBody.findElements(By.tagName("tr")));
        return rows.size();
    }

    public void editLastNote() {
        List<WebElement> rows = wait.until(driver -> notesTable.findElements(By.tagName("tr")));
        WebElement lastNoteEditButton = wait.until(driver -> rows.get(rows.size() - 1).findElement(By.className("btn-success")));
        lastNoteEditButton.click();
    }

    public void deleteLastNote() {
        List<WebElement> rows = wait.until(driver -> notesTable.findElements(By.tagName("tr")));
        WebElement lastNoteDeleteButton = wait.until(driver -> rows.get(rows.size() - 1).findElement(By.className("btn-danger")));
        lastNoteDeleteButton.click();
    }

    public void deleteLastCredential() {
        List<WebElement> rows = wait.until(driver -> credentialsTable.findElements(By.tagName("tr")));
        WebElement lastNoteDeleteButton = wait.until(driver -> rows.get(rows.size() - 1).findElement(By.className("btn-danger")));
        lastNoteDeleteButton.click();
    }

    public String getLastNoteTitle() {
        List<WebElement> rows = wait.until(driver -> notesTable.findElements(By.tagName("tr")));
        WebElement lastNote = wait.until(driver -> rows.get(rows.size() - 1).findElement(By.tagName("th")));
        return lastNote.getText();
    }

    public String getLastCredentialPassword() {
        List<WebElement> rows = wait.until(driver -> credentialsTable.findElements(By.tagName("tr")));
        List<WebElement> tds = wait.until(driver -> rows.get(rows.size() - 1).findElements(By.tagName("td")));
        return tds.get(rows.size() - 1).getText();
    }

    public String getLastCredentialUrl() {
        List<WebElement> rows = wait.until(driver -> credentialsTable.findElements(By.tagName("tr")));
        WebElement lastCredentialUrl = wait.until(driver -> rows.get(rows.size() - 1).findElement(By.tagName("th")));
        return lastCredentialUrl.getText();
    }

    public void editLastCredential() {
        List<WebElement> rows = credentialsTable.findElements(By.tagName("tr"));
        WebElement lastNoteEditButton = rows.get(rows.size() - 1).findElement(By.className("btn-success"));
        lastNoteEditButton.click();
    }
}
