package com.epam.runners;

import annotations.PlaywrightPage;
import com.microsoft.playwright.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.mobile.HeaderPage;
import pages.mobile.HomePage;
import utils.EnvironmentReader;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlaywrightMobileRunner {
    protected Page page;
    protected BrowserContext browserContext;
    protected Browser browser;
    protected Playwright playwright;
    private boolean headless;
    private int width, height;
    @PlaywrightPage
    protected HomePage homePage;

    @PlaywrightPage
    protected HeaderPage headerPage;

    @BeforeAll
    public void init(){
        playwright = Playwright.create();
    }

    @BeforeEach
    public void setUp() {
        String browserName = System.getProperty("browserName");
        if (browserName == null) {
            browserName = EnvironmentReader.getProperty("browserName");
        }
        if (System.getProperty("headless") != null) {
            headless = Boolean.parseBoolean(System.getProperty("headless"));
        } else {
            headless = Boolean.parseBoolean(EnvironmentReader.getProperty("headless"));
        }
        System.out.println("Browser name is : " + browserName);
        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "edge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless));
                break;

            default:
                System.out.println("please pass the right browser name......");
                break;
        }
        width = Integer.parseInt(EnvironmentReader.getProperty("mobileWidth"));
        height = Integer.parseInt(EnvironmentReader.getProperty("mobileHeight"));
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height).setIsMobile(true));
        browserContext.setDefaultTimeout(40000);
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(false)
                .setSources(false));

        page = browserContext.newPage();
        initPage(this, page);
    }

    private void initPage(Object object, Page page) {
        Class<?> clazz = object.getClass().getSuperclass();
        for(Field field : clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(PlaywrightPage.class)) {
                Class<?>[] type = {Page.class};
                try {
                    field.set(this, field.getType().getConstructor(type).newInstance(page));
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    System.out.println("Did not manage to call constructor for playwright page with name " + field.getName());
                }
            }
        }
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        takeScreenshot(testInfo);
        browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("traces/" + testInfo.getDisplayName().replace("()", "") + ".zip")));
        browser.close();
    }

    public void takeScreenshot(TestInfo testInfo) {
        String path = "screenshots/" + testInfo.getDisplayName().replace("()", "") + ".png";
        Allure.addAttachment("Page screenshot", new ByteArrayInputStream(page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path)))));
    }
}
