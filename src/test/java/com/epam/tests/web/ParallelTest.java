package com.epam.tests.web;

import com.epam.runners.*;
import org.junit.jupiter.api.*;

public class ParallelTest extends PlaywrightWebRunner {

    @Test
    public void parallelPageTest() {
        homePage.navigate();
        homePage.checkTitleHomePage();
    }
}