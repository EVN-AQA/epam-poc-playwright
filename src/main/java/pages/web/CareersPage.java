package pages.web;
import com.beust.ah.A;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.asserts.SoftAssert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class CareersPage {
    private final Page careerPage;
    private SoftAssert sortAssert;
    public CareersPage(Page page) {
        this.careerPage = page;
        this.sortAssert = new SoftAssert();
    }
    public void verifySearchLabel(){
        careerPage.getByLabel("Keyword or job ID").scrollIntoViewIfNeeded();
        sortAssert.assertTrue(careerPage.getByLabel("Keyword or job ID").isVisible());
        sortAssert.assertTrue(careerPage.getByLabel("Location").first().isVisible());
        sortAssert.assertTrue(careerPage.getByLabel("Skills").isVisible());
    }
    public void verifyPlaceHolderSearch() {
        assertThat(careerPage.getByPlaceholder("Keyword")).isVisible();
        assertThat(careerPage.getByText("All Skills")).isVisible();
        assertThat(careerPage.getByTitle("All Cities in Vietnam")).isVisible();
    }

    public void clickOnFindButton() {
        careerPage.getByLabel("Keyword or job ID").scrollIntoViewIfNeeded();
        careerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Global (English)")).scrollIntoViewIfNeeded();
        careerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Global (English)")).click();
    }

    public void verifyResult() {

    }

}