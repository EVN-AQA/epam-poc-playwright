package com.epam.runners;

import annotations.PlaywrightPage;
import com.microsoft.playwright.Page;
import core.Configuration;
import core.PlaywrightFactory;
import core.enums.ENVIRONMENT;
import io.qameta.allure.Allure;
import org.testng.annotations.*;
import pages.web.*;

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
    @BeforeTest
    public void loadEnvironment() {
        try {
            if (System.getProperty("env") != null) {
                ENVIRONMENT env = ENVIRONMENT.valueOf(System.getProperty("env"));
                Configuration.load(env);
            } else {
                Configuration.load(ENVIRONMENT.LOCAL);
            }
        } catch (IllegalArgumentException ex) {
            Configuration.load(ENVIRONMENT.LOCAL);
        }
    }

    @BeforeClass
    public void initBrowser() {
        factory = new PlaywrightFactory();
        page = factory.initBrowser(Configuration.get());
        loadPages(this, page);
        page.navigate(Configuration.get().getProperty("url"));
    }

    @BeforeMethod
    public void setDebug() {
        if (Boolean.parseBoolean(Configuration.get().getProperty("isTracing"))) {
            factory.startTracing();
        }
    }

    @AfterMethod
    public void captureScreen(Method method) {
        attachScreenshot(method.getName());
        if (Boolean.parseBoolean(Configuration.get().getProperty("isTracing"))) {
            factory.stopTracing(method.getName());
        }
    }

    @AfterClass
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