package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.driver.BaseTest;
import org.example.pages.MainPage;
import org.junit.Assert;
import org.junit.Test;

@Epic("Stellar Burgers")
@Feature("Раздел 'Конструктор'")
public class ConstructorTests extends BaseTest {

    @Test
    @Story("Переход к разделу 'Булки'")
    @Description("Проверка, что при клике по вкладке 'Булки' она становится активной")
    public void openBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSaucesTab(); // переключимся куда-нибудь, чтобы клик по 'Булки' был осмысленным
        mainPage.openBunsTab();
        Assert.assertTrue("Ожидалось, что вкладка 'Булки' станет активной", mainPage.isBunsTabActive());
    }

    @Test
    @Story("Переход к разделу 'Соусы'")
    @Description("Проверка, что при клике по вкладке 'Соусы' она становится активной")
    public void openSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openSaucesTab();
        Assert.assertTrue("Ожидалось, что вкладка 'Соусы' станет активной", mainPage.isSaucesTabActive());
    }

    @Test
    @Story("Переход к разделу 'Начинки'")
    @Description("Проверка, что при клике по вкладке 'Начинки' она становится активной")
    public void openFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openFillingsTab();
        Assert.assertTrue("Ожидалось, что вкладка 'Начинки' станет активной", mainPage.isFillingsTabActive());
    }
}

