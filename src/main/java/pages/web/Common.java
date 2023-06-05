package pages.web;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
public class Common {
    private final Page common;

    public Common(Page common) {
        this.common = common;
    }

    public void hoverMenuItem(String itemName) {
        common.locator(itemName).hover();
    }
}
