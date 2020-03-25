package com.jotformeu.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.jotformeu.drivermanager.WebDriverFactory;

@ComponentScan(basePackages = "com.jotformeu")
public class E2ETestConfig {

    @Autowired
    private WebDriverFactory webDriverFactory;

    @Bean
    public WebDriver getDriver() {
        return webDriverFactory.getDriver();
    }
}
