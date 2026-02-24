package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;

    protected static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    // Задайте действующие логин/пароль существующего пользователя
    protected static final String EXISTING_USER_EMAIL = "user@example.com";
    protected static final String EXISTING_USER_PASSWORD = "password123";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        if ("yandex".equals(browser)) {
            initYandexBrowser();
        } else {
            initChrome();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Запуск теста в Google Chrome")
    private void initChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Step("Запуск теста в Яндекс.Браузере")
    private void initYandexBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // Укажите путь к бинарнику Яндекс.Браузера в вашей системе
        options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
}

