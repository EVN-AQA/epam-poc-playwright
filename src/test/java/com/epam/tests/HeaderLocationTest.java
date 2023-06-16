package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import core.Configuration;
import core.enums.MENU;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JsonReader;

public class HeaderLocationTest extends PlaywrightRunner {
    @DataProvider(name = "navOptions")
    public Object[][] navOptions() {
        return new Object[][]{
                {MENU.SERVICES.getName()},
                {MENU.INSIGHTS.getName()},
                {MENU.ABOUT.getName()},
                {MENU.CAREERS.getName()},
                {MENU.CONTACT_US.getName()}
        };
    }

    @DataProvider(name = "locationData")
    public Object[][] locationData() {
        return JsonReader.load("testData/LocationData.json");
    }

    @BeforeMethod(alwaysRun=true)
    public void setConditions() {
        homePage.navigate();
        if (Boolean.parseBoolean(Configuration.get().getProperty("isMobile"))) {
            headerPage.clickHamburgerMenu();
        }
    }

    @Test
    @Description("Test case 571")
    public void verifyLocationDefaultText() {
        headerPage.clickLocationMenu();
        headerPage.verifyLocationDefaultTextDisplayed();
    }

    @Test (groups = { "smoke" })
    @Description("Test case 573")
    public void verifyAllLocationAreDisplayed() {
        headerPage.clickLocationMenu();
        headerPage.verifyLocationMenuDisplayed();
    }

    @Test(dataProvider = "locationData")
    @Description("Test case 575 + 579")
    public void verifySwitchLocation(String location, String title, String url) {
        headerPage.clickLocationMenu();
        headerPage.clickLocationOption(location);
        headerPage.verifySwitchLocationSuccessful(title, url);
    }

    @Test(dataProvider = "navOptions")
    @Description("Test case 577")
    public void verifyLocationDisplayedOnAnyPages(String mainNavigationName) {
        headerPage.clickMainNavigationOption(mainNavigationName);
        if (Boolean.parseBoolean(Configuration.get().getProperty("isMobile"))) {
            headerPage.clickHamburgerMenu();
        }
        headerPage.clickLocationMenu();
        headerPage.verifyLocationMenuDisplayed();
    }
}
