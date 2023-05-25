package com.epam.tests.web;

import com.epam.runners.PlaywrightWebRunner;
import org.junit.jupiter.api.Test;

public class ExampleTest extends PlaywrightWebRunner {

    @Test
    public void openEpamPageTest() {
        homePage.navigate();
        homePage.checkTitleHomePage();
    }
}
