package com.epam.runners;
import annotations.PlaywrightPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pages.HomePage;
import utils.EnvironmentReader;

import java.nio.file.Paths;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlaywrightRunner {
    protected Page page;
    protected BrowserContext browserContext;
    protected Browser browser;
    protected Playwright playwright;
    private boolean isMobile;
    private boolean headless;
    private int width, height;
    @PlaywrightPage
    protected HomePage homePage;

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
        if (System.getProperty("isMobile") != null) {
            isMobile = Boolean.parseBoolean(System.getProperty("isMobile"));
        } else {
            isMobile = Boolean.parseBoolean(EnvironmentReader.getProperty("isMobile"));
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
        if (isMobile) {
            width = Integer.parseInt(EnvironmentReader.getProperty("mobileWidth"));
            height = Integer.parseInt(EnvironmentReader.getProperty("mobileHeight"));
        } else {
            width = Integer.parseInt(EnvironmentReader.getProperty("deskWidth"));
            height = Integer.parseInt(EnvironmentReader.getProperty("deskHeight"));
        }
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height).setIsMobile(isMobile));
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
        browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("traces/" + testInfo.getDisplayName().replace("()", "") + ".zip")));
        browserContext.close();
        browser.close();
    }
}

