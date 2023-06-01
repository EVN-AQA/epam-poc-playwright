package com.epam.tests.web;

import com.epam.runners.PlaywrightWebRunner;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
@Tag("RunOnWeb")
public class ExampleTest extends PlaywrightWebRunner {

    @Test
    public void openEpamPageTest() {
        homePage.navigate();
        homePage.checkTitleHomePage();
    }
    @Test
    @Description("Test case 592 + 595 + 594 ")
    public void verifyContactUsDisplayed() {
        homePage.navigate();
        headerPage.verifyContactUsItemDisplayed();
        headerPage.verifyColorContactUsItem();
    }
}
