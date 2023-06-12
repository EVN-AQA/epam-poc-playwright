package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactUsTest extends PlaywrightRunner {
    @Test
    @Description("Test case 592 + 595 + 594 ")
    public void verifyContactUsDisplayed() {
        headerPage.verifyContactUsItemDisplayed();
        headerPage.verifyColorContactUsItem();
    }
    @Test
    @Description("Test case 643")
    public void verifyContactPageDisplayed() {
        headerPage.clickMainNavigationOption("Contact us");
        contactPage.verifyContactUsPageDisplayed();
        headerPage.clickMainNavigationOption("Careers");
        headerPage.verifyContactUsItemDisplayed();
        headerPage.clickMainNavigationOption("Contact us");
        contactPage.verifyContactUsPageDisplayed();
    }
    @Test
    @Description("Test case 639 + 642")
    public void verifyContentOfContactUs() {
        String address = "41 University Drive • Suite 202, Newtown, PA 18940 • US";
        String p = "+1-267-759-9000";
        String f = "+1-267-759-8989";
        headerPage.clickMainNavigationOption("Contact us");
        contactPage.verifyContactUsPageDisplayed();
        contactPage.verifyBreadscumbLink("Contact Us");
        contactPage.verifyContentOfContactUs(address,p,f);
    }
}
