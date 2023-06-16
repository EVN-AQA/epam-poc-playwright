package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import core.enums.MENU;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HeaderMainNavigationTest extends PlaywrightRunner {

    @DataProvider(name = "navOptionsEachPage")
    public Object[][] navOptions() {
        return new Object[][]{
                {MENU.SERVICES.getName()},
                {MENU.INSIGHTS.getName()},
                {MENU.ABOUT.getName()},
                {MENU.CAREERS.getName()}
        };
    }

    @BeforeMethod(alwaysRun=true)
    public void beforeMethod() {
        homePage.navigate();
    }

    @Test
    @Description("Test case 584")
    public void verifyLabelDisplayed() {
        headerPage.verifyCareerLabelDisplayed();
        headerPage.verifyAboutsLabelDisplayed();
        headerPage.verifyIndustriesLabelDisplayed();
        headerPage.verifyInsightsLabelDisplayed();
        headerPage.verifyServicesLabelDisplayed();
    }

    @Test (groups = { "smoke" })
    @Description("Test case 586 + 580")
    public void verifyMainNavigationAlwaysAvailable() {
        headerPage.clickMainNavigationOption(MENU.SERVICES.getName());
        headerPage.verifyAboutsLabelDisplayed();
        headerPage.verifyIndustriesLabelDisplayed();
        headerPage.verifyInsightsLabelDisplayed();
        headerPage.verifyServicesLabelDisplayed();
    }

    @Test
    @Description("Test case 542")
    public void verifyMainNavigationDisplay() {
        headerPage.verifyMainNavigationDisplayedTop();
    }

    @Test
    @Description("Test case 585")
    public void verifyColorChangeOfMainNavigation() {
        headerPage.verifyColorChangeOfAbout();
        headerPage.verifyColorChangeOfCareer();
        headerPage.verifyColorChangeOfInsight();
        headerPage.verifyColorChangeOfServices();
    }

    @Test(dataProvider = "navOptionsEachPage")
    @Description("Test case 577")
    public void verifyLocationDisplayedOnAnyPages(String mainNavigationName) {
        headerPage.clickMainNavigationOption(mainNavigationName);
        headerPage.verifyCorrespondingNavigation(mainNavigationName);
    }
}
