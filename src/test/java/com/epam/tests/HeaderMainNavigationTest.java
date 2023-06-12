package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HeaderMainNavigationTest extends PlaywrightRunner {

    @DataProvider(name = "navOptionsEachPage")
    public Object[][] navOptions() {
        return new Object[][]{
                {"Services"},
                {"Insights"},
                {"About"},
                {"Careers"}
        };
    }

    @Test
    @Description("Test case 584")
    public void verifyLabelDisplayed() {
        homePage.navigate();
        headerPage.verifyCareerLabelDisplayed();
        headerPage.verifyAboutsLabelDisplayed();
        headerPage.verifyIndustriesLabelDisplayed();
        headerPage.verifyInsightsLabelDisplayed();
        headerPage.verifyServicesLabelDisplayed();
    }

    @Test
    @Description("Test case 586 + 580")
    public void verifyMainNavigationAlwaysAvailable() {
        homePage.navigate();
        headerPage.ClickOnServicesItem();
        headerPage.verifyAboutsLabelDisplayed();
        headerPage.verifyIndustriesLabelDisplayed();
        headerPage.verifyInsightsLabelDisplayed();
        headerPage.verifyServicesLabelDisplayed();
    }

    @Test
    @Description("Test case 542")
    public void verifyMainNavigationDisplay() {
        homePage.navigate();
        headerPage.verifyMainNavigationDisplayedTop();
    }

    @Test
    @Description("Test case 585")
    public void verifyColorChangeOfMainNavigation() {
        homePage.navigate();
//        headerPage.verifyColorChangeOfIndustries();
        headerPage.verifyColorChangeOfAbout();
        headerPage.verifyColorChangeOfCareer();
        headerPage.verifyColorChangeOfInsight();
        headerPage.verifyColorChangeOfServices();
    }

    @Test(dataProvider = "navOptionsEachPage")
    @Description("Test case 577")
    public void verifyLocationDisplayedOnAnyPages(String mainNavigationName) {
        homePage.navigate();
        headerPage.clickMainNavigationOption(mainNavigationName);
        headerPage.verifyCorrespondingNavigation(mainNavigationName);
    }
}
