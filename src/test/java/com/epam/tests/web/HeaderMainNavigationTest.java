package com.epam.tests.web;
import com.epam.runners.PlaywrightWebRunner;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
@Tag("linhtest")
public class HeaderMainNavigationTest extends PlaywrightWebRunner{
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
    public void verifyMainNavigationDisplay(){
        homePage.navigate();
        headerPage.verifyMainNavigationDisplayedTop();
    }

    @Test
    @Description("Test case 585")
    public void verifyColorChangeOfMainNavigation(){
        homePage.navigate();
//        headerPage.verifyColorChangeOfIndustries();
        headerPage.verifyColorChangeOfAbout();
        headerPage.verifyColorChangeOfCareer();
        headerPage.verifyColorChangeOfInsight();
        headerPage.verifyColorChangeOfServices();
    }
    @ParameterizedTest
    @ValueSource(strings = { "Services", "Insights", "About", "Careers"})
    @Description("Test case 577")
    public void verifyLocationDisplayedOnAnyPages(String mainNavigationName) {
        homePage.navigate();
        headerPage.clickMainNavigationOption(mainNavigationName);
        headerPage.verifyCorrespondingNavigation(mainNavigationName);
    }
}
