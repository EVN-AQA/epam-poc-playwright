package pages;
import com.beust.ah.A;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.asserts.SoftAssert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class CareersPage {
    private final Page careerPage;
    private SoftAssert sortAssert;
    private static final String SEARCH_RESULT_TITLE = ".search-result__heading-23";
    private static final String REMOTE= "#jobSearchFilterForm";
    private static final String LOCATION = ".select2-selection__rendered";
    public CareersPage(Page page) {
        this.careerPage = page;
        this.sortAssert = new SoftAssert();
    }
    public void verifySearchLabel(){
        sortAssert.assertTrue(careerPage.getByLabel("Keyword or job ID").isVisible());
        sortAssert.assertTrue(careerPage.getByLabel("Location").first().isVisible());
        sortAssert.assertTrue(careerPage.getByLabel("Skills").isVisible());
    }
    public void verifyPlaceHolderSearch() {
        assertThat(careerPage.getByPlaceholder("Keyword")).isVisible();
        assertThat(careerPage.getByText("All Skills")).isVisible();
    }

    public void clickOnFindButton() {
        careerPage.locator(REMOTE).scrollIntoViewIfNeeded();
        careerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Find")).click();

    }
    public void verifyResultTitle() {
        careerPage.locator(SEARCH_RESULT_TITLE).scrollIntoViewIfNeeded();
        assertThat(careerPage.locator(SEARCH_RESULT_TITLE)).isVisible();
    }
    public void inputJobID(String jobID) {
        careerPage.locator(REMOTE).scrollIntoViewIfNeeded();
        careerPage.getByRole(AriaRole.TEXTBOX).first().fill(jobID);
    }
    public void verifyNoResultMessage(String message) {
        assertThat(careerPage.getByText(message));
    }
    public void clickOnFindYourDreamJob() {
        careerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Find Your Dream Job")).first().click();
    }
    public void verifyTitleResultWithKeyword(String keyword) {
        careerPage.locator(SEARCH_RESULT_TITLE).scrollIntoViewIfNeeded();
        assertThat(careerPage.locator(SEARCH_RESULT_TITLE)).containsText(keyword);
    }
    public void selectCountry(String countryName, String city) {
        careerPage.locator(REMOTE).scrollIntoViewIfNeeded();
        careerPage.locator(LOCATION).click();
        careerPage.getByText(countryName, new Page.GetByTextOptions().setExact(true)).click();
        careerPage.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(city)).click();
    }
    public void selectSkill(String skill) {
        careerPage.locator(REMOTE).scrollIntoViewIfNeeded();
        careerPage.getByText("All Skills").click();
        careerPage.getByRole(AriaRole.TREEITEM, new Page.GetByRoleOptions().setName(skill)).getByText(skill).click();
    }
    public void verifySearchResultOfSelection() {
        careerPage.getByText("We found 26 job openings for you").isVisible();
    }
    public void verifySortOption(String option) {
        careerPage.getByText(option).isVisible();
    }
    public void clickOnFilterSearching(String filterName) {
        careerPage.getByText(filterName).first().click();
    }

}