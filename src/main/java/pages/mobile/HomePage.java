//package pages.mobile;
//
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.options.AriaRole;
//import core.LoadConfig;
//
//public class HomePage {
//
//    private final Page homePage;
//
//    public HomePage(Page page) {
//        this.homePage = page;
//    }
//
//    public void navigate() {
//        homePage.navigate(LoadConfig.get().getProperty("url"));
//    }
//
//    public void clickAcceptAllCookie() {
//        homePage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept All")).click();
//    }
//}
