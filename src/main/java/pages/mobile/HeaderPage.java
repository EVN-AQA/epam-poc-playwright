//package pages.mobile;
//
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.options.AriaRole;
//
//import java.util.Objects;
//import java.util.regex.Pattern;
//import io.qameta.allure.Step;
//
//import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
//
//public class HeaderPage {
//    private final Page headerPage;
//
//    private static final String LOCATION_MENU_BTN = "button.location-selector__button";
//
//    public HeaderPage(Page page) {
//        this.headerPage = page;
//    }
//
//    @Step("Click on Hamburger menu")
//    public void clickHamburgerMenu() {
//        headerPage.getByRole(AriaRole.BANNER).getByRole(AriaRole.BUTTON).first().click();
//    }
//
//    @Step("Click on main navigation {mainNavigationName} option")
//    public void clickMainNavigationOption(String mainNavigationName) {
//        if(Objects.equals(mainNavigationName, "Contact us")) {
//            headerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CONTACT US")).click();
//        }
//        else {
//            headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(mainNavigationName)).first().click();
//        }
//    }
//
//    @Step("Click on Location menu")
//    public void clickLocationMenu() {
//        headerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Global (EN)")).click();
//    }
//
//    @Step("Click on Location {locationName} option")
//    public void clickLocationOption(String locationName) {
//        headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locationName)).click();
//    }
//
//    @Step("Verify Location default text is displayed")
//    public void verifyLocationDefaultTextDisplayed() {
//        assertThat(headerPage.locator(LOCATION_MENU_BTN)).hasText("Global (EN)");
//    }
//
//    @Step("Verify Location menu is displayed")
//    public void verifyLocationMenuDisplayed() {
////        assertAll(
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Global (English)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Česká Republika (Čeština)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Czech Republic (English)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("DACH (Deutsch)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Hungary (English)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("India (English)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("日本 (日本語)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Polska (Polski)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("СНГ (Русский)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Україна (Українська)"))).isVisible(),
////                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("中国 (中文)"))).isVisible()
////        );
//    }
//
//    @Step("Verify switch Location successfully")
//    public void verifySwitchLocationSuccessful(String title, String url) {
////        assertAll(
////                () -> assertThat(headerPage).hasTitle(Pattern.compile(title)),
////                () -> assertThat(headerPage).hasURL(Pattern.compile(url))
////        );
//    }
//}
