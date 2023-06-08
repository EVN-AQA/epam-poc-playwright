//package pages.mobile;
//
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.options.AriaRole;
//import core.LoadConfig;
//import io.qameta.allure.Step;
//
//public class HomePage {
//
//    private final Page homePage;
//
//    public HomePage(Page page) {
//        this.homePage = page;
//    }
//
//    @Step("Navigate to a url")
//    public void navigate() {
//        homePage.navigate(LoadConfig.get().getProperty("url"));
//    }
//
//    @Step("Click on Accept All Cookie")
//    public void clickAcceptAllCookie() {
//        homePage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept All")).click();
//    }
//}
