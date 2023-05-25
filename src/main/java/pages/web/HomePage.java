package pages.web;

import com.microsoft.playwright.Page;
import utils.EnvironmentReader;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {

    private final Page homePage;
    private static final String TOP_MENU_LINKS = "ul.bottom-nav-left li a";

    public HomePage(Page page) {
        this.homePage = page;
    }

    public void navigate() {
        homePage.navigate(EnvironmentReader.getProperty("url"));
    }

    public void checkTitleHomePage() {
        assertThat(homePage).hasTitle(Pattern.compile("Software Engineering & Product Development Services"));
    }
}
