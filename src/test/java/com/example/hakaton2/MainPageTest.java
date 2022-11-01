package com.example.hakaton2;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private final File file = new File("css_resolver.js").getAbsoluteFile();
    private final String script = "return foo();\n" + FileUtils.readFileToString(file, StandardCharsets.UTF_8);

    public MainPageTest() throws IOException {
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void foo() {
        driver.get("file:///C:/Users/Sergey/WebstormProjects/QA_hakaton/index.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String str = (String) js.executeScript(script);
        System.out.println(str);
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}


