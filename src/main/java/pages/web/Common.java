package pages.web;

import com.microsoft.playwright.Page;

public class Common {
    private final Page common;

    public Common(Page common) {
        this.common = common;
    }

    public void hoverMenuItem(String itemName) {
        common.locator(itemName).hover();
    }
}
