package com.epam.tests.web;

import com.epam.runners.PlaywrightRunner;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JsonReader;

public class HeaderLocationTest extends PlaywrightRunner {
    @DataProvider(name = "navOptions")
    public Object[][] navOptions() {
        return new Object[][]{
                {"Services"},
                {"Insights"},
                {"About"},
                {"Careers"},
                {"Contact us"}
        };
    }

    @DataProvider(name = "locationData")
    public Object[][] locationData() {
        return JsonReader.load("testData/LocationData.json");
    }

    @Test
    @Description("Test case 571")
    public void verifyLocationDefaultText() {
        homePage.navigate();
        headerPage.clickLocationMenu();
        headerPage.verifyLocationDefaultTextDisplayed();
    }

    @Test
    @Description("Test case 573")
    public void verifyAllLocationAreDisplayed() {
        homePage.navigate();
        headerPage.clickLocationMenu();
        headerPage.verifyLocationMenuDisplayed();
    }

    @Test(dataProvider = "locationData")
    @Description("Test case 575 + 579")
    public void verifySwitchLocation(String location, String title, String url) {
        homePage.navigate();
        headerPage.clickLocationMenu();
        headerPage.clickLocationOption(location);
        headerPage.verifySwitchLocationSuccessful(title, url);
    }

    @Test(dataProvider = "navOptions")
    @Description("Test case 577")
    public void verifyLocationDisplayedOnAnyPages(String mainNavigationName) {
        homePage.navigate();
        headerPage.clickMainNavigationOption(mainNavigationName);
        headerPage.clickLocationMenu();
        headerPage.verifyLocationMenuDisplayed();
    }
}
