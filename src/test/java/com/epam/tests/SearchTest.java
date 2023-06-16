package com.epam.tests;
import com.epam.runners.PlaywrightRunner;
import core.enums.MENU;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod()
    public void beforeMethod() {
        headerPage.clickMainNavigationOption(MENU.CAREERS.getName());
    }

    @Test
    @Description("Test case 664")
    public void verifySearchSessionDisplayed() {
        careersPage.verifySearchLabel();
        careersPage.verifyPlaceHolderSearch();
    }

    @Test
    @Description("Test case 674")
    public void  verifySearchResultWithoutInput() {
        careersPage.clickOnFindButton();
        careersPage.verifyResultTitle();
    }

    @Test
    @Description("Test case 673")
    public void verifyNoResultMessage() {
        careersPage.inputJobID("abc23avc");
        careersPage.clickOnFindButton();
        careersPage.verifyNoResultMessage("Don’t see the dream job you were hoping to find?");
    }

    @Test
    @Description("Test case 666")
    public void verifySearchJobOfAllCountryAndSkill() {
        careersPage.clickOnFindYourDreamJob();
        careersPage.verifyResultTitle();
    }

    @Test
    @Description("Test case 667")
    public void verifySearchMatchingWithKeyword() {
        String jobId = "test";
        careersPage.inputJobID(jobId);
        careersPage.clickOnFindButton();
        careersPage.verifyTitleResultWithKeyword(jobId);
    }

    @Test
    @Description("Test case 670 + 677")
    public void verifySearchMatchingWithSkill() {
        careersPage.selectCountry("Argentina", "All Cities in Argentina");
        careersPage.selectSkill("Business and Data Analysis");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
        careersPage.verifySortOption("Relevance");
    }

    @Test
    @Description("Test case 669")
    public void verifySearchWithLocation() {
        careersPage.selectCountry("Argentina", "All Cities in Argentina");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
    }

    @Test
    @Description("Test case 671")
    public void verifySearchWithCombination() {
        careersPage.inputJobID("automation");
        careersPage.selectCountry("Singapore", "All Cities in Singapore");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
    }

    @Test(dataProvider = "filterSearching")
    @Description("Test case 672")
    public void verifySearchWithFilterSearching(String filter) {
        careersPage.clickOnFilterSearching(filter);
        careersPage.verifySearchResultOfSelection();
    }
}
