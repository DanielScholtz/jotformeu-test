package com.jotformeu.config;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.jotformeu")
@AutoConfigurationPackage
public class E2ETestConfig {
}
