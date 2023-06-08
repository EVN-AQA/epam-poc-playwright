package pages.web;

import com.microsoft.playwright.Page;
import core.Configuration;
import io.qameta.allure.Step;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {

    private final Page homePage;
    private static final String TOP_MENU_LINKS = "ul.bottom-nav-left li a";

    public HomePage(Page page) {
        this.homePage = page;
    }

    @Step("Navigate to a url")
    public void navigate() {
        homePage.navigate(Configuration.get().getProperty("url"));
    }

    @Step("Check title of Home page")
    public void checkTitleHomePage() {
        assertThat(homePage).hasTitle(Pattern.compile("Software Engineering & Product Development Services"));
    }
}
