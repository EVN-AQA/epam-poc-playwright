package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import core.enums.MENU;
import io.qameta.allure.Description;
import org.testng.annotations.Test;


public class ContactUsTest extends PlaywrightRunner {
    @Test (groups = { "smoke" })
    @Description("Test case 592 + 595 + 594 ")
    public void verifyContactUsDisplayed() {
        headerPage.verifyContactUsItemDisplayed();
        headerPage.verifyColorContactUsItem();
    }

    @Test
    @Description("Test case 643")
    public void verifyContactPageDisplayed() {
        headerPage.clickMainNavigationOption(MENU.CONTACT_US.getName());
        contactPage.verifyContactUsPageDisplayed();
        headerPage.clickMainNavigationOption(MENU.CAREERS.getName());
        headerPage.verifyContactUsItemDisplayed();
        headerPage.clickMainNavigationOption(MENU.CONTACT_US.getName());
        contactPage.verifyContactUsPageDisplayed();
    }

    @Test (groups = { "smoke" })
    @Description("Test case 639 + 642")
    public void verifyContentOfContactUs() {
        String address = "41 University Drive • Suite 202, Newtown, PA 18940 • US";
        String p = "+1-267-759-90001";
        String f = "+1-267-759-89891";
        headerPage.clickMainNavigationOption(MENU.CONTACT_US.getName());
        contactPage.verifyContactUsPageDisplayed();
        contactPage.verifyBreadscumbLink(MENU.CONTACT_US.getName());
        contactPage.verifyContentOfContactUs(address,p,f);
    }
}
