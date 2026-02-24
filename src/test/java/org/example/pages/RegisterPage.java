package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    private final By nameInput = By.xpath("//label[.='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[.='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[.='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[.='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[.='Войти']");
    private final By errorMessage = By.xpath("//p[contains(@class,'input__error')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнить форму регистрации: имя {name}, email {email}")
    public void fillForm(String name, String email, String password) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Отправить форму регистрации")
    public void submit() {
        driver.findElement(registerButton).click();
    }

    @Step("Перейти со страницы регистрации на форму логина по ссылке 'Войти'")
    public LoginPage goToLoginFromRegister() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Получить текст ошибки под полем пароля")
    public String getPasswordErrorText() {
        return driver.findElement(errorMessage).getText();
    }
}

