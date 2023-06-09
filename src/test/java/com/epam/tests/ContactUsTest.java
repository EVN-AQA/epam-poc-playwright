package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ContactUsTest extends PlaywrightRunner {

    @Test
    @Description("Test case 592 + 595 + 594 ")
    public void verifyContactUsDisplayed() {
        homePage.navigate();
        headerPage.verifyContactUsItemDisplayed();
        headerPage.verifyColorContactUsItem();
    }
}
