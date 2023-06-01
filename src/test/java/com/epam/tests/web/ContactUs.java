package com.epam.tests.web;
import com.epam.runners.PlaywrightWebRunner;
import jdk.jfr.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
@Tag("RunOn")
public class ContactUs extends PlaywrightWebRunner{
    @Test
    @Description("Test case 592 + 595 + 594 ")
    public void verifyContactUsDisplayed() {
        homePage.navigate();
        headerPage.verifyContactUsItemDisplayed();
        headerPage.verifyColorContactUsItem();
    }

}
