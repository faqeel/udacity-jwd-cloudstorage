package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(className = "alert-success")
    private WebElement alert;

    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void setUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput)).sendKeys(username);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }

    public void login() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getAlertText() {
        if (alert == null) {
            return "";
        }
        return wait.until(ExpectedConditions.elementToBeClickable(alert)).getText();
    }
}
