package com.epam.runners;

import annotations.PlaywrightPage;
import com.microsoft.playwright.Page;
import core.Configuration;
import core.PlaywrightFactory;
import core.enums.ENVIRONMENT;
import io.qameta.allure.Allure;
import org.testng.annotations.*;

import pages.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;

public class PlaywrightRunner {

    PlaywrightFactory factory;
    Page page;

    @PlaywrightPage
    protected HomePage homePage;
    @PlaywrightPage
    protected HeaderPage headerPage;
    @PlaywrightPage
    protected Contact contactPage;

    @PlaywrightPage
    protected CareersPage careersPage;

    @Parameters({ "browserName", "isHeadless", "isMobile" })
    @BeforeTest(alwaysRun=true)
    public void loadEnvironment(String browserName, String isHeadless, String isMobile) {
        try {
            if (System.getProperty("env") != null) {
                ENVIRONMENT env = ENVIRONMENT.valueOf(System.getProperty("env"));
                Configuration.load(env);
            } else {
                Configuration.load(ENVIRONMENT.LOCAL);
            }
            Configuration.update("browserName", browserName);
            Configuration.update("isHeadless", isHeadless);
            Configuration.update("isMobile", isMobile);
        } catch (IllegalArgumentException ex) {
            Configuration.load(ENVIRONMENT.LOCAL);
        }
    }

    @BeforeClass(alwaysRun=true)
    public void initBrowser() {
        factory = new PlaywrightFactory();
        page = factory.initBrowser(Configuration.get());
        loadPages(this, page);
        page.navigate("");
        if (Boolean.parseBoolean(Configuration.get().getProperty("isMobile"))) {
            homePage.clickAcceptAllCookie();
        }
    }

    @Parameters({ "isTracing" })
    @BeforeMethod (alwaysRun=true)
    public void setDebug(String isTracing) {
        if (Boolean.parseBoolean(isTracing)) {
            factory.startTracing();
        }
    }

    @Parameters({ "isTracing" })
    @AfterMethod (alwaysRun=true)
    public void captureScreen(Method method, Object[] dataProviders, String isTracing) {
        String methodName = method.getName();
        if(dataProviders.length != 0) {
            methodName += dataProviders[0].toString();
        }

        attachScreenshot(methodName);
        if (Boolean.parseBoolean(isTracing)) {
            factory.stopTracing(methodName);
        }
    }

    @AfterClass (alwaysRun=true)
    public void closeBrowser() {
        page.context().browser().close();
    }

    private void loadPages(Object object, Page page) {
        Class<?> clazz = object.getClass().getSuperclass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlaywrightPage.class)) {
                Class<?>[] type = {Page.class};
                try {
                    field.set(this, field.getType().getConstructor(type).newInstance(page));
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    System.out.println("Did not manage to call constructor for playwright page with name " + field.getName());
                }
            }
        }
    }

    private void attachScreenshot(String testName) {
        String path = "screenshots/" + testName.replace("()", "") + ".png";
        Allure.addAttachment("Page screenshot",
                new ByteArrayInputStream(page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)))));
    }
}