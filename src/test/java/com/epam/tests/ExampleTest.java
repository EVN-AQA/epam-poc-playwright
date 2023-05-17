package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import org.junit.jupiter.api.Test;

public class ExampleTest extends PlaywrightRunner {

    @Test
    public void openEpamPageTest() {
        homePage.navigate();
        homePage.checkTitleHomePage();
    }
}
