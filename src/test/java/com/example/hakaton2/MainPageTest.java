package com.example.hakaton2;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainPageTest {
    private WebDriver driver;
    private final String script;
    private final String url = URLProvider.getUrl(Dotenv.load().get("WEBSITE_URL"));

    public MainPageTest() throws IOException {
        File jsScript = new File("css_resolver.js").getAbsoluteFile();
        script = "return checkOverrides();\n" + FileUtils.readFileToString(jsScript, StandardCharsets.UTF_8);
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void checkCSSOverriding() {
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertDoesNotThrow(() -> {
            String output = (String) js.executeScript(script);
            System.out.println(output);
            assertFalse(output.contains("color changed"));
        });
    }

    @AfterEach
    public void close() {
        driver.quit();
    }

}


