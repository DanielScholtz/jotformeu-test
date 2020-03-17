package com.jotformeu.drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebFactory {

    public static final String BROWSER_TYPE = "browser.type";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebFactory.class);

    public static WebDriver getDriver() {
        WebDriver driver;
        LOGGER.info("Starting {}", getDriverType());
        if (getDriverType().equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static String getDriverType() {
        return System.getProperty(BROWSER_TYPE);
    }
}
