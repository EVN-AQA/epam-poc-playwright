package com.epam.tests.web;

import com.epam.runners.PlaywrightWebRunner;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("RunOnWeb")
public class HeaderLocationTest extends PlaywrightWebRunner {
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

    @ParameterizedTest
    @CsvFileSource(resources = "/LocationData.csv", numLinesToSkip = 1, delimiterString = ";")
    @Description("Test case 575 + 579")
    public void verifySwitchLocation(String location, String title, String url) {
        homePage.navigate();
        headerPage.clickLocationMenu();
        headerPage.clickLocationOption(location);
        headerPage.verifySwitchLocationSuccessful(title, url);
    }

    @ParameterizedTest
    @ValueSource(strings = { "Services", "Insights", "About", "Careers", "Contact us"})
    @Description("Test case 577")
    public void verifyLocationDisplayedOnAnyPages(String mainNavigationName) {
        homePage.navigate();
        headerPage.clickMainNavigationOption(mainNavigationName);
        headerPage.clickLocationMenu();
        headerPage.verifyLocationMenuDisplayed();
    }
}
