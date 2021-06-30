package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInput;

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "buttonSubmit")
    private WebElement submitButton;

    private final WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void setFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInput)).sendKeys(firstName);
    }

    public void setLastNameInput(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(lastNameInput)).sendKeys(lastName);
    }

    public void setUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).sendKeys(username);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    public void signup() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}
