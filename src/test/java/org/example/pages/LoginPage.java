package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private final By emailInput = By.xpath("//label[.='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[.='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[.='Войти']");
    private final By registrationLink = By.xpath("//a[.='Зарегистрироваться']");
    private final By restorePasswordLink = By.xpath("//a[.='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Выполнить логин пользователем {email}")
    public MainPage login(String email, String password) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }

    @Step("Перейти на форму регистрации")
    public RegisterPage goToRegister() {
        driver.findElement(registrationLink).click();
        return new RegisterPage(driver);
    }

    @Step("Перейти на форму восстановления пароля")
    public ForgotPasswordPage goToForgotPassword() {
        driver.findElement(restorePasswordLink).click();
        return new ForgotPasswordPage(driver);
    }

    @Step("Проверить отображение кнопки 'Войти' на форме логина")
    public boolean isLoginButtonVisible() {
        return driver.findElement(loginButton).isDisplayed();
    }
}

