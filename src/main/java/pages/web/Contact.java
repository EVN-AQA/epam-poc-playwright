package pages.web;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Contact {
    private final Page contactPage;
    private static final String MAIN_CONTACT_US = "main[id='main']";
    private static final String BREADSCUMB_LINK= ".breadcrumbs__link";
    public Contact(Page contactPage) {
        this.contactPage = contactPage;
    }

//    ============= VERIFY =============
    public void verifyContactUsPageDisplayed() {
        assertThat(contactPage.locator(MAIN_CONTACT_US)).isVisible();
    }
    public void verifyBreadscumbLink(String link) {
        assertThat(contactPage.getByText("Home About Who We Are Contact Us")).isVisible();
    }
    public void verifyContentOfContactUs(String address, String p, String f) {
        contactPage.getByText(address).isVisible();
        contactPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(p)).isVisible();
        contactPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(f)).isVisible();
    }


}
