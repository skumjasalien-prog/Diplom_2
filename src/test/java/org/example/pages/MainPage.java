package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private final WebDriver driver;

    // Кнопки хедера/главной
    private final By loginToAccountButton = By.xpath("//button[.='Войти в аккаунт']");
    // Ссылка личного кабинета в хедере
    private final By personalAccountButton = By.xpath("//a[@href='/account']");

    // Вкладки конструктора (div-элементы с текстом вкладки)
    private final By bunsTab = By.xpath("//span[.='Булки']/parent::*");
    private final By saucesTab = By.xpath("//span[.='Соусы']/parent::*");
    private final By fillingsTab = By.xpath("//span[.='Начинки']/parent::*");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку 'Войти в аккаунт' на главной")
    public LoginPage clickLoginToAccount() {
        driver.findElement(loginToAccountButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public LoginPage clickPersonalAccount() {
        driver.findElement(personalAccountButton).click();
        return new LoginPage(driver);
    }

    @Step("Проверить, что кнопка 'Личный кабинет' видна в хедере")
    public boolean isPersonalAccountButtonVisible() {
        return driver.findElement(personalAccountButton).isDisplayed();
    }

    @Step("Перейти во вкладку 'Булки'")
    public void openBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Перейти во вкладку 'Соусы'")
    public void openSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Перейти во вкладку 'Начинки'")
    public void openFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    private boolean isTabActive(By tabLocator) {
        WebElement element = driver.findElement(tabLocator);
        String clazz = element.getAttribute("class");
        if (clazz == null) {
            return false;
        }
        // В разных версиях проекта имя класса может отличаться,
        // поэтому проверяем несколько характерных подстрок
        return clazz.contains("tab_tab_type_current") || clazz.contains("current");
    }

    @Step("Проверить, что активна вкладка 'Булки'")
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить, что активна вкладка 'Соусы'")
    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить, что активна вкладка 'Начинки'")
    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }
}

