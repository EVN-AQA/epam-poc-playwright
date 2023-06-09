package com.epam.tests.web;
import com.epam.runners.PlaywrightRunner;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Tag("search")
public class SearchTest extends PlaywrightRunner {
    @DataProvider(name = "filterSearching")
    public Object[][] navOptions() {
        return new Object[][]{
                {"Remote"},
                {"Office"},
                {"Open to Relocation"}
        };
    }
    @Test
    @Description("Test case 664")
    public void verifySearchSessionDisplayed() {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.verifySearchLabel();
        careersPage.verifyPlaceHolderSearch();
    }
    @Test
    @Description("Test case 674")
    public void  verifySearchResultWithoutInput() {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.clickOnFindButton();
        careersPage.verifyResultTitle();
    }
    @Test
    @Description("Test case 673")
    public void verifyNoResultMessage() {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.inputJobID("abc23avc");
        careersPage.clickOnFindButton();
        careersPage.verifyNoResultMessage("Don’t see the dream job you were hoping to find?");
    }
    @Test
    @Description("Test case 666")
    public void verifySearchJobOfAllCountryAndSkill() {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.clickOnFindYourDreamJob();
        careersPage.verifyResultTitle();
    }
    @Test
    @Description("Test case 667")
    public void verifySearchMatchingWithKeyword() {
        String jobId = "test";
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.inputJobID(jobId);
        careersPage.clickOnFindButton();
        careersPage.verifyTitleResultWithKeyword(jobId);
    }
    @Test
    @Description("Test case 670 + 677")
    public void verifySearchMatchingWithSkill() {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.selectCountry("Argentina", "All Cities in Argentina");
        careersPage.selectSkill("Business and Data Analysis");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
        careersPage.verifySortOption("Relevance");
    }
    @Test
    @Description("Test case 669")
    public void verifySearchWithLocation() {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.selectCountry("Argentina", "All Cities in Argentina");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
    }
    @Test
    @Description("Test case 671")
    public void verifySearchWithCombination() {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.inputJobID("automation");
        careersPage.selectCountry("Singapore", "All Cities in Singapore");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
    }
    @Test(dataProvider = "filterSearching")
    @Description("Test case 672")
    public void verifySearchWithFilterSearching(String filter) {
        homePage.navigate();
        headerPage.navigateToCareersPage();
        careersPage.clickOnFilterSearching(filter);
        careersPage.verifySearchResultOfSelection();
    }
}
