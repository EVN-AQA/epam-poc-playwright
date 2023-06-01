package pages.web;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HeaderPage {
    private final Page headerPage;

    private static final String LOCATION_MENU_BTN = "button.location-selector__button";
    private static final String CONTACTUS_MENU_BTN= ".header__content>a[data-gtm-category='header-contact-cta']>span";
    public HeaderPage(Page page) {
        this.headerPage = page;
    }

    public void clickMainNavigationOption(String mainNavigationName) {
        if(Objects.equals(mainNavigationName, "Contact us")) {
            headerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CONTACT US")).click();
        }
        else {
            headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(mainNavigationName)).first().click();
        }
    }

    public void clickLocationMenu() {
        headerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Global (EN)")).click();
    }

    public void clickLocationOption(String locationName) {
        headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locationName)).click();
    }

    public void verifyLocationDefaultTextDisplayed() {
        assertThat(headerPage.locator(LOCATION_MENU_BTN)).hasText("Global (EN)");
    }

    public void verifyLocationMenuDisplayed() {
        assertAll(
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Global (English)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Česká Republika (Čeština)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Czech Republic (English)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("DACH (Deutsch)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Hungary (English)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("India (English)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("日本 (日本語)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Polska (Polski)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("СНГ (Русский)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Україна (Українська)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("中国 (中文)"))).isVisible()
        );
    }

    public void verifySwitchLocationSuccessful(String title, String url) {
        assertAll(
                () -> assertThat(headerPage).hasTitle(Pattern.compile(title)),
                () -> assertThat(headerPage).hasURL(Pattern.compile(url))
        );
    }

    public void verifyContactUsItemDisplayed() {

        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).isVisible();
        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).hasText("CONTACT US");
    }

    public void verifyColorContactUsItem() {
        headerPage.locator(CONTACTUS_MENU_BTN).hover();
        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).hasCSS("background-color", "rgb(255, 255, 255)");
    }
}
