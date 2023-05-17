package com.epam.tests;

import com.epam.runners.*;
import org.junit.jupiter.api.*;

public class ParallelTest extends PlaywrightRunner {

    @Test
    public void parallelPageTest() {
        homePage.navigate();
        homePage.checkTitleHomePage();
    }
}
