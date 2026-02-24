package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private final WebDriver driver;

    private final By loginLink = By.xpath("//a[.='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти со страницы восстановления пароля на форму логина по ссылке 'Войти'")
    public LoginPage goToLoginFromForgotPassword() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}

