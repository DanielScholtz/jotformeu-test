package com.jotformeu.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.jotformeu.drivermanager.WebDriverFactory;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.jotformeu")
@Configuration
public class E2ETestConfig {

    @Autowired
    private WebDriverFactory webDriverFactory;

    @Bean
    public WebDriver getDriver() {
        return webDriverFactory.getDriver();
    }
}
