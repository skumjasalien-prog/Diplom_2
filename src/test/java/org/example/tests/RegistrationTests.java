package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.driver.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegisterPage;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

@Epic("Stellar Burgers")
@Feature("Регистрация")
public class RegistrationTests extends BaseTest {

    @Test
    @Story("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации с валидным паролем (не менее 6 символов)")
    public void successfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginToAccount();

        RegisterPage registerPage = loginPage.goToRegister();

        String uniqueEmail = "auto_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        String password = "valid1";

        registerPage.fillForm("Автотест", uniqueEmail, password);
        registerPage.submit();

        // После успешной регистрации пользователь попадает на форму логина
        LoginPage afterRegisterLogin = new LoginPage(driver);
        Assert.assertTrue("После успешной регистрации должна открыться форма логина",
                afterRegisterLogin.isLoginButtonVisible());
    }

    @Test
    @Story("Ошибка регистрации при некорректном пароле")
    @Description("Проверка, что при пароле короче 6 символов выводится сообщение об ошибке")
    public void registrationWithInvalidPasswordShowsError() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginToAccount();

        RegisterPage registerPage = loginPage.goToRegister();

        String uniqueEmail = "auto_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        String shortPassword = "12345"; // меньше 6 символов

        registerPage.fillForm("Автотест", uniqueEmail, shortPassword);
        registerPage.submit();

        String errorText = registerPage.getPasswordErrorText();
        Assert.assertTrue("Ожидалось сообщение об ошибке для некорректного пароля",
                errorText != null && errorText.toLowerCase().contains("некорректный пароль"));
    }
}

