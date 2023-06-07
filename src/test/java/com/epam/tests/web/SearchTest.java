package com.epam.tests.web;
import com.epam.runners.PlaywrightRunner;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Tag("search")
public class SearchTest extends PlaywrightRunner {
    @Test
    @Description("Test case 664")
    public void verifySearchSessionDisplayed() {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.verifySearchLabel();
        careersPage.verifyPlaceHolderSearch();
    }

    public void  verifySearchResultWithoutInput() {
    }
}
