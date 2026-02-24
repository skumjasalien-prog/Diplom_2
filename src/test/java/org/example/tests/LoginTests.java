package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.driver.BaseTest;
import org.example.pages.ForgotPasswordPage;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.RegisterPage;
import org.junit.Assert;
import org.junit.Test;

@Epic("Stellar Burgers")
@Feature("Авторизация")
public class LoginTests extends BaseTest {

    @Test
    @Story("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Пользователь переходит с главной страницы по кнопке 'Войти в аккаунт' и авторизуется")
    public void loginFromMainLoginButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginToAccount();

        MainPage afterLogin = loginPage.login(EXISTING_USER_EMAIL, EXISTING_USER_PASSWORD);
        Assert.assertTrue("После логина со страницы 'Войти в аккаунт' должна быть доступна главная с кнопкой 'Личный кабинет'",
                afterLogin.isPersonalAccountButtonVisible());
    }

    @Test
    @Story("Вход через кнопку 'Личный кабинет'")
    @Description("Пользователь нажимает 'Личный кабинет', попадает на форму логина и авторизуется")
    public void loginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickPersonalAccount();

        MainPage afterLogin = loginPage.login(EXISTING_USER_EMAIL, EXISTING_USER_PASSWORD);
        Assert.assertTrue("После логина через 'Личный кабинет' должна быть доступна главная с кнопкой 'Личный кабинет'",
                afterLogin.isPersonalAccountButtonVisible());
    }

    @Test
    @Story("Вход через кнопку в форме регистрации")
    @Description("Пользователь переходит на форму регистрации и по ссылке 'Войти' возвращается на форму логина, где авторизуется")
    public void loginFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginToAccount();

        RegisterPage registerPage = loginPage.goToRegister();
        LoginPage backToLogin = registerPage.goToLoginFromRegister();

        MainPage afterLogin = backToLogin.login(EXISTING_USER_EMAIL, EXISTING_USER_PASSWORD);
        Assert.assertTrue("После логина из формы регистрации должна быть доступна главная с кнопкой 'Личный кабинет'",
                afterLogin.isPersonalAccountButtonVisible());
    }

    @Test
    @Story("Вход через кнопку в форме восстановления пароля")
    @Description("Пользователь переходит на форму восстановления пароля и по ссылке 'Войти' возвращается на форму логина, где авторизуется")
    public void loginFromForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginToAccount();

        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPassword();
        LoginPage backToLogin = forgotPasswordPage.goToLoginFromForgotPassword();

        MainPage afterLogin = backToLogin.login(EXISTING_USER_EMAIL, EXISTING_USER_PASSWORD);
        Assert.assertTrue("После логина со страницы восстановления пароля должна быть доступна главная с кнопкой 'Личный кабинет'",
                afterLogin.isPersonalAccountButtonVisible());
    }
}

