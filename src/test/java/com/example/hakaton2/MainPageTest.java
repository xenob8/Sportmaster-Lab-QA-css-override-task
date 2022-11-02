package com.example.hakaton2;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

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
        driver.get(Dotenv.load().get("WEBSITE_URL"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        assertDoesNotThrow(() -> {
            String str = (String) js.executeScript(script);
            System.out.println(str);
            assertFalse(str.contains("color changed"));
        });
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}


