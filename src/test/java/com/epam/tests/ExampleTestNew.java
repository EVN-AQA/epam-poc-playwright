package com.epam.tests;

import com.epam.runners.*;
import org.junit.jupiter.api.*;

public class ExampleTestNew extends PlaywrightRunner {

    @Test
    public void openEpamPageTest() {
        homePage.navigate();
        homePage.checkTitleHomePage();
    }
}
