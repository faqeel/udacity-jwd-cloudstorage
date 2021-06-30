package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(className = "alert-success")
    private WebElement alert;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void login() {
        submitButton.click();
    }

    public String getAlertText() {
        if (alert == null) {
            return "";
        }
        return alert.getText();
    }
}
