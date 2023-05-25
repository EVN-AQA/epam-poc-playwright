package pages.mobile;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.EnvironmentReader;

public class HomePage {

    private final Page homePage;

    public HomePage(Page page) {
        this.homePage = page;
    }

    public void navigate() {
        homePage.navigate(EnvironmentReader.getProperty("url"));
    }

    public void clickAcceptAllCookie() {
        homePage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept All")).click();
    }
}
